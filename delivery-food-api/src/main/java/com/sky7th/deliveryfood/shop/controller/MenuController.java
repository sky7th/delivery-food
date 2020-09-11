package com.sky7th.deliveryfood.shop.controller;

import com.sky7th.deliveryfood.shop.dto.Menu.MenuRequestDto;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuResponseDto;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuUpdateRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroup.MenuGroupRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuGroup.MenuGroupResponseDto;
import com.sky7th.deliveryfood.shop.service.MenuGroupService;
import com.sky7th.deliveryfood.shop.service.MenuService;
import com.sky7th.deliveryfood.user.dto.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/owners/shops")
@RequiredArgsConstructor
@RestController
public class MenuController {

  private final MenuGroupService menuGroupService;
  private final MenuService menuService;

  @PostMapping("/{shopId}/menu-groups")
  public ResponseEntity<MenuGroupResponseDto> createMenuGroup(
      @PathVariable Long shopId, @RequestBody MenuGroupRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(menuGroupService.save(shopId, requestDto, userContext));
  }

  @PutMapping("/{shopId}/menu-groups/{menuGroupId}")
  public ResponseEntity<MenuGroupResponseDto> updateMenuGroup(@PathVariable Long shopId,
      @PathVariable Long menuGroupId, @RequestBody MenuGroupRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(menuGroupService.update(shopId, menuGroupId, requestDto, userContext));
  }

  @DeleteMapping("/{shopId}/menu-groups/{menuGroupId}")
  public ResponseEntity<MenuGroupResponseDto> deleteMenuGroup(@PathVariable Long shopId,
      @PathVariable Long menuGroupId, UserContext userContext) {
    menuGroupService.delete(shopId, menuGroupId, userContext);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{shopId}/menu-groups/{menuGroupId}/menus")
  public ResponseEntity<MenuResponseDto> createMenu(@PathVariable Long shopId,
      @PathVariable Long menuGroupId, @RequestBody MenuRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(menuService.save(shopId, menuGroupId, requestDto, userContext));
  }

  @PutMapping("/{shopId}/menus/{menuId}")
  public ResponseEntity<MenuResponseDto> updateMenu(@PathVariable Long shopId,
      @PathVariable Long menuId, @RequestBody MenuUpdateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(menuService.update(shopId, menuId, requestDto, userContext));
  }
}
