package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class OptionValidation {

  private String name;
  private Money price;

  @Builder
  public OptionValidation(String name, Money price) {
    this.name = name;
    this.price = price;
  }
}
