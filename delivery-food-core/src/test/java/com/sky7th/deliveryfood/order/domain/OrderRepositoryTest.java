package com.sky7th.deliveryfood.order.domain;

import static com.sky7th.deliveryfood.order.domain.OrderDummy.anOrder;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void 주문_이하_내용들까지_한꺼번에_저장된다() {
    Order newOrder = anOrder().build();
    OrderMenuItem newOrderMenuItem = newOrder.getOrderMenuItems().iterator().next();
    OrderOptionGroup newOrderOptionGroup = newOrderMenuItem.getOrderOptionGroups().iterator().next();
    OrderOption newOrderOption = newOrderOptionGroup.getOrderOptions().iterator().next();

    orderRepository.save(newOrder);
    Order savedOrder = orderRepository.findOrderById(newOrder.getId());
    OrderMenuItem savedOrderMenuItem = savedOrder.getOrderMenuItems().iterator().next();
    OrderOptionGroup savedOrderOptionGroup = savedOrderMenuItem.getOrderOptionGroups().iterator().next();
    OrderOption savedOrderOption = savedOrderOptionGroup.getOrderOptions().iterator().next();

    assertThat(newOrder.getOrderedTime()).isEqualTo(savedOrder.getOrderedTime());
    assertThat(newOrderMenuItem.getName()).isEqualTo(savedOrderMenuItem.getName());
    assertThat(newOrderOptionGroup.getName()).isEqualTo(savedOrderOptionGroup.getName());
    assertThat(newOrderOption.getName()).isEqualTo(savedOrderOption.getName());
  }
}