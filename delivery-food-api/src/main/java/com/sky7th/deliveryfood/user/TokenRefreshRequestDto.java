package com.sky7th.deliveryfood.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenRefreshRequestDto {

  private String refreshToken;

  public TokenRefreshRequestDto(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  private TokenRefreshRequestDto() {
  }
}
