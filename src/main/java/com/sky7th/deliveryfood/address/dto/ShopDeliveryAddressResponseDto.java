package com.sky7th.deliveryfood.address.dto;

import com.sky7th.deliveryfood.address.domain.ShopDeliveryAddress;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDeliveryAddressResponseDto {

  private String townCode;
  private String townName;

  private ShopDeliveryAddressResponseDto() {
  }

  public static List<ShopDeliveryAddressResponseDto> of(Set<ShopDeliveryAddress> shopDeliveryAddresses) {
    return shopDeliveryAddresses.stream()
        .map(shopDeliveryAddress ->
            new ShopDeliveryAddressResponseDto(shopDeliveryAddress.getTownCode(), shopDeliveryAddress.getTownName()))
        .collect(Collectors.toList());
  }
}
