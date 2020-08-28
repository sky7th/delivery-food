package com.sky7th.deliveryfood.security.refreshtoken;

import com.sky7th.deliveryfood.security.exception.TokenRefreshException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken findById(String id) {
    return refreshTokenRepository.findById(id)
        .orElseThrow(() -> new TokenRefreshException("해당 토큰을 찾을 수 없습니다. 다시 로그인 해주세요."));
  }
}
