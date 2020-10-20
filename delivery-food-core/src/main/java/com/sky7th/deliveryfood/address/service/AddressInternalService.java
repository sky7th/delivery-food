package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.AddressRepository;
import com.sky7th.deliveryfood.address.dto.address.AddressSearchRequestDto;
import com.sky7th.deliveryfood.address.exception.NotFoundAddressException;
import java.util.List;
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

  public List<Address> findBySearchRequest(AddressSearchRequestDto requestDto) {
    switch (requestDto.getSearchType()) {
      case town:
        return addressRepository.findByTownName(requestDto.getTownName(), requestDto.getBuildingNumber(), requestDto.getBuildingSideNumber());

      case road:
        return addressRepository.findByRoadName(requestDto.getRoadName(), requestDto.getBuildingNumber(), requestDto.getBuildingSideNumber());

      default:
        return addressRepository.findByBuildingName(requestDto.getBuildingName());
    }
  }
}
