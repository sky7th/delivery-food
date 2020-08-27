package com.sky7th.deliveryfood.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LoginRequestDto {

  private String email;
  private String password;

  public LoginRequestDto(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
