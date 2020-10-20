package com.sky7th.deliveryfood.address.controller;

import com.sky7th.deliveryfood.address.dto.address.AddressSearchRequestDto;
import com.sky7th.deliveryfood.address.dto.address.AddressSearchResponseDto;
import com.sky7th.deliveryfood.address.service.AddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AddressController {

  private final AddressService addressService;

  @GetMapping("/addresses")
  public ResponseEntity<List<AddressSearchResponseDto>> list(AddressSearchRequestDto requestDto) {
    return ResponseEntity.ok(addressService.findBySearchRequest(requestDto));
  }
}
