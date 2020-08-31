package com.sky7th.deliveryfood.user.owner.dto;

import com.sky7th.deliveryfood.user.owner.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class OwnerResponseDto {

  private Long id;
  private String email;
  private String username;

  public static OwnerResponseDto of(Owner entity) {
    return new OwnerResponseDto(entity.getId(), entity.getEmail(), entity.getUsername());
  }
}
