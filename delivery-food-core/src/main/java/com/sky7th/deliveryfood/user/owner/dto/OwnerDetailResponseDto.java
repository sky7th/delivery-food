package com.sky7th.deliveryfood.user.owner.dto;

import com.sky7th.deliveryfood.user.owner.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class OwnerDetailResponseDto {

  private Long id;
  private String email;
  private String username;

  public static OwnerDetailResponseDto of(Owner entity) {
    return new OwnerDetailResponseDto(entity.getId(), entity.getEmail(), entity.getUsername());
  }
}
