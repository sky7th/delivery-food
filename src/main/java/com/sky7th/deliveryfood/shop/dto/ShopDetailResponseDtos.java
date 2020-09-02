package com.sky7th.deliveryfood.shop.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDetailResponseDtos {

  private List<ShopDetailResponseDto> shops;
}
