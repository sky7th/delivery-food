package com.sky7th.deliveryfood.shop.domain;

import static com.sky7th.deliveryfood.shop.domain.ShopDummy.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import java.util.Arrays;
import org.junit.jupiter.api.Test;


class MenuTest {

  @Test
  void 주문_검증_시에_메뉴_이름이_변경되었으면_exception_발생() {
    Menu menu = aMenu().name("menu 2").build();

    assertThrows(IllegalArgumentException.class, () -> {
      menu.validateOrder("menu 1", Arrays.asList(anOptionGroupValidation().build()));
    });
  }


  @Test
  void 주문_검증_시에_옵션_그룹_이름이_변경되었으면_exception_발생() {
    Menu menu = aMenu().name("menu 1").basic(anOptionGroup().name("option group 2").build()).build();

    assertThrows(IllegalArgumentException.class, () -> {
      menu.validateOrder("menu 1", Arrays.asList(anOptionGroupValidation().name("option group 1").build()));
    });
  }


  @Test
  void 주문_검증_시에_옵션_이름이_변경되었으면_exception_발생() {
    Menu menu = aMenu().name("menu 1")
        .basic(anOptionGroup()
            .options(Arrays.asList(anOption().name("option 2-2").build()))
            .build())
        .build();

    assertThrows(IllegalArgumentException.class, () -> {
      menu.validateOrder("menu 1", Arrays.asList(
          anOptionGroupValidation().options(Arrays.asList(anOption().name("option 1-1").build()))
              .build()));
    });
  }


  @Test
  void 주문_검증_시에_옵션_가격이_변경되었으면_exception_발생() {
    Menu menu = aMenu()
        .basic(anOptionGroup()
            .options(Arrays.asList(anOption().name("option 1-1").price(Money.wons(500)).build()))
            .build())
        .build();

    assertThrows(IllegalArgumentException.class, () -> {
      menu.validateOrder("", Arrays.asList(anOptionGroupValidation()
          .options(Arrays.asList(anOption().name("option 1-1").price(Money.wons(0)).build()))
          .build()));
    });
  }
}
