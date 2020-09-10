package com.sky7th.deliveryfood.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterRequestDto {

  private String email;
  private String password;
  private String username;

  public RegisterRequestDto(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  private RegisterRequestDto() {
  }
}
