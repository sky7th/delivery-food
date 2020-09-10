package com.sky7th.deliveryfood.order.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.OptionValidation;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class OrderOption {

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE")
  private Money price;

  @Builder
  public OrderOption(String name, Money price) {
    this.name = name;
    this.price = price;
  }

  OrderOption() {
  }

  public OptionValidation convertToOptionValidation() {
    return new OptionValidation(name, price);
  }
}
