package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.ShopDeliveryAddress;
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

  private List<String> townCodes;

  private ShopDeliveryAddressRequestDto() {
  }

  public static Set<ShopDeliveryAddress> toEntities(ShopDeliveryAddressRequestDto requestDto, Shop shop) {
    return requestDto.getTownCodes().stream()
        .map(townCode -> new ShopDeliveryAddress(shop, townCode))
        .collect(Collectors.toSet());
  }
}
