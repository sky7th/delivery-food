package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.ShopDeliveryAddress;
import com.sky7th.deliveryfood.shop.domain.Shop;
import java.util.List;
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

  public static ShopDeliveryAddress toEntity(String townCodes, Shop shop) {
    return new ShopDeliveryAddress(shop, townCodes);
  }
}
