package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.dto.MenuGroup.MenuGroupRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroup.MenuGroupResponseDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroup.MenuGroupResponseDtos;
import com.sky7th.deliveryfood.user.dto.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuGroupService {

  private final MenuGroupInternalService menuGroupInternalService;

  public MenuGroupResponseDtos findAllByShopId(Long shopId) {
    return MenuGroupResponseDtos.of(menuGroupInternalService.findAllByShopId(shopId));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public MenuGroupResponseDto save(Long shopId, MenuGroupRequestDto requestDto, UserContext userContext) {
    return MenuGroupResponseDto.of(menuGroupInternalService.save(shopId, requestDto));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public MenuGroupResponseDto update(Long shopId, Long menuGroupId, MenuGroupRequestDto requestDto, UserContext userContext) {
    return MenuGroupResponseDto.of(menuGroupInternalService.update(menuGroupId, requestDto));
  }

  @PreAuthorize("@shopInternalService.isOwner(#shopId, #userContext)")
  public void delete(Long shopId, Long menuGroupId, UserContext userContext) {
    menuGroupInternalService.delete(menuGroupId);
  }
}
