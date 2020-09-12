package com.sky7th.deliveryfood.shop.dto.Shop;

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
public class ShopResponseDto {

  private Long id;
  private String name;
  private String operatingTime;
  private ShopStatus status;
  private DeliveryType deliveryType;
  private Long minOrderAmount;
  private String introduction;
  private String guide;

  public static ShopResponseDto of(Shop entity) {
    return ShopResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .operatingTime(entity.getOperatingTime())
        .status(entity.getStatus())
        .deliveryType(entity.getDeliveryType())
        .minOrderAmount(entity.getMinOrderAmount().getAmount().longValue())
        .introduction(entity.getIntroduction())
        .guide(entity.getGuide())
        .build();
  }

  public static List<ShopResponseDto> of(List<Shop> entities) {
    return entities.stream()
        .map(ShopResponseDto::of)
        .collect(Collectors.toList());
  }
}
