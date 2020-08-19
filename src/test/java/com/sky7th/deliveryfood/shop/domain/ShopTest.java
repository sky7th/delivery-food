package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import org.junit.jupiter.api.Test;

import static com.sky7th.deliveryfood.shop.domain.ShopDummy.aShop;
import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

  @Test
  void 가게_최소_주문_금액_체크() {
    Shop shop = aShop().minOrderAmount(Money.wons(20000)).build();

    assertThat(shop.isValidOrderAmount(Money.wons(19000))).isFalse();
    assertThat(shop.isValidOrderAmount(Money.wons(21000))).isTrue();
  }
}
