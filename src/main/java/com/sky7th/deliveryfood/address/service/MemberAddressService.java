package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.MemberAddress;
import com.sky7th.deliveryfood.address.domain.MemberAddressRepository;
import com.sky7th.deliveryfood.address.dto.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.address.dto.MemberAddressResponseDto;
import com.sky7th.deliveryfood.address.dto.MemberAddressResponseDtos;
import com.sky7th.deliveryfood.address.service.exception.NotFoundMemberAddressException;
import com.sky7th.deliveryfood.user.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberAddressService {

  private final MemberAddressRepository memberAddressRepository;
  private final AddressService addressService;

  @Transactional(readOnly = true)
  public MemberAddress findById(Long memberAddressId) {
    return memberAddressRepository.findById(memberAddressId).orElseThrow(NotFoundMemberAddressException::new);
  }

  @PreAuthorize("@memberService.isMyself(#memberId, #userContext)")
  public MemberAddressResponseDtos findMyAddresses(Long memberId, UserContext userContext) {
    return MemberAddressResponseDtos.of(memberAddressRepository.findAllByMemberId(userContext.getId()));
  }

  @PreAuthorize("@memberService.isMyself(#memberId, #userContext)")
  public MemberAddressResponseDto save(Long memberId, MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    MemberAddress memberAddress = memberAddressRepository.save(new MemberAddress(userContext.getId(), address, requestDto.getDetailedAddress()));

    return MemberAddressResponseDto.of(memberAddress);
  }

  @PreAuthorize("@memberService.isMyself(#memberId, #userContext)")
  public MemberAddressResponseDto update(Long memberId, Long memberAddressId, MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    MemberAddress memberAddress = findById(memberAddressId);
    memberAddress.update(address, requestDto.getDetailedAddress());

    return MemberAddressResponseDto.of(memberAddress);
  }

  @PreAuthorize("@memberService.isMyself(#memberId, #userContext)")
  public void delete(Long memberId, Long memberAddressId, UserContext userContext) {
    memberAddressRepository.delete(findById(memberAddressId));
  }
}
