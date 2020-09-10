package com.sky7th.deliveryfood.order.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @EntityGraph(attributePaths = {"orderMenuItems", "orderMenuItems.orderOptionGroups",
      "orderMenuItems.orderOptionGroups.orderOptions"})
  @Query("SELECT o FROM Order o WHERE o.id = :orderId")
  Order findOrderById(@Param("orderId") Long orderId);
}
