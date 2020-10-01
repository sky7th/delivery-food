package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.MenuRepository;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuRequestDto;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuUpdateRequestDto;
import com.sky7th.deliveryfood.shop.dto.Option.OptionUpdateRequestDto;
import com.sky7th.deliveryfood.shop.exception.NotFoundMenuException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuInternalService {

  private final MenuRepository menuRepository;

  @Transactional(readOnly = true)
  public Menu findById(Long menuId) {
    return menuRepository.findById(menuId).orElseThrow(NotFoundMenuException::new);
  }

  public Menu save(Long menuGroupId, MenuRequestDto requestDto) {
    return menuRepository.save(MenuRequestDto.toEntity(menuGroupId, requestDto));
  }

  public Menu update(Long menuId, MenuUpdateRequestDto requestDto) {
    Menu menu = findById(menuId);
    menu.update(requestDto.getName(), requestDto.getDescription(), requestDto.getImageUrl(),
        OptionUpdateRequestDto.toEntities(requestDto.getBasicOptions()));

    return menu;
  }
}
