package com.sky7th.deliveryfood.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class TownAddressRequestDto {

  private String townCode;
  private String townName;

  private TownAddressRequestDto() {
  }
}
