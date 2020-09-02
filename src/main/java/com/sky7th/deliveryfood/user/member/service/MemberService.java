package com.sky7th.deliveryfood.user.member.service;

import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationToken;
import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationTokenService;
import com.sky7th.deliveryfood.generic.mail.event.OnGenerateEmailVerificationEvent;
import com.sky7th.deliveryfood.security.service.UserValidateService;
import com.sky7th.deliveryfood.user.LoginRequestDto;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.domain.MemberRepository;
import com.sky7th.deliveryfood.user.member.dto.MemberResponseDto;
import com.sky7th.deliveryfood.user.member.dto.MemberDetailResponseDto;
import com.sky7th.deliveryfood.user.member.service.exception.AlreadyEmailVerifiedException;
import com.sky7th.deliveryfood.user.member.service.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final EmailVerificationTokenService emailVerificationTokenService;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final UserValidateService userValidateService;

  @Transactional(readOnly = true)
  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
  }

  @Transactional(readOnly = true)
  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(NotFoundMemberException::new);
  }

  @Transactional(readOnly = true)
  public MemberDetailResponseDto findByIdWithMemberAddresses(Long memberId, UserContext userContext) {
    Member member = memberRepository.findByIdWithMemberAddresses(memberId).orElseThrow(NotFoundMemberException::new);
    member.same(userContext.toMember());

    return MemberDetailResponseDto.of(member);
  }

  @Transactional
  public MemberResponseDto register(RegisterRequestDto registerRequestDto) {
    Member member = save(registerRequestDto);
    sendVerificationEmail(member);

    return MemberResponseDto.of(member);
  }

  public Member save(RegisterRequestDto registerRequestDto) {
    Member member = new Member(
        registerRequestDto.getEmail(),
        bCryptPasswordEncoder.encode(registerRequestDto.getPassword()),
        registerRequestDto.getUsername());

    return memberRepository.save(member);
  }

  private void sendVerificationEmail(Member member) {
    UriComponentsBuilder urlBuilder = ServletUriComponentsBuilder.fromCurrentContextPath().path(MEMBER_REGISTER_CONFIRM_URL);
    OnGenerateEmailVerificationEvent onGenerateEmailVerificationEvent = new OnGenerateEmailVerificationEvent(member, urlBuilder);
    applicationEventPublisher.publishEvent(onGenerateEmailVerificationEvent);
  }

  @Transactional
  public void emailVerify(String key) {
    EmailVerificationToken token = emailVerificationTokenService.findById(key);
    token.verifyExpiration();
    Member member = findById(token.getUserId());
    member.emailVerify();
  }

  public void resendVerificationEmail(LoginRequestDto loginRequestDto) {
    Member member = findByEmail(loginRequestDto.getEmail());
    userValidateService.validateUser(member, loginRequestDto.getPassword());

    if (member.getEmailVerified()) {
      throw new AlreadyEmailVerifiedException();
    }

    sendVerificationEmail(member);
  }
}
