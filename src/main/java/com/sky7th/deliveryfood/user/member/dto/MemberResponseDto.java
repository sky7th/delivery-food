package com.sky7th.deliveryfood.user.member.dto;

import com.sky7th.deliveryfood.user.member.domain.Member;
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

  public static MemberResponseDto of(Member entity) {
    return new MemberResponseDto(entity.getId(), entity.getEmail(), entity.getUsername());
  }
}
