package com.sky7th.deliveryfood.address.dto.shopdeliverytown;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDeliveryTownRequestDto {

  private String townCode;
  private String townName;

  private ShopDeliveryTownRequestDto() {
  }
}
