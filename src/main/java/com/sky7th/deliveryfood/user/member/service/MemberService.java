package com.sky7th.deliveryfood.user.member.service;

import com.sky7th.deliveryfood.security.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.domain.MemberRepository;
import com.sky7th.deliveryfood.user.member.dto.MemberResponseDto;
import com.sky7th.deliveryfood.user.member.service.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
  }

  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(NotFoundMemberException::new);
  }

  public MemberResponseDto save(RegisterRequestDto registerRequestDto) {
    if (memberRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    Member member = new Member(
        registerRequestDto.getEmail(),
        bCryptPasswordEncoder.encode(registerRequestDto.getPassword()),
        registerRequestDto.getUsername());

    return MemberResponseDto.of(memberRepository.save(member));
  }
}
