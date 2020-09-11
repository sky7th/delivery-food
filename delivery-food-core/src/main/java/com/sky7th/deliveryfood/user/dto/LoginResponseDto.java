package com.sky7th.deliveryfood.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginResponseDto {

  private String accessToken;
  private String refreshToken;
  private String tokenType;
  private Long expiryDuration;

  public LoginResponseDto(String accessToken, String refreshToken, Long expiryDuration) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.tokenType = "Bearer";
    this.expiryDuration = expiryDuration;
  }
}
