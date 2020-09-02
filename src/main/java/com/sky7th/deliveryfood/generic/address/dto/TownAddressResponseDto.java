package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.ShopDeliveryAddress;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class TownAddressResponseDto {

  private String townCode;
  private String townName;

  private TownAddressResponseDto() {
  }

  public static List<TownAddressResponseDto> of(Set<ShopDeliveryAddress> shopDeliveryAddresses) {
    return shopDeliveryAddresses.stream()
        .map(shopDeliveryAddress ->
            new TownAddressResponseDto(shopDeliveryAddress.getTownCode(), shopDeliveryAddress.getTownName()))
        .collect(Collectors.toList());
  }
}
