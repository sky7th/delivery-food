package com.sky7th.deliveryfood.address.dto;

import com.sky7th.deliveryfood.address.domain.ShopDeliveryTown;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopDeliveryTownResponseDtos {

  private List<ShopDeliveryTownResponseDto> shopDeliveryTowns;

  private ShopDeliveryTownResponseDtos() {
  }

  public static ShopDeliveryTownResponseDtos of(Set<ShopDeliveryTown> entities) {
    return new ShopDeliveryTownResponseDtos(ShopDeliveryTownResponseDto.of(entities));
  }
}
