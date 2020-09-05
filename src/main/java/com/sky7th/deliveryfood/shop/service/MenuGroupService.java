package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import com.sky7th.deliveryfood.shop.domain.MenuGroupRepository;
import com.sky7th.deliveryfood.shop.dto.MenuGroupResponseDtos;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuGroupService {

  private final MenuGroupRepository menuGroupRepository;

  @Transactional(readOnly = true)
  public MenuGroupResponseDtos findAllByShopId(Long shopId) {
    return MenuGroupResponseDtos.of(
        menuGroupRepository.findAllByShopId(shopId).orElseThrow(NotFoundShopException::new)
    );
  }

  void saveRepresentative(Long shopId) {
    menuGroupRepository.save(MenuGroup.representative(shopId));
  }
}
