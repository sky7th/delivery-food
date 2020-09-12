package com.sky7th.deliveryfood.user.member.service;

import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationToken;
import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationTokenService;
import com.sky7th.deliveryfood.generic.mail.event.OnGenerateEmailVerificationEvent;
import com.sky7th.deliveryfood.security.service.UserValidateService;
import com.sky7th.deliveryfood.user.dto.LoginRequestDto;
import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.dto.MemberDetailResponseDto;
import com.sky7th.deliveryfood.user.member.dto.MemberRegisterRequestDto;
import com.sky7th.deliveryfood.user.member.exception.AlreadyEmailVerifiedException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

  private static final String MEMBER_REGISTER_CONFIRM_URL = "/members/register/confirm";

  private final MemberInternalService memberInternalService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final EmailVerificationTokenService emailVerificationTokenService;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final UserValidateService userValidateService;

  @PreAuthorize("@memberInternalService.isMyself(#memberId, #userContext)")
  public MemberDetailResponseDto findById(Long memberId, UserContext userContext) {
    return MemberDetailResponseDto.of(memberInternalService.findById(memberId));
  }

  public MemberDetailResponseDto register(MemberRegisterRequestDto requestDto) {
    String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());
    Member member = memberInternalService.save(requestDto, encodedPassword);
    sendVerificationEmail(member);

    return MemberDetailResponseDto.of(member);
  }

  private void sendVerificationEmail(Member member) {
    UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path(MEMBER_REGISTER_CONFIRM_URL);
    OnGenerateEmailVerificationEvent onGenerateEmailVerificationEvent = new OnGenerateEmailVerificationEvent(member, urlBuilder);
    applicationEventPublisher.publishEvent(onGenerateEmailVerificationEvent);
  }

  public void emailVerify(String key) {
    EmailVerificationToken token = emailVerificationTokenService.findById(key);
    token.verifyExpiration();
    Member member = memberInternalService.findById(token.getUserId());
    member.emailVerify();
  }

  public void resendVerificationEmail(LoginRequestDto loginRequestDto) {
    Member member = memberInternalService.findByEmail(loginRequestDto.getEmail());
    userValidateService.validateUser(member, loginRequestDto.getPassword());

    if (member.getEmailVerified()) {
      throw new AlreadyEmailVerifiedException();
    }

    sendVerificationEmail(member);
  }
}
