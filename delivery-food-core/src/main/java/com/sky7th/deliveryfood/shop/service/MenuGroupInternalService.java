package com.sky7th.deliveryfood.shop.service;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import com.sky7th.deliveryfood.shop.domain.MenuGroupRepository;
import com.sky7th.deliveryfood.shop.dto.MenuGroup.MenuGroupRequestDto;
import com.sky7th.deliveryfood.shop.exception.NotFoundMenuGroupException;
import com.sky7th.deliveryfood.shop.exception.NotFoundShopException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MenuGroupInternalService {

  private final MenuGroupRepository menuGroupRepository;

  @Transactional(readOnly = true)
  public MenuGroup findById(Long menuGroupId) {
    return menuGroupRepository.findById(menuGroupId).orElseThrow(NotFoundMenuGroupException::new);
  }

  @Transactional(readOnly = true)
  public List<MenuGroup> findAllByShopId(Long shopId) {
    return menuGroupRepository.findAllByShopId(shopId).orElseThrow(NotFoundShopException::new);
  }

  void saveRepresentative(Long shopId) {
    menuGroupRepository.save(MenuGroup.representative(shopId));
  }

  public MenuGroup save(Long shopId, MenuGroupRequestDto requestDto) {
    return menuGroupRepository.save(MenuGroupRequestDto.toEntity(shopId, requestDto));
  }

  public MenuGroup update(Long menuGroupId, MenuGroupRequestDto requestDto) {
    MenuGroup menuGroup = findById(menuGroupId);
    menuGroup.update(requestDto.getName(), requestDto.getDescription());

    return menuGroup;
  }

  public void delete(Long menuGroupId) {
    MenuGroup menuGroup = findById(menuGroupId);
    menuGroupRepository.delete(menuGroup);
  }
}
