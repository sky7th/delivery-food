package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.ShopDeliveryTown;
import com.sky7th.deliveryfood.address.dto.ShopDeliveryTownRequestDtos;
import com.sky7th.deliveryfood.address.dto.ShopDeliveryTownResponseDtos;
import com.sky7th.deliveryfood.address.service.AddressService;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import com.sky7th.deliveryfood.shop.dto.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDto;
import com.sky7th.deliveryfood.shop.dto.ShopDetailResponseDtos;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import com.sky7th.deliveryfood.user.UserContext;
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
  private final MenuGroupService menuGroupService;

  @Transactional(readOnly = true)
  public Shop findById(Long shopId) {
    return shopRepository.findById(shopId).orElseThrow(NotFoundShopException::new);
  }

  public ShopDetailResponseDto findById(Long shopId, UserContext userContext) {
    Shop shop = findById(shopId);
    shop.same(userContext.getId());

    return ShopDetailResponseDto.of(shop);
  }

  public ShopDetailResponseDtos findMyShops(UserContext userContext) {
    return ShopDetailResponseDtos.of(shopRepository.findAllByOwnerIdAndStatus(userContext.getId(), ShopStatus.ACTIVE));
  }

  public void save(ShopApplyRequestDto requestDto, UserContext userContext) {
    Address address = addressService.findByAddressCode(requestDto.getAddressCode());
    Shop savedShop = shopRepository.save(ShopApplyRequestDto.toEntity(requestDto, address, userContext.getId()));
    menuGroupService.saveRepresentative(savedShop.getId());
  }

  public ShopDeliveryTownResponseDtos updateDeliveryTowns(Long shopId, ShopDeliveryTownRequestDtos requestDto, UserContext userContext) {
    Shop shop = findById(shopId);
    Set<ShopDeliveryTown> shopDeliveryTowns = ShopDeliveryTownRequestDtos
        .toEntities(requestDto, shop);
    shop.updateDeliveryTowns(shopDeliveryTowns, userContext.getId());

    return ShopDeliveryTownResponseDtos.of(shop.getShopDeliveryTowns());
  }
}
