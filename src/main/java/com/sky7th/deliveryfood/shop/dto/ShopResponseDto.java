package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.generic.address.dto.AddressResponseDto;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.DeliveryType;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class ShopResponseDto {

  private String name;
  private ShopStatus status;
  private String operatingTime;
  private DeliveryType deliveryType;
  private Long minOrderAmount;
  private String businessNumber;
  private String introduction;
  private String guide;
  private String addressCode;
  private AddressResponseDto addressResponseDto;

  public static ShopResponseDto of(Shop entity) {
    return ShopResponseDto.builder()
        .name(entity.getName())
        .operatingTime(entity.getOperatingTime())
        .deliveryType(entity.getDeliveryType())
        .minOrderAmount(entity.getMinOrderAmount().getAmount().longValue())
        .businessNumber(entity.getBusinessNumber())
        .introduction(entity.getIntroduction())
        .guide(entity.getGuide())
        .status(entity.getStatus())
        .addressResponseDto(AddressResponseDto.of(entity.getAddress()))
        .build();
  }
}
