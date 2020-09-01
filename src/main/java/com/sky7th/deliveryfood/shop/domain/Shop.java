package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.common.domain.BaseTimeEntity;
import com.sky7th.deliveryfood.generic.address.domain.Address;
import com.sky7th.deliveryfood.generic.address.domain.ShopDeliveryAddress;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHOPS")
@NoArgsConstructor
@Getter
public class Shop extends BaseTimeEntity {

  public enum ShopStatus {ACTIVE, INACTIVE, NOT_APPROVAL, DELETED}
  public enum DeliveryType {DELIVERABLE, NON_DELIVERABLE, RIDER}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SHOP_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private ShopStatus status;

  @Column(name = "DELIVERY_TYPE")
  @Enumerated(EnumType.STRING)
  private DeliveryType deliveryType;

  @Column(name = "OPERATING_TIME")
  private String operatingTime;

  @Column(name = "MIN_ORDER_AMOUNT")
  private Money minOrderAmount;

  @Column(name = "BUSINESS_NUMBER")
  private String businessNumber;

  @Column(name = "OWNER_ID")
  private Long ownerId;

  @Lob
  @Column(name = "INTRODUCTION")
  private String introduction;

  @Lob
  @Column(name = "GUIDE")
  private String guide;

  @OneToOne(cascade = CascadeType.ALL)
  private Address address;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<ShopDeliveryAddress> shopDeliveryAddresses = new LinkedHashSet<>();

  @Builder
  public Shop(String name, ShopStatus status,
      DeliveryType deliveryType, String operatingTime,
      Money minOrderAmount, String businessNumber, Long ownerId, String introduction,
      String guide, Address address) {
    this.name = name;
    this.status = status;
    this.deliveryType = deliveryType;
    this.operatingTime = operatingTime;
    this.minOrderAmount = minOrderAmount;
    this.businessNumber = businessNumber;
    this.ownerId = ownerId;
    this.introduction = introduction;
    this.guide = guide;
    this.address = address;
  }

  public boolean isValidOrderAmount(Money amount) {
    return amount.isGreaterThanOrEqual(minOrderAmount);
  }

  public void updateStatus(ShopStatus status) {
    this.status = status;
  }
}