package com.sky7th.deliveryfood.generic.address.domain;

import com.sky7th.deliveryfood.shop.domain.Shop;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "SHOP_DELIVERY_ADDRESS")
@Getter
public class ShopDeliveryAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SHOP_DELIVERY_ADDRESS_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "SHOP_ID")
  private Shop shop;

  @Column(name = "TOWN_CODE")
  private String townCode;

  public ShopDeliveryAddress(Shop shop, String townCode) {
    this.shop = shop;
    this.townCode = townCode;
  }

  private ShopDeliveryAddress() {
  }
}
