package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.address.dto.AddressResponseDto;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.DeliveryType;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class ShopDetailResponseDto {

  private Long id;
  private String name;
  private ShopStatus status;
  private String operatingTime;
  private DeliveryType deliveryType;
  private Long minOrderAmount;
  private String businessNumber;
  private String introduction;
  private String guide;
  private AddressResponseDto address;

  public static ShopDetailResponseDto of(Shop entity) {
    return ShopDetailResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .operatingTime(entity.getOperatingTime())
        .deliveryType(entity.getDeliveryType())
        .minOrderAmount(entity.getMinOrderAmount().getAmount().longValue())
        .businessNumber(entity.getBusinessNumber())
        .introduction(entity.getIntroduction())
        .guide(entity.getGuide())
        .status(entity.getStatus())
        .address(AddressResponseDto.of(entity.getAddress()))
        .build();
  }

  public static List<ShopDetailResponseDto> of(List<Shop> entities) {
    return entities.stream()
        .map(ShopDetailResponseDto::of)
        .collect(Collectors.toList());
  }
}
