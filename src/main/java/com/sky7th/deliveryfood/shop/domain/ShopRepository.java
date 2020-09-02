package com.sky7th.deliveryfood.shop.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

  List<Shop> findAllByOwnerId(Long ownerId);
}