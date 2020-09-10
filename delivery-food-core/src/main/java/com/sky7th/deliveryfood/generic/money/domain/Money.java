package com.sky7th.deliveryfood.generic.money.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class Money {

  public static final Money ZERO = Money.wons(0);

  private final BigDecimal amount;

  public static Money wons(long amount) {
    return new Money(BigDecimal.valueOf(amount));
  }

  Money(BigDecimal amount) {
    this.amount = amount;
  }

  public Money plus(Money amount) {
    return new Money(this.amount.add(amount.amount));
  }

  public boolean isGreaterThanOrEqual(Money other) {
    return amount.compareTo(other.amount) >= 0;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public static <T> Money sum(Collection<T> elements, Function<T, Money> behavior) {
    return elements.stream().map(behavior).reduce(Money.ZERO, Money::plus);
  }

  public Money multiply(int number) {
    return new Money(this.amount.multiply(BigDecimal.valueOf(number)));
  }

  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (!(object instanceof Money)) {
      return false;
    }

    Money other = (Money) object;
    return Objects.equals(amount.doubleValue(), other.amount.doubleValue());
  }

  public int hashCode() {
    return Objects.hashCode(amount);
  }

  public String toString() {
    return amount.toString() + "원";
  }
}