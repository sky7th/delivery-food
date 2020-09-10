package com.sky7th.deliveryfood.generic.mail.emailVerificationToken;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sky7th.deliveryfood.generic.mail.domain.token.EmailVerificationToken;
import com.sky7th.deliveryfood.security.exception.TokenRefreshException;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class EmailVerificationTokenTest {

  @Test
  void 이메일_인증의_만료시간이_지났으면_exception_발생() {
    EmailVerificationToken token = new EmailVerificationToken("111", 1L, Instant.now().minusSeconds(100));

    assertThrows(TokenRefreshException.class, token::verifyExpiration);
  }
}