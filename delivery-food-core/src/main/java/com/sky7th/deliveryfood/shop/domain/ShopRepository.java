package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.shop.domain.Shop.ShopStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

  List<Shop> findAllByOwnerIdAndStatusIsNot(Long ownerId, ShopStatus status);
}