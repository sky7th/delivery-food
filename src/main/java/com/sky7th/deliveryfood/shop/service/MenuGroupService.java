package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import com.sky7th.deliveryfood.shop.domain.MenuGroupRepository;
import com.sky7th.deliveryfood.shop.dto.MenuGroupRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroupResponseDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroupResponseDtos;
import com.sky7th.deliveryfood.shop.exception.NotFoundMenuGroupException;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import com.sky7th.deliveryfood.user.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuGroupService {

  private final MenuGroupRepository menuGroupRepository;

  @Transactional(readOnly = true)
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

  @PreAuthorize("@shopService.isOwner(#shopId, #userContext)")
  public MenuGroupResponseDto save(Long shopId, MenuGroupRequestDto requestDto, UserContext userContext) {
    return MenuGroupResponseDto.of(menuGroupRepository.save(MenuGroupRequestDto.toEntity(shopId, requestDto)));
  }

  @PreAuthorize("@shopService.isOwner(#shopId, #userContext)")
  public MenuGroupResponseDto update(Long shopId, Long menuGroupId, MenuGroupRequestDto requestDto, UserContext userContext) {
    MenuGroup menuGroup = findById(menuGroupId);
    menuGroup.update(requestDto.getName(), requestDto.getDescription());

    return MenuGroupResponseDto.of(menuGroup);
  }

  @PreAuthorize("@shopService.isOwner(#shopId, #userContext)")
  public void delete(Long shopId, Long menuGroupId, UserContext userContext) {
    MenuGroup menuGroup = findById(menuGroupId);
    menuGroupRepository.delete(menuGroup);
  }
}
