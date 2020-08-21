package com.sky7th.deliveryfood.shop.domain;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {

  @EntityGraph(attributePaths = {"menus", "menus.optionGroups", "menus.optionGroups.options"})
  @Query("SELECT m FROM Menu m WHERE m.id = :menuId")
  List<Menu> findByShopId(@Param("menuId") Long menuId);
}
