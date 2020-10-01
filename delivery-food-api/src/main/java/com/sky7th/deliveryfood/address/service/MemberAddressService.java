package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressResponseDto;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressResponseDtos;
import com.sky7th.deliveryfood.user.dto.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberAddressService {

  private final MemberAddressInternalService memberAddressInternalService;

  @PreAuthorize("@memberInternalService.isMyself(#memberId, #userContext)")
  public MemberAddressResponseDtos findAllByMemberId(Long memberId, UserContext userContext) {
    return MemberAddressResponseDtos.of(memberAddressInternalService.findAllByMemberId(userContext));
  }

  @PreAuthorize("@memberInternalService.isMyself(#memberId, #userContext)")
  public MemberAddressResponseDto save(Long memberId, MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return MemberAddressResponseDto.of(memberAddressInternalService.save(requestDto, userContext));
  }

  @PreAuthorize("@memberInternalService.isMyself(#memberId, #userContext)")
  public MemberAddressResponseDto update(Long memberId, Long memberAddressId, MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return MemberAddressResponseDto.of(memberAddressInternalService.update(memberAddressId, requestDto));
  }

  @PreAuthorize("@memberInternalService.isMyself(#memberId, #userContext)")
  public void delete(Long memberId, Long memberAddressId, UserContext userContext) {
    memberAddressInternalService.delete(memberAddressId);
  }
}
