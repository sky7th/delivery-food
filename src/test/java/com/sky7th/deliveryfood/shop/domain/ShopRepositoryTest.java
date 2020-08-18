//package com.sky7th.deliveryfood.shop.domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//class ShopRepositoryTest {
//
//  @Autowired
//  private ShopRepository shopRepository;
//
//  @Test
//  void 가게_정보_가져오기() {
//    Shop shop = shopRepository.findById(1L)
//        .orElseThrow(IllegalArgumentException::new);
//    assertThat(shop.getName()).isEqualTo("shop 1");
//  }
//
//}
