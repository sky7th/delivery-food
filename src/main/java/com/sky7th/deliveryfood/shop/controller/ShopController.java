package com.sky7th.deliveryfood.shop.controller;

import com.sky7th.deliveryfood.generic.address.dto.ShopDeliveryAddressRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDto;
import com.sky7th.deliveryfood.shop.service.ShopService;
import com.sky7th.deliveryfood.user.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/owners/shops")
@RequiredArgsConstructor
@RestController
public class ShopController {

  private final ShopService shopService;

  @PostMapping("/apply")
  public ResponseEntity apply(@RequestBody ShopApplyRequestDto requestDto, UserContext userContext) {
    shopService.save(requestDto, userContext);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{shopId}/deliveryArea")
  public ResponseEntity<ShopDetailResponseDto> updateDeliveryArea(
      @PathVariable Long shopId, @RequestBody ShopDeliveryAddressRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(shopService.updateDeliveryArea(shopId, requestDto, userContext));
  }
}
