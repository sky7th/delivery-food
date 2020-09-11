package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.address.domain.Address;
import com.sky7th.deliveryfood.address.domain.ShopDeliveryTown;
import com.sky7th.deliveryfood.address.dto.shopdeliverytown.ShopDeliveryTownRequestDtos;
import com.sky7th.deliveryfood.address.service.AddressInternalService;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import com.sky7th.deliveryfood.shop.dto.Shop.ShopApplyRequestDto;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import com.sky7th.deliveryfood.user.dto.UserContext;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopInternalService {

  private final ShopRepository shopRepository;
  private final AddressInternalService addressInternalService;
  private final MenuGroupInternalService menuGroupInternalService;

  @Transactional(readOnly = true)
  public Shop findById(Long shopId) {
    return shopRepository.findById(shopId).orElseThrow(NotFoundShopException::new);
  }

  @Transactional(readOnly = true)
  public List<Shop> findAllIsNotDeletedByOwnerId(UserContext userContext) {
    return shopRepository.findAllByOwnerIdAndStatusIsNot(userContext.getId(), ShopStatus.DELETED);
  }

  public Shop save(ShopApplyRequestDto requestDto, UserContext userContext) {
    Address address = addressInternalService.findByAddressCode(requestDto.getAddressCode());
    Shop savedShop = shopRepository.save(ShopApplyRequestDto.toEntity(requestDto, address, userContext.getId()));
    menuGroupInternalService.saveRepresentative(savedShop.getId());

    return savedShop;
  }

  public Set<ShopDeliveryTown> updateDeliveryTowns(Long shopId, ShopDeliveryTownRequestDtos requestDto) {
    Shop shop = findById(shopId);
    shop.updateDeliveryTowns(ShopDeliveryTownRequestDtos.toEntities(requestDto, shop));

    return shop.getShopDeliveryTowns();
  }

  public boolean isOwner(Long shopId, UserContext userContext) {
    Shop shop = findById(shopId);
    shop.checkOwner(userContext.getId());
    return true;
  }
}
