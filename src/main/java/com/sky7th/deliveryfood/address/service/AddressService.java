package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.AddressRepository;
import com.sky7th.deliveryfood.address.service.exception.NotFoundAddressException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

  private final AddressRepository memberAddressRepository;

  public Address findByAddressCode(String addressId) {
    return memberAddressRepository.findByBuildingManagementNumber(addressId)
        .orElseThrow(NotFoundAddressException::new);
  }
}
