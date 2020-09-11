package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.dto.Menu.MenuRequestDto;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuResponseDto;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuUpdateRequestDto;
import com.sky7th.deliveryfood.user.dto.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {

  private final MenuInternalService menuInternalService;

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public MenuResponseDto save(Long shopId, Long menuGroupId, MenuRequestDto requestDto, UserContext userContext) {
    return MenuResponseDto.of(menuInternalService.save(menuGroupId, requestDto));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public MenuResponseDto update(Long shopId, Long menuId, MenuUpdateRequestDto requestDto, UserContext userContext) {
    return MenuResponseDto.of(menuInternalService.update(menuId, requestDto));
  }
}
