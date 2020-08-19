package com.sky7th.deliveryfood.shop.domain;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

  @EntityGraph(attributePaths = {"menus", "menus.optionGroups", "menus.optionGroups.options"})
  @Query("SELECT m FROM MenuGroup m WHERE m.shopId = :shopId")
  List<MenuGroup> findByShopId(@Param("shopId") Long shopId);
}
