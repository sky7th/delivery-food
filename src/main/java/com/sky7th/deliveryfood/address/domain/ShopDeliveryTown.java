package com.sky7th.deliveryfood.address.domain;

import com.sky7th.deliveryfood.shop.domain.Shop;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHOP_DELIVERY_TOWN")
@NoArgsConstructor
@Getter
public class ShopDeliveryTown {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SHOP_DELIVERY_TOWN_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "SHOP_ID")
  private Shop shop;

  @Column(name = "TOWN_CODE")
  private String townCode;

  @Column(name = "TOWN_NAME")
  private String townName;

  public ShopDeliveryTown(Shop shop, String townCode, String townName) {
    this.shop = shop;
    this.townCode = townCode;
    this.townName = townName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShopDeliveryTown that = (ShopDeliveryTown) o;
    return Objects.equals(shop, that.shop) &&
        Objects.equals(townCode, that.townCode) &&
        Objects.equals(townName, that.townName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shop, townCode, townName);
  }
}
