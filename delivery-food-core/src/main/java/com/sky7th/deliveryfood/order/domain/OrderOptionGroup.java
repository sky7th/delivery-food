package com.sky7th.deliveryfood.order.domain;

import static java.util.stream.Collectors.toList;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.OptionGroupValidation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@NoArgsConstructor
@Getter
public class OrderOptionGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_OPTION_GROUP_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @ElementCollection
  @CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
  private Set<OrderOption> orderOptions = new LinkedHashSet<>();

  public OrderOptionGroup(String name, List<OrderOption> options) {
    this(null, name, options);
  }

  @Builder
  public OrderOptionGroup(Long id, String name, List<OrderOption> options) {
    this.id = id;
    this.name = name;
    this.orderOptions.addAll(options);
  }

  public Money calculatePrice() {
    return Money.sum(orderOptions, OrderOption::getPrice);
  }

  public OptionGroupValidation convertToOptionGroupValidation() {
    return new OptionGroupValidation(name,
        orderOptions.stream().map(OrderOption::convertToOptionValidation).collect(toList()));
  }
}
