package com.sky7th.deliveryfood.address.controller;

import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressResponseDto;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressResponseDtos;
import com.sky7th.deliveryfood.address.service.MemberAddressService;
import com.sky7th.deliveryfood.user.dto.UserContext;
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
public class MemberAddressController {

  private final MemberAddressService memberAddressService;

  @GetMapping("/members/{memberId}/address")
  public ResponseEntity<MemberAddressResponseDtos> list(@PathVariable Long memberId, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.findAllByMemberId(memberId, userContext));
  }

  @PostMapping("/members/{memberId}/address")
  public ResponseEntity<MemberAddressResponseDto> create(@PathVariable Long memberId,
      @RequestBody MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.save(memberId, requestDto, userContext));
  }

  @PutMapping("/members/{memberId}/address/{memberAddressId}")
  public ResponseEntity<MemberAddressResponseDto> update(@PathVariable Long memberId,
      @PathVariable Long memberAddressId, @RequestBody MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.update(memberId, memberAddressId, requestDto, userContext));
  }

  @DeleteMapping("/members/{memberId}/address/{memberAddressId}")
  public ResponseEntity<Void> delete(@PathVariable Long memberId,
      @PathVariable Long memberAddressId, UserContext userContext) {
    memberAddressService.delete(memberId, memberAddressId, userContext);
    return ResponseEntity.ok().build();
  }
}
