package com.sky7th.deliveryfood.order.domain;

import static com.sky7th.deliveryfood.shop.domain.ShopDummy.*;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.order.domain.Order.OrderBuilder;
import com.sky7th.deliveryfood.order.domain.Order.OrderStatus;
import com.sky7th.deliveryfood.order.domain.OrderMenuItem.OrderMenuItemBuilder;
import com.sky7th.deliveryfood.order.domain.OrderOption.OrderOptionBuilder;
import com.sky7th.deliveryfood.order.domain.OrderOptionGroup.OrderOptionGroupBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionGroup;
import java.time.LocalDateTime;
import java.util.Arrays;

public class OrderDummy {

  public static OrderBuilder anOrder() {
    return Order.builder()
        .userId(1L)
        .shopId(aShop().build().getId())
        .status(OrderStatus.BEFORE_PAYMENT)
        .orderedTime(LocalDateTime.of(2020, 1, 1, 0, 0))
        .items(Arrays.asList(anOrderMenuItem().build()));
  }

  public static OrderMenuItemBuilder anOrderMenuItem() {
    return OrderMenuItem.builder()
        .menuId(aMenu().build().getId())
        .name("menu")
        .count(1)
        .groups(Arrays.asList(
            anOrderOptionGroup()
                .name(OptionGroup.FIRST_OPTION_GROUP_NAME)
                .options(Arrays
                    .asList(anOrderOption().name("option 1-1").price(Money.wons(20000)).build()))
                .build()));
  }

  public static OrderOptionGroupBuilder anOrderOptionGroup() {
    return OrderOptionGroup.builder()
        .name("option group")
        .options(Arrays.asList(anOrderOption().build()));
  }

  public static OrderOptionBuilder anOrderOption() {
    return OrderOption.builder()
        .name("option")
        .price(Money.wons(20000));
  }
}