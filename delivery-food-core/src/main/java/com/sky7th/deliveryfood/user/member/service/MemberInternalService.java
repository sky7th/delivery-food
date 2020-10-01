package com.sky7th.deliveryfood.user.member.service;

import com.sky7th.deliveryfood.user.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.domain.MemberRepository;
import com.sky7th.deliveryfood.user.member.dto.MemberRegisterRequestDto;
import com.sky7th.deliveryfood.user.exception.MismatchUserException;
import com.sky7th.deliveryfood.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberInternalService {

  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);
  }

  @Transactional(readOnly = true)
  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
  }

  public Member save(MemberRegisterRequestDto requestDto, String encodedPassword) {
    if (memberRepository.existsByEmail(requestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    return memberRepository.save(new Member(requestDto.getEmail(), encodedPassword, requestDto.getUsername()));
  }

  public boolean isMyself(Long memberId, UserContext userContext) {
    if (!memberId.equals(userContext.getId())) {
      throw new MismatchUserException();
    }
    return true;
  }
}
