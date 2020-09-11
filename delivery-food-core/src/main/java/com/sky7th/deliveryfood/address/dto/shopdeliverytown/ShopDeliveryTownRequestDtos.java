package com.sky7th.deliveryfood.address.dto.shopdeliverytown;

import com.sky7th.deliveryfood.address.domain.ShopDeliveryTown;
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
public class ShopDeliveryTownRequestDtos {

  private List<ShopDeliveryTownRequestDto> shopDeliveryTowns;

  private ShopDeliveryTownRequestDtos() {
  }

  public static Set<ShopDeliveryTown> toEntities(ShopDeliveryTownRequestDtos requestDtos, Shop shop) {
    return requestDtos.getShopDeliveryTowns().stream()
        .map(town -> new ShopDeliveryTown(shop, town.getTownCode(), town.getTownName()))
        .collect(Collectors.toSet());
  }
}
