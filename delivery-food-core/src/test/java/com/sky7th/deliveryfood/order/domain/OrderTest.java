package com.sky7th.deliveryfood.order.domain;

import static com.sky7th.deliveryfood.order.domain.OrderDummy.anOrder;
import static org.assertj.core.api.Assertions.assertThat;

import com.sky7th.deliveryfood.order.domain.Order.OrderStatus;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void 결제_요청_상태로_변경한다() {
    Order order = anOrder().build();

    order.changeStatus(OrderStatus.ORDER_REQUEST);

    assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER_REQUEST);
  }

  @Test
  void 배달_완료_상태로_변경한다() {
    Order order = anOrder().build();

    order.changeStatus(OrderStatus.DELIVERY_COMPLETE);

    assertThat(order.getStatus()).isEqualTo(OrderStatus.DELIVERY_COMPLETE);
  }
}