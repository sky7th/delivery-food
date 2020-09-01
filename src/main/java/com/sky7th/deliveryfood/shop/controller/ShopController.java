package com.sky7th.deliveryfood.shop.controller;

import com.sky7th.deliveryfood.shop.dto.ShopRequestDto;
import com.sky7th.deliveryfood.shop.service.ShopService;
import com.sky7th.deliveryfood.user.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
