package com.sky7th.deliveryfood.shop.controller;

import com.sky7th.deliveryfood.generic.address.dto.ShopDeliveryAddressRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopResponseDto;
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

  @PostMapping
  public ResponseEntity apply(@RequestBody ShopRequestDto requestDto, UserContext userContext) {
    shopService.save(requestDto, userContext);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{shopId}")
  public ResponseEntity<ShopResponseDto> updateDeliveryArea(@PathVariable Long shopId, @RequestBody ShopDeliveryAddressRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(shopService.updateDeliveryArea(shopId, requestDto, userContext));
  }
}
