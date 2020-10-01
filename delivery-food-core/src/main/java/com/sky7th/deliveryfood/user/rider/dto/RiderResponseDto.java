package com.sky7th.deliveryfood.user.rider.dto;

import com.sky7th.deliveryfood.user.rider.domain.Rider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RiderResponseDto {

  private Long id;
  private String email;
  private String username;

  public static RiderResponseDto of(Rider entity) {
    return new RiderResponseDto(entity.getId(), entity.getEmail(), entity.getUsername());
  }
}
