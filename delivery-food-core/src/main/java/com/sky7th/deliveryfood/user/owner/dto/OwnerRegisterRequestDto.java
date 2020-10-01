package com.sky7th.deliveryfood.user.owner.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OwnerRegisterRequestDto {

  private String email;
  private String password;
  private String username;

  public OwnerRegisterRequestDto(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  private OwnerRegisterRequestDto() {
  }
}
