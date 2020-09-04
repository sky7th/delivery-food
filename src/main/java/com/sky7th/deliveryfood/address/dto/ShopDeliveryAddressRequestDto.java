package com.sky7th.deliveryfood.address.dto;

import com.sky7th.deliveryfood.address.domain.ShopDeliveryAddress;
import com.sky7th.deliveryfood.shop.domain.Shop;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDeliveryAddressRequestDto {

  private List<TownAddressRequestDto> deliveryAreaTowns;

  private ShopDeliveryAddressRequestDto() {
  }

  public static Set<ShopDeliveryAddress> toEntities(ShopDeliveryAddressRequestDto requestDto, Shop shop) {
    return requestDto.getDeliveryAreaTowns().stream()
        .map(town -> new ShopDeliveryAddress(shop, town.getTownCode(), town.getTownName()))
        .collect(Collectors.toSet());
  }
}
