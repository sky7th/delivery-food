package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.generic.address.domain.Address;
import com.sky7th.deliveryfood.generic.address.domain.ShopDeliveryAddress;
import com.sky7th.deliveryfood.generic.address.dto.ShopDeliveryAddressRequestDto;
import com.sky7th.deliveryfood.generic.address.service.AddressService;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import com.sky7th.deliveryfood.shop.dto.ShopRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopResponseDto;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.owner.domain.Owner;
import com.sky7th.deliveryfood.user.owner.service.OwnerService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShopService {

  private final ShopRepository shopRepository;
  private final OwnerService ownerService;
  private final AddressService addressService;

  public Shop findById(Long shopId) {
    return shopRepository.findById(shopId).orElseThrow(NotFoundShopException::new);
  }

  public void save(ShopRequestDto requestDto, UserContext userContext) {
    Owner owner = ownerService.findById(userContext.getId());
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    shopRepository.save(ShopRequestDto.toEntity(requestDto, address, owner));
  }

  public ShopResponseDto updateDeliveryArea(Long shopId, ShopDeliveryAddressRequestDto requestDto, UserContext userContext) {
    Shop shop = findById(shopId);
    Set<ShopDeliveryAddress> shopDeliveryAddresses = requestDto.getTownCodes().stream()
        .map(townCode -> ShopDeliveryAddressRequestDto.toEntity(townCode, shop))
        .collect(Collectors.toSet());
    shop.updateDeliveryArea(shopDeliveryAddresses, userContext);

    return ShopResponseDto.of(shop);
  }
}
