package com.sky7th.deliveryfood.address.controller;

import com.sky7th.deliveryfood.address.dto.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.address.dto.MemberAddressResponseDto;
import com.sky7th.deliveryfood.address.dto.MemberAddressResponseDtos;
import com.sky7th.deliveryfood.address.service.MemberAddressService;
import com.sky7th.deliveryfood.user.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class AddressController {

  private final MemberAddressService memberAddressService;

  @GetMapping("/address")
  public ResponseEntity<MemberAddressResponseDtos> list(UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.findMyAddresses(userContext));
  }

  @PostMapping("/address")
  public ResponseEntity<MemberAddressResponseDto> createMemberAddress(
      @RequestBody MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.save(requestDto, userContext));
  }

  @PutMapping("/address/{memberAddressId}")
  public ResponseEntity<MemberAddressResponseDto> updateMemberAddress(@PathVariable Long memberAddressId,
      @RequestBody MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.update(memberAddressId, requestDto, userContext));
  }

  @DeleteMapping("/address/{memberAddressId}")
  public ResponseEntity<MemberAddressResponseDto> deleteMemberAddress(
      @PathVariable Long memberAddressId, UserContext userContext) {
    memberAddressService.delete(memberAddressId, userContext);
    return ResponseEntity.ok().build();
  }
}
