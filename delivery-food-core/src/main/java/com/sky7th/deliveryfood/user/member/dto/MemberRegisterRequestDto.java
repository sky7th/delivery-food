package com.sky7th.deliveryfood.user.member.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberRegisterRequestDto {

  private String email;
  private String password;
  private String username;

  public MemberRegisterRequestDto(String email, String password, String username) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  private MemberRegisterRequestDto() {
  }
}
