package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.generic.address.domain.Address;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.DeliveryType;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import com.sky7th.deliveryfood.user.owner.domain.Owner;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ShopRequestDto {

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
  private String guide;
  @NotBlank
  private String addressCode;

  public static Shop toEntity(ShopRequestDto requestDto, Address address, Owner owner) {
    return Shop.builder()
        .name(requestDto.getName())
        .operatingTime(requestDto.getOperatingTime())
        .deliveryType(requestDto.getDeliveryType())
        .minOrderAmount(Money.wons(requestDto.getMinOrderAmount()))
        .businessNumber(requestDto.getBusinessNumber())
        .introduction(requestDto.getIntroduction())
        .guide(requestDto.getGuide())
        .status(ShopStatus.NOT_APPROVAL)
        .ownerId(owner.getId())
        .address(address)
        .build();
  }

  private ShopRequestDto() {
  }
}
