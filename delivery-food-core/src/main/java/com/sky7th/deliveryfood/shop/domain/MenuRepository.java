package com.sky7th.deliveryfood.shop.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {

  @EntityGraph(attributePaths = {"menuOptionGroups", "menuOptionGroups.optionGroup", "menuOptionGroups.optionGroup.options"})
  @Query("SELECT m FROM Menu m WHERE m.id = :menuId")
  Menu findByIdWithFetch(@Param("menuId") Long menuId);
}
