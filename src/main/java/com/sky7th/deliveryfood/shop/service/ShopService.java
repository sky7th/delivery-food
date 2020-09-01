package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.generic.address.domain.Address;
import com.sky7th.deliveryfood.generic.address.service.AddressService;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import com.sky7th.deliveryfood.shop.dto.ShopRequestDto;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.owner.domain.Owner;
import com.sky7th.deliveryfood.user.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopService {

  private final ShopRepository shopRepository;
  private final OwnerService ownerService;
  private final AddressService addressService;

  public void save(ShopRequestDto requestDto, UserContext userContext) {
    Owner owner = ownerService.findById(userContext.getId());
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    shopRepository.save(ShopRequestDto.toEntity(requestDto, address, owner));
  }
}
