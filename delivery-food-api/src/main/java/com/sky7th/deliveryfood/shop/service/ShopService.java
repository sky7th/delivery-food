package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.address.dto.shopdeliverytown.ShopDeliveryTownRequestDtos;
import com.sky7th.deliveryfood.address.dto.shopdeliverytown.ShopDeliveryTownResponseDtos;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopDetailResponseDto;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopResponseDto;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopResponseDtos;
import com.sky7th.deliveryfood.user.dto.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopService {

  private final ShopInternalService shopInternalService;

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public ShopDetailResponseDto findById(Long shopId, UserContext userContext) {
    return ShopDetailResponseDto.of(shopInternalService.findById(shopId));
  }

  public ShopResponseDtos findAllIsNotDeletedByOwnerId(UserContext userContext) {
    return ShopResponseDtos.of(shopInternalService.findAllIsNotDeletedByOwnerId(userContext));
  }

  public ShopDetailResponseDto save(ShopApplyRequestDto requestDto, UserContext userContext) {
    return ShopDetailResponseDto.of(shopInternalService.save(requestDto, userContext));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public ShopDeliveryTownResponseDtos updateDeliveryTowns(Long shopId, ShopDeliveryTownRequestDtos requestDto, UserContext userContext) {
    return ShopDeliveryTownResponseDtos.of(shopInternalService.updateDeliveryTowns(shopId, requestDto));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public void delete(Long shopId, UserContext userContext) {
    shopInternalService.delete(shopId);
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public ShopResponseDto close(Long shopId, UserContext userContext) {
    return ShopResponseDto.of(shopInternalService.close(shopId));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public ShopResponseDto open(Long shopId, UserContext userContext) {
    return ShopResponseDto.of(shopInternalService.open(shopId));
  }
}
