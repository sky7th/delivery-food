package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.common.domain.BaseTimeEntity;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHOPS")
@NoArgsConstructor
@Getter
public class Shop extends BaseTimeEntity {

  public enum ShopStatus {ACTIVE, INACTIVE, DELETED, NON_DELIVERABLE}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SHOP_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private ShopStatus status;

  @Column(name = "OPERATING_TIME")
  private String operatingTime;

  @Column(name = "MIN_ORDER_AMOUNT")
  private Money minOrderAmount;

  public Shop(String name, String operatingTime, Money minOrderAmount) {
    this(null, name, ShopStatus.INACTIVE, operatingTime, minOrderAmount);
  }

  @Builder
  public Shop(Long id, String name, ShopStatus status, String operatingTime, Money minOrderAmount) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.operatingTime = operatingTime;
    this.minOrderAmount = minOrderAmount;
  }

  public boolean isValidOrderAmount(Money amount) {
    return amount.isGreaterThanOrEqual(minOrderAmount);
  }

  public void updateStatus(ShopStatus status) {
    this.status = status;
  }
}