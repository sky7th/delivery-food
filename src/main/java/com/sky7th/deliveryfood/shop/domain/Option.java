package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OPTIONS")
@NoArgsConstructor
@Getter
public class Option extends BaseTimeEntity {

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

  public boolean isSatisfiedBy(Option option) {
    return Objects.equals(name, option.getName()) && Objects.equals(price, option.getPrice());
  }
}
