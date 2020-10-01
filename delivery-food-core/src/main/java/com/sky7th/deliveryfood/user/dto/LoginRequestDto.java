package com.sky7th.deliveryfood.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginRequestDto {

  private String email;
  private String password;

  public LoginRequestDto(String email, String password) {
    this.email = email;
    this.password = password;
  }

  private LoginRequestDto() {
  }
}
