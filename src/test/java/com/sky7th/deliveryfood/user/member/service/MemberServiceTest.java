package com.sky7th.deliveryfood.user.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationToken;
import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationTokenService;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.domain.MemberRepository;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private EmailVerificationTokenService emailVerificationTokenService;

  @InjectMocks
  private MemberService memberService;

  @Test
  void 이메일_인증을_한다() {
    String emailVerificationTokenKey = "1";
    Long userId = 1L;
    EmailVerificationToken token = new EmailVerificationToken(emailVerificationTokenKey, userId,
        Instant.now().plusSeconds(100));
    when(emailVerificationTokenService.findById(any())).thenReturn(token);
    Member member = new Member("email", "password", "username");
    when(memberRepository.findById(userId)).thenReturn(java.util.Optional.of(member));

    memberService.emailVerify(emailVerificationTokenKey);

    assertThat(member.getEmailVerified()).isEqualTo(true);
  }
}