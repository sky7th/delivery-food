package com.sky7th.deliveryfood.user.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberResponseDto {

  private Long id;
  private String email;
  private String username;
}
