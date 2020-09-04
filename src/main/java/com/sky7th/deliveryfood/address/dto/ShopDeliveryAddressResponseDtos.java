package com.sky7th.deliveryfood.address.dto;

import com.sky7th.deliveryfood.address.domain.ShopDeliveryAddress;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDeliveryAddressResponseDtos {

  private List<ShopDeliveryAddressResponseDto> shopDeliveryAddresses;

  private ShopDeliveryAddressResponseDtos() {
  }

  public static ShopDeliveryAddressResponseDtos of(Set<ShopDeliveryAddress> entities) {
    return new ShopDeliveryAddressResponseDtos(ShopDeliveryAddressResponseDto.of(entities));
  }
}
