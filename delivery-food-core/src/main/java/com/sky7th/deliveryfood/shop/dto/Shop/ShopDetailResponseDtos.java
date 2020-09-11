package com.sky7th.deliveryfood.shop.dto.Shop;

import com.sky7th.deliveryfood.shop.domain.Shop;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDetailResponseDtos {

  private List<ShopDetailResponseDto> shops;

  public static ShopDetailResponseDtos of(List<Shop> entities) {
    return new ShopDetailResponseDtos(ShopDetailResponseDto.of(entities));
  }
}
