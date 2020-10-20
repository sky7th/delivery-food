package com.sky7th.deliveryfood.address.service;

import com.sky7th.deliveryfood.address.dto.address.AddressSearchRequestDto;
import com.sky7th.deliveryfood.address.dto.address.AddressSearchResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

  private final AddressInternalService addressInternalService;

  public List<AddressSearchResponseDto> findBySearchRequest(AddressSearchRequestDto requestDto) {
    return AddressSearchResponseDto.of(addressInternalService.findBySearchRequest(requestDto));
  }
}
