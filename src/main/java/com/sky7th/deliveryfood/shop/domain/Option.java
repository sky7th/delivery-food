package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "OPTIONS")
@Getter
public class Option {

  public enum OptionStatus {ACTIVE, INACTIVE, DELETED}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OPTION_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE")
  private Money price;

  public Option(String name, Money price) {
    this(null, name, price);
  }

  @Builder
  public Option(Long id, String name, Money price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  private Option() {
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }

    if (!(object instanceof Option)) {
      return false;
    }

    Option other = (Option) object;
    return Objects.equals(name, other.getName()) && Objects.equals(price, other.getPrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price);
  }

  public boolean isSatisfiedBy(OptionValidation cartOption) {
    return Objects.equals(name, cartOption.getName()) && Objects.equals(price, cartOption.getPrice());
  }
}
