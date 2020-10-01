package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.MemberAddress;
import com.sky7th.deliveryfood.address.domain.MemberAddressRepository;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.address.exception.NotFoundMemberAddressException;
import com.sky7th.deliveryfood.user.dto.UserContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberAddressInternalService {

  private final MemberAddressRepository memberAddressRepository;
  private final AddressInternalService addressInternalService;

  @Transactional(readOnly = true)
  public MemberAddress findById(Long memberAddressId) {
    return memberAddressRepository.findById(memberAddressId).orElseThrow(NotFoundMemberAddressException::new);
  }

  @Transactional(readOnly = true)
  public List<MemberAddress> findAllByMemberId(UserContext userContext) {
    return memberAddressRepository.findAllByMemberId(userContext.getId());
  }

  public MemberAddress save(MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    Address address = addressInternalService.findById(requestDto.getAddressId());
    MemberAddress memberAddress = new MemberAddress(userContext.getId(), address, requestDto.getDetailedAddress());

    return memberAddressRepository.save(memberAddress);
  }

  public MemberAddress update(Long memberAddressId, MemberAddressCreateRequestDto requestDto) {
    Address address = addressInternalService.findById(requestDto.getAddressId());
    MemberAddress memberAddress = findById(memberAddressId);
    memberAddress.update(address, requestDto.getDetailedAddress());

    return memberAddress;
  }

  public void delete(Long memberAddressId) {
    memberAddressRepository.delete(findById(memberAddressId));
  }
}
