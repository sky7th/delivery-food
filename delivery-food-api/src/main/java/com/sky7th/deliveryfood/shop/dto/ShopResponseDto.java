package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.address.dto.AddressResponseDto;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class ShopResponseDto {

  private Long id;
  private String name;
  private String operatingTime;
  private DeliveryType deliveryType;
  private Long minOrderAmount;
  private String introduction;
  private String guide;
  private AddressResponseDto address;

  public static ShopResponseDto of(Shop entity) {
    return ShopResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .operatingTime(entity.getOperatingTime())
        .deliveryType(entity.getDeliveryType())
        .minOrderAmount(entity.getMinOrderAmount().getAmount().longValue())
        .introduction(entity.getIntroduction())
        .guide(entity.getGuide())
        .address(AddressResponseDto.of(entity.getAddress()))
        .build();
  }
}
