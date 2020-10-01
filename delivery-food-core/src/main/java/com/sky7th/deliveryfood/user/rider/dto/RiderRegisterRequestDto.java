package com.sky7th.deliveryfood.user.rider.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RiderRegisterRequestDto {

  private String email;
  private String password;
  private String username;

  public RiderRegisterRequestDto(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  private RiderRegisterRequestDto() {
  }
}
