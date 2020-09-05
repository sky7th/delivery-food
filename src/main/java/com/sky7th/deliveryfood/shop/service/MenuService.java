package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.MenuRepository;
import com.sky7th.deliveryfood.shop.dto.MenuRequestDto;
import com.sky7th.deliveryfood.shop.dto.MenuResponseDto;
import com.sky7th.deliveryfood.shop.exception.NotFoundMenuException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuService {

  private final MenuRepository menuRepository;

  @Transactional(readOnly = true)
  public Menu findById(Long menuGroupId) {
    return menuRepository.findById(menuGroupId).orElseThrow(NotFoundMenuException::new);
  }

  public MenuResponseDto save(Long menuGroupId, MenuRequestDto requestDto) {
    return MenuResponseDto.of(menuRepository.save(MenuRequestDto.toEntity(menuGroupId, requestDto)));
  }
}
