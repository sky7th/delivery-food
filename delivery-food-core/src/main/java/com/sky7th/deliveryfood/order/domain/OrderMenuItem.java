package com.sky7th.deliveryfood.order.domain;

import static java.util.stream.Collectors.toList;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.OptionGroupValidation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "ORDER_MENU_ITEMS")
@Getter
public class OrderMenuItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_MENU_ITEM_ID")
  private Long id;

  @Column(name = "MENU_ID")
  private Long menuId;

  @Column(name = "NAME")
  private String name;

  @Column(name = "COUNT")
  private int count;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "ORDER_MENU_ITEM_ID")
  private Set<OrderOptionGroup> orderOptionGroups = new LinkedHashSet<>();

  public OrderMenuItem(Long menuId, String name, int count, List<OrderOptionGroup> orderOptionGroups) {
    this(null, menuId, name, count, orderOptionGroups);
  }

  @Builder
  public OrderMenuItem(Long id, Long menuId, String name, int count,
      List<OrderOptionGroup> orderOptionGroups) {
    this.id = id;
    this.menuId = menuId;
    this.name = name;
    this.count = count;
    this.orderOptionGroups.addAll(orderOptionGroups);
  }

  private OrderMenuItem() {
  }

  public Money calculatePrice() {
    return Money.sum(orderOptionGroups, OrderOptionGroup::calculatePrice).multiply(count);
  }

  private List<OptionGroupValidation> convertToOptionGroupValidations() {
    return orderOptionGroups.stream().map(OrderOptionGroup::convertToOptionGroupValidation).collect(toList());
  }
}
