package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.generic.address.domain.Address;
import com.sky7th.deliveryfood.generic.address.domain.ShopDeliveryAddress;
import com.sky7th.deliveryfood.generic.address.dto.ShopDeliveryAddressRequestDto;
import com.sky7th.deliveryfood.generic.address.service.AddressService;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import com.sky7th.deliveryfood.shop.dto.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDtos;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import com.sky7th.deliveryfood.user.UserContext;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopService {

  private final ShopRepository shopRepository;
  private final AddressService addressService;

  @Transactional(readOnly = true)
  public Shop findById(Long shopId) {
    return shopRepository.findById(shopId).orElseThrow(NotFoundShopException::new);
  }

  public ShopDetailResponseDto findById(Long shopId, UserContext userContext) {
    Shop shop = findById(shopId);
    shop.isSameOwner(userContext.getId());

    return ShopDetailResponseDto.of(shop);
  }

  public ShopDetailResponseDtos findAllByOwnerId(UserContext userContext) {
    List<ShopDetailResponseDto> shopDetailResponseDtos =
        ShopDetailResponseDto.of(shopRepository.findAllByOwnerId(userContext.getId()));
    return new ShopDetailResponseDtos(shopDetailResponseDtos);
  }

  public void save(ShopApplyRequestDto requestDto, UserContext userContext) {
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    shopRepository.save(ShopApplyRequestDto.toEntity(requestDto, address, userContext.getId()));
  }

  public ShopDetailResponseDto updateDeliveryArea(Long shopId, ShopDeliveryAddressRequestDto requestDto, UserContext userContext) {
    Shop shop = findById(shopId);
    Set<ShopDeliveryAddress> shopDeliveryAddresses = ShopDeliveryAddressRequestDto.toEntities(requestDto, shop);
    shop.updateDeliveryArea(shopDeliveryAddresses, userContext.getId());

    return ShopDetailResponseDto.of(shop);
  }
}
