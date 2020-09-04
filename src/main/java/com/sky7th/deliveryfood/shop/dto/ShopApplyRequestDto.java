package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.DeliveryType;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopApplyRequestDto {

  @NotBlank
  private String name;
  private String operatingTime;
  @NotBlank
  private DeliveryType deliveryType;
  @NotBlank
  private Long minOrderAmount;
  @NotBlank
  private String businessNumber;
  private String introduction;
  @NotBlank
  private String addressCode;
  @NotBlank
  private String telNumber;

  public static Shop toEntity(ShopApplyRequestDto requestDto, Address address, Long ownerId) {
    return Shop.builder()
        .name(requestDto.getName())
        .operatingTime(requestDto.getOperatingTime())
        .deliveryType(requestDto.getDeliveryType())
        .minOrderAmount(Money.wons(requestDto.getMinOrderAmount()))
        .businessNumber(requestDto.getBusinessNumber())
        .introduction(requestDto.getIntroduction())
        .status(ShopStatus.NOT_APPROVAL)
        .ownerId(ownerId)
        .address(address)
        .telNumber(requestDto.getTelNumber())
        .build();
  }

  private ShopApplyRequestDto() {
  }
}
