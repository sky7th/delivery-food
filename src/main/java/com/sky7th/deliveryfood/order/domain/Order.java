package com.sky7th.deliveryfood.order.domain;

import static java.util.stream.Collectors.toList;

import com.sky7th.deliveryfood.common.domain.BaseTimeEntity;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order extends BaseTimeEntity {

  public enum OrderStatus {BEFORE_PAYMENT, ORDER_REQUEST, ORDER_APPROVAL, IN_DELIVERY, DELIVERY_COMPLETE}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ID")
  private Long id;

  @Column(name = "USER_ID")
  private Long userId;

  @Column(name = "SHOP_ID")
  private Long shopId;

  @Column(name = "ORDERED_TIME")
  private LocalDateTime orderedTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS")
  private OrderStatus status;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "ORDER_ID")
  private Set<OrderMenuItem> orderMenuItems = new LinkedHashSet<>();

  public Order(Long userId, Long shopId, List<OrderMenuItem> items) {
    this(null, userId, shopId, items, LocalDateTime.now(), OrderStatus.BEFORE_PAYMENT);
  }

  @Builder
  public Order(Long id, Long userId, Long shopId, List<OrderMenuItem> items,
      LocalDateTime orderedTime, OrderStatus status) {
    this.id = id;
    this.userId = userId;
    this.shopId = shopId;
    this.orderedTime = orderedTime;
    this.status = status;
    this.orderMenuItems.addAll(items);
  }

  private Order() {
  }

  public List<Long> getMenuIds() {
    return orderMenuItems.stream().map(OrderMenuItem::getMenuId).collect(toList());
  }

  public void order(OrderValidator orderValidator) {
    orderValidator.validate(this);
    changeStatus(OrderStatus.BEFORE_PAYMENT);
  }

  public void changeStatus(OrderStatus status) {
    this.status = status;
  }

  public Money calculateTotalPrice() {
    return Money.sum(orderMenuItems, OrderMenuItem::calculatePrice);
  }
}
