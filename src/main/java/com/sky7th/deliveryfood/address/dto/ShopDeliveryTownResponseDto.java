package com.sky7th.deliveryfood.address.dto;

import com.sky7th.deliveryfood.address.domain.ShopDeliveryTown;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDeliveryTownResponseDto {

  private String townCode;
  private String townName;

  private ShopDeliveryTownResponseDto() {
  }

  public static List<ShopDeliveryTownResponseDto> of(Set<ShopDeliveryTown> shopDeliveryTowns) {
    return shopDeliveryTowns.stream()
        .map(shopDeliveryTown ->
            new ShopDeliveryTownResponseDto(shopDeliveryTown.getTownCode(), shopDeliveryTown.getTownName()))
        .collect(Collectors.toList());
  }
}
