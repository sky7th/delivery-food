package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.AddressRepository;
import com.sky7th.deliveryfood.address.exception.NotFoundAddressException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressInternalService {

  private final AddressRepository addressRepository;

  public Address findById(String addressId) {
    return addressRepository.findByBuildingManagementNumber(addressId)
        .orElseThrow(NotFoundAddressException::new);
  }
}
