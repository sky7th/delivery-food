package com.sky7th.deliveryfood.order.domain;

import static com.sky7th.deliveryfood.order.domain.OrderDummy.anOrder;
import static com.sky7th.deliveryfood.order.domain.OrderDummy.anOrderMenuItem;
import static com.sky7th.deliveryfood.order.domain.OrderDummy.anOrderOption;
import static com.sky7th.deliveryfood.order.domain.OrderDummy.anOrderOptionGroup;
import static com.sky7th.deliveryfood.shop.domain.ShopDummy.aMenu;
import static com.sky7th.deliveryfood.shop.domain.ShopDummy.aShop;
import static com.sky7th.deliveryfood.shop.domain.ShopDummy.anOption;
import static com.sky7th.deliveryfood.shop.domain.ShopDummy.anOptionGroup;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.MenuRepository;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.ShopRepository;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderValidatorTest {

  private OrderValidator validator;

  @BeforeEach
  void setUp() {
    validator = new OrderValidator(mock(ShopRepository.class), mock(MenuRepository.class));
  }

  @Test
  void 가게_최소_주문_금액_체크() {
    Shop shop = aShop().minOrderAmount(Money.wons(20000)).build();

    assertThat(shop.isValidOrderAmount(Money.wons(19000))).isFalse();
    assertThat(shop.isValidOrderAmount(Money.wons(21000))).isTrue();
  }

  @Test
  public void 주문_내용이_없을_경우_exception_발생() {
    Order order = anOrder().items(emptyList()).build();

    assertThrows(IllegalStateException.class, () -> {
      validator.validate(order, aShop().build(), new HashMap<>());
    });
  }

  @Test
  void 주문_검증_시에_메뉴_이름이_변경되었으면_exception_발생() {
    Menu menu = aMenu().name("menu 1").build();
    Order order = anOrder().items(asList(anOrderMenuItem().name("menu 2").build()))
        .build();

    assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(order, aShop().build(), new HashMap<>() {{ put(1L, menu); }});
    });
  }

  @Test
  void 주문_검증_시에_옵션_그룹_이름이_변경되었으면_exception_발생() {
    Menu menu = aMenu().basic(anOptionGroup().name("option group 1").build())
        .build();
    Order order = anOrder().items(asList(
            anOrderMenuItem().groups(asList(
                anOrderOptionGroup().name("option group 2").build()
            )).build()))
        .build();

    assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(order, aShop().build(), new HashMap<>() {{ put(1L, menu); }});
    });
  }

  @Test
  void 주문_검증_시에_옵션_이름이_변경되었으면_exception_발생() {
    Menu menu = aMenu()
        .basic(anOptionGroup().options(asList(
                anOption().name("option 1").build()
            )).build())
        .build();
    Order order = anOrder()
        .items(asList(
            anOrderMenuItem().groups(asList(
                    anOrderOptionGroup().options(asList(
                            anOrderOption().name("option 2").build()
                    )).build()
                )).build()))
        .build();

    assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(order, aShop().build(), new HashMap<>() {{ put(1L, menu); }});
    });
  }

  @Test
  void 주문_검증_시에_옵션_가격이_변경되었으면_exception_발생() {
    Menu menu = aMenu()
        .basic(anOptionGroup().options(asList(
            anOption().price(Money.wons(30000)).build()
        )).build())
        .build();
    Order order = anOrder()
        .items(asList(
            anOrderMenuItem().groups(asList(
                anOrderOptionGroup().options(asList(
                    anOrderOption().price(Money.wons(40000)).build()
                )).build()
            )).build()))
        .build();

    assertThrows(IllegalArgumentException.class, () -> {
      validator.validate(order, aShop().build(), new HashMap<>() {{ put(1L, menu); }});
    });
  }
}