package com.sky7th.deliveryfood.order.domain;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.MenuOptionGroup;
import com.sky7th.deliveryfood.shop.domain.MenuRepository;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.DeliveryType;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderValidator {

  private final ShopRepository shopRepository;
  private final MenuRepository menuRepository;

  public void validate(Order order) {
    validate(order, getShop(order), getMenus(order));
  }

  public void validate(Order order, Shop shop, Map<Long, Menu> menus) {
    if (shop.getStatus() == ShopStatus.INACTIVE) {
      throw new IllegalArgumentException("가게가 영업중이 아닙니다.");
    }

    if (shop.getDeliveryType() == DeliveryType.NON_DELIVERABLE) {
      throw new IllegalArgumentException("현재 배달이 불가능한 가게입니다.");
    }

    if (shop.getStatus() == ShopStatus.DELETED) {
      throw new IllegalArgumentException("삭제된 가게입니다.");
    }

    if (order.getOrderMenuItems().isEmpty()) {
      throw new IllegalStateException("주문 항목이 비어 있습니다.");
    }

    if (!shop.isValidOrderAmount(order.calculateTotalPrice())) {
      throw new IllegalStateException(
          String.format("최소 주문 금액은 %s 입니다.", shop.getMinOrderAmount().toString()));
    }

    for (OrderMenuItem item : order.getOrderMenuItems()) {
      validateOrderMenu(item, menus.get(item.getMenuId()));
    }
  }

  private void validateOrderMenu(OrderMenuItem item, Menu menu) {
    if (!menu.getName().equals(item.getName())) {
      throw new IllegalArgumentException("메뉴가 변경됐습니다.");
    }

    for (OrderOptionGroup group : item.getOrderOptionGroups()) {
      validateOrderOptionGroup(group, menu);
    }
  }

  private void validateOrderOptionGroup(OrderOptionGroup group, Menu menu) {
    for (MenuOptionGroup menuOptionGroup : menu.getMenuOptionGroups()) {
      if (menuOptionGroup.getOptionGroup().isSatisfiedBy(group.convertToOptionGroupValidation())) {
        return;
      }
    }
    throw new IllegalArgumentException("메뉴가 변경됐습니다.");
  }

  private Shop getShop(Order order) {
    return shopRepository.findById(order.getShopId()).orElseThrow(IllegalArgumentException::new);
  }

  private Map<Long, Menu> getMenus(Order order) {
    return menuRepository.findAllById(order.getMenuIds()).stream()
        .collect(toMap(Menu::getId, identity()));
  }
}