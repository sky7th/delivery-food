package com.sky7th.deliveryfood.user.member.service;

import com.sky7th.deliveryfood.user.member.Member;
import com.sky7th.deliveryfood.user.member.MemberRepository;
import com.sky7th.deliveryfood.user.member.service.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
  }

  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email).orElseThrow(NotFoundMemberException::new);
  }
}
