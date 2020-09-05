package com.sky7th.deliveryfood.shop.controller;

import com.sky7th.deliveryfood.address.dto.ShopDeliveryTownRequestDtos;
import com.sky7th.deliveryfood.address.dto.ShopDeliveryTownResponseDtos;
import com.sky7th.deliveryfood.shop.dto.MenuGroupRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroupResponseDto;
import com.sky7th.deliveryfood.shop.dto.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDtos;
import com.sky7th.deliveryfood.shop.service.MenuGroupService;
import com.sky7th.deliveryfood.shop.service.ShopService;
import com.sky7th.deliveryfood.user.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final MenuGroupService menuGroupService;

  @PostMapping("/apply")
  public ResponseEntity apply(@RequestBody ShopApplyRequestDto requestDto, UserContext userContext) {
    shopService.save(requestDto, userContext);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<ShopDetailResponseDtos> list(UserContext userContext) {
    return ResponseEntity.ok(shopService.findMyShops(userContext));
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

  @PostMapping("/{shopId}/menu-groups")
  public ResponseEntity<MenuGroupResponseDto> createMenuGroup(
      @PathVariable Long shopId, @RequestBody MenuGroupRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(menuGroupService.save(shopId, requestDto));
  }
}
