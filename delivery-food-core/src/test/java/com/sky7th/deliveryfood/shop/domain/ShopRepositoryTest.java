//package com.sky7th.deliveryfood.shop.domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//class ShopRepositoryTest {
//
//  @Autowired
//  private MenuGroupRepository menuGroupRepository;
//
//  @Test
//  void 메뉴_그룹_이하_내용들까지_한꺼번에_저장된다() {
//    final Long SHOP_ID = 1L;
//    MenuGroup newMenuGroup = MenuGroup
//        .representative(SHOP_ID);
//    Menu newMenu = newMenuGroup.getMenus().iterator().next();
//    OptionGroup newOptionGroup = newMenu.getOptionGroups().iterator().next();
//    Option newOption = newOptionGroup.getOptions().iterator().next();
//
//    menuGroupRepository.save(newMenuGroup);
//    List<MenuGroup> savedMenuGroups = menuGroupRepository.findByShopId(SHOP_ID);
//    MenuGroup savedMenuGroup = savedMenuGroups.get(savedMenuGroups.size() - 1);
//    Menu savedMenu = savedMenuGroup.getMenus().iterator().next();
//    OptionGroup savedOptionGroup = savedMenu.getOptionGroups().iterator().next();
//    Option savedOption = savedOptionGroup.getOptions().iterator().next();
//
//    assertThat(newMenuGroup.getName()).isEqualTo(savedMenuGroup.getName());
//    assertThat(newMenu.getName()).isEqualTo(savedMenu.getName());
//    assertThat(newOptionGroup.getName()).isEqualTo(savedOptionGroup.getName());
//    assertThat(newOption.getName()).isEqualTo(savedOption.getName());
//  }
//}
