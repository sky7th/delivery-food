package com.sky7th.deliveryfood.generic.address.service;

import com.sky7th.deliveryfood.generic.address.domain.Address;
import com.sky7th.deliveryfood.generic.address.domain.MemberAddress;
import com.sky7th.deliveryfood.generic.address.domain.MemberAddressRepository;
import com.sky7th.deliveryfood.generic.address.service.exception.NotFoundMemberAddressException;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.dto.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberAddressService {

  private final MemberAddressRepository memberAddressRepository;
  private final AddressService addressService;
  private final MemberService memberService;

  public MemberAddress findById(Long memberAddressId) {
    return memberAddressRepository.findById(memberAddressId).orElseThrow(NotFoundMemberAddressException::new);
  }

  public MemberAddress save(MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    Member member = memberService.findById(userContext.getId());
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());

    return memberAddressRepository.save(new MemberAddress(member, address, requestDto.getDetailedAddress()));
  }

  public void update(Long memberAddressId, MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    Member member = memberService.findById(userContext.getId());
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    MemberAddress memberAddress = findById(memberAddressId);

    memberAddress.update(member, address, requestDto.getDetailedAddress());
  }
}
