package com.sky7th.deliveryfood.security.refreshtoken;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sky7th.deliveryfood.security.exception.TokenRefreshException;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class RefreshTokenTest {

  @Test
  void refresh_token의_만료시간이_지났으면_exception_발생() {
    RefreshToken refreshToken = new RefreshToken("token", Instant.now().minusSeconds(100));

    assertThrows(TokenRefreshException.class, refreshToken::verifyExpiration);
  }
}