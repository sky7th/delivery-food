package com.sky7th.deliveryfood.shop.controller;

import com.sky7th.deliveryfood.address.dto.shopdeliverytown.ShopDeliveryTownRequestDtos;
import com.sky7th.deliveryfood.address.dto.shopdeliverytown.ShopDeliveryTownResponseDtos;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopDetailResponseDto;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopResponseDto;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopResponseDtos;
import com.sky7th.deliveryfood.shop.service.ShopService;
import com.sky7th.deliveryfood.user.dto.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  public ResponseEntity<ShopDetailResponseDto> apply(@RequestBody ShopApplyRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(shopService.save(requestDto, userContext));
  }

  @GetMapping
  public ResponseEntity<ShopResponseDtos> list(UserContext userContext) {
    return ResponseEntity.ok(shopService.findAllIsNotDeletedByOwnerId(userContext));
  }

  @DeleteMapping("/{shopId}")
  public ResponseEntity<Void> delete(@PathVariable Long shopId, UserContext userContext) {
    shopService.delete(shopId, userContext);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{shopId}/close")
  public ResponseEntity<ShopResponseDto> close(@PathVariable Long shopId, UserContext userContext) {
    return ResponseEntity.ok(shopService.close(shopId, userContext));
  }

  @PatchMapping("/{shopId}/open")
  public ResponseEntity<ShopResponseDto> open(@PathVariable Long shopId, UserContext userContext) {
    return ResponseEntity.ok(shopService.open(shopId, userContext));
  }

  @GetMapping("/{shopId}")
  public ResponseEntity<ShopDetailResponseDto> show(@PathVariable Long shopId, UserContext userContext) {
    return ResponseEntity.ok(shopService.findById(shopId, userContext));
  }

  @PostMapping("/{shopId}/delivery-town")
  public ResponseEntity<ShopDeliveryTownResponseDtos> updateDeliveryTown(
      @PathVariable Long shopId, @RequestBody ShopDeliveryTownRequestDtos requestDto, UserContext userContext) {
    return ResponseEntity.ok(shopService.updateDeliveryTowns(shopId, requestDto, userContext));
  }
}
