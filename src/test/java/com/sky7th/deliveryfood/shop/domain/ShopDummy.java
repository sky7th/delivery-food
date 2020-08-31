package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.Menu.MenuBuilder;
import com.sky7th.deliveryfood.shop.domain.Option.OptionBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionGroup.OptionGroupBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionGroupValidation.OptionGroupValidationBuilder;
import com.sky7th.deliveryfood.shop.domain.OptionValidation.OptionValidationBuilder;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopBuilder;
import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import java.util.Arrays;

public class ShopDummy {

  public static ShopBuilder aShop() {
    return Shop.builder()
        .name("shop")
        .status(ShopStatus.ACTIVE)
        .minOrderAmount(Money.wons(20000));
  }

  public static MenuBuilder aMenu() {
    return Menu.builder()
        .name("menu")
        .description("menu description")
        .basic(OptionGroup.basic(
            Option.builder().name("option 1-1").price(Money.wons(20000)).build(),
            Option.builder().name("option 1-2").price(Money.wons(20000)).build()))
        .additives(Arrays.asList(
            OptionGroup.additive("option group 2", false,
                Option.builder().name("option 2-1").price(Money.wons(0)).build(),
                Option.builder().name("option 2-2").price(Money.wons(0)).build()),
            OptionGroup.additive("option group 3", true,
                Option.builder().name("option group 3-1").price(Money.wons(20000)).build(),
                Option.builder().name("option group 3-2").price(Money.wons(20000)).build())
        ));
  }

  public static OptionGroupBuilder anOptionGroup() {
    return OptionGroup.builder()
        .selectable(true)
        .basic(true)
        .name("option group")
        .options(Arrays.asList(anOption().build()));
  }

  public static OptionBuilder anOption() {
    return Option.builder()
        .name("option")
        .price(Money.wons(20000));
  }

  public static OptionGroupValidationBuilder anOptionGroupValidation() {
    return OptionGroupValidation.builder()
        .name("option group")
        .optionValidations(Arrays.asList(anOptionValidation().build()));
  }

  public static OptionValidationBuilder anOptionValidation() {
    return OptionValidation.builder()
        .name("option")
        .price(Money.wons(20000));
  }
}