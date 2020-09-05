package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import com.sky7th.deliveryfood.shop.domain.MenuGroupRepository;
import com.sky7th.deliveryfood.shop.dto.MenuGroupRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroupResponseDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroupResponseDtos;
import com.sky7th.deliveryfood.shop.exception.NotFoundMenuGroupException;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuGroupService {

  private final MenuGroupRepository menuGroupRepository;

  public MenuGroup findById(Long menuGroupId) {
    return menuGroupRepository.findById(menuGroupId).orElseThrow(NotFoundMenuGroupException::new);
  }

  @Transactional(readOnly = true)
  public MenuGroupResponseDtos findAllByShopId(Long shopId) {
    return MenuGroupResponseDtos.of(
        menuGroupRepository.findAllByShopId(shopId).orElseThrow(NotFoundShopException::new)
    );
  }

  void saveRepresentative(Long shopId) {
    menuGroupRepository.save(MenuGroup.representative(shopId));
  }

  public MenuGroupResponseDto save(Long shopId, MenuGroupRequestDto requestDto) {
    return MenuGroupResponseDto.of(menuGroupRepository.save(MenuGroupRequestDto.toEntity(shopId, requestDto)));
  }

  public MenuGroupResponseDto update(Long menuGroupId, MenuGroupRequestDto requestDto) {
    MenuGroup menuGroup = findById(menuGroupId);
    menuGroup.update(requestDto.getName(), requestDto.getDescription());

    return MenuGroupResponseDto.of(menuGroup);
  }

  public void delete(Long menuGroupId) {
    MenuGroup menuGroup = findById(menuGroupId);
    menuGroupRepository.delete(menuGroup);
  }
}
