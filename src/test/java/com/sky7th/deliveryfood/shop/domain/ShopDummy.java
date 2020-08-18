package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.Menu.MenuBuilder;
import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import com.sky7th.deliveryfood.shop.domain.MenuGroup.MenuGroupBuilder;
import com.sky7th.deliveryfood.shop.domain.Shop;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopBuilder;
import com.sky7th.deliveryfood.shop.domain.Option;
import com.sky7th.deliveryfood.shop.domain.Option.OptionBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionGroup;
import com.sky7th.deliveryfood.shop.domain.OptionGroup.OptionGroupBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionValidation;
import com.sky7th.deliveryfood.shop.domain.OptionValidation.OptionValidationBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionGroupValidation;
import com.sky7th.deliveryfood.shop.domain.OptionGroupValidation.OptionGroupValidationBuilder;

import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import java.util.Arrays;

public class ShopDummy {

  public static ShopBuilder aShop() {
    return Shop.builder()
        .id(1L)
        .name("황금족발")
        .status(ShopStatus.ACTIVE)
        .minOrderAmount(Money.wons(20000));
  }

  public static MenuBuilder aMenu() {
    return Menu.builder()
        .id(1L)
        .name("menu 1")
        .description("menu 1 description")
        .basic(OptionGroup.basic(
            Option.builder().name("option 1-1").price(Money.wons(25000)).build(),
            Option.builder().name("option 1-2").price(Money.wons(30000)).build()))
        .additives(Arrays.asList(
            OptionGroup.additive("option group 2", false,
                Option.builder().name("option 2-1").price(Money.wons(0)).build(),
                Option.builder().name("option 2-2").price(Money.wons(0)).build()),
            OptionGroup.additive("option group 3", true,
                Option.builder().name("option group 3-1").price(Money.wons(500)).build(),
                Option.builder().name("option group 3-2").price(Money.wons(2000)).build())
        ));
  }

  public static OptionGroupBuilder anOptionGroup() {
    return OptionGroup.builder()
        .selectable(true)
        .basic(true)
        .name("option group 1")
        .options(Arrays.asList(anOption().build()));
  }

  public static OptionBuilder anOption() {
    return Option.builder()
        .name("option 1-1")
        .price(Money.wons(0));
  }

  public static OptionGroupValidationBuilder anOptionGroupValidation() {
    return OptionGroupValidation.builder()
        .name("option group validation 1-2")
        .options(Arrays.asList(anOption().build()));
  }

  public static OptionValidationBuilder anOptionValidation() {
    return OptionValidation.builder()
        .name("option validation 1-2")
        .price(Money.wons(0));
  }
}