package com.sky7th.deliveryfood.shop.dto.Shop;

import com.sky7th.deliveryfood.shop.domain.Shop;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class ShopResponseDtos {

  private List<ShopResponseDto> shops;

  public static ShopResponseDtos of(List<Shop> entities) {
    return new ShopResponseDtos(ShopResponseDto.of(entities));
  }
}
