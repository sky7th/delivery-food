package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OPTIONS")
@NoArgsConstructor
@Getter
public class Option {

  public enum OptionStatus {ACTIVE, INACTIVE, DELETED}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OPTION_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OPTION_GROUP_ID")
  private OptionGroup optionGroup;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE")
  private Money price;

  public Option(Long optionGroupId, String name, Money price) {
    this(null, optionGroupId, name, price);
  }

  @Builder
  public Option(Long id, Long optionGroupId, String name, Money price) {
    this.id = id;
    this.optionGroup = new OptionGroup(optionGroupId);
    this.name = name;
    this.price = price;
  }

  public void update(String name, Money price) {
    this.name = name;
    this.price = price;
  }

  public boolean isSatisfiedBy(OptionValidation optionValidation) {
    return Objects.equals(name, optionValidation.getName()) && Objects.equals(price, optionValidation.getPrice());
  }

  public void setOptionGroup(OptionGroup optionGroup) {
    this.optionGroup = optionGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Option option = (Option) o;
    return Objects.equals(id, option.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
