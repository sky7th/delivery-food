package com.sky7th.deliveryfood.shop.domain;

import com.sky7th.deliveryfood.common.domain.BaseTimeEntity;
import com.sky7th.deliveryfood.generic.money.domain.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "MENU_GROUPS")
@NoArgsConstructor
@Getter
public class MenuGroup {

  public enum MenuGroupStatus {ACTIVE, INACTIVE, DELETED}

  public static final String FIRST_OPTION_GROUP_NAME = "대표 메뉴";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MENU_GROUP_ID")
  private Long id;

  @Column(name = "SHOP_ID")
  private Long shopId;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "REPRESENTATIVE")
  private boolean representative;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "STATUS")
  private MenuGroupStatus status;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "MENU_GROUP_ID")
  private List<Menu> menus = new ArrayList<>();

  public static MenuGroup representative(Long shopId, String description, Menu... groups) {
    return new MenuGroup(shopId, FIRST_OPTION_GROUP_NAME, description, true, Menu.FIRST_PRIORITY,
        groups);
  }

  public static MenuGroup additive(Long shopId, String name, String description, Menu... groups) {
    return new MenuGroup(shopId, name, description, false, Menu.LAST_PRIORITY, groups);
  }

  private MenuGroup(Long shopId, String name, String description, boolean representative,
      Integer priority, Menu... groups) {
    this(null, shopId, name, description, representative, priority, MenuGroupStatus.INACTIVE,
        Arrays.asList(groups));
    this.priority = representative ? 0 : Menu.LAST_PRIORITY;
  }

  @Builder
  public MenuGroup(Long id, Long shopId, String name, String description, boolean representative,
      Integer priority, MenuGroupStatus status, List<Menu> additives) {
    this.id = id;
    this.shopId = shopId;
    this.name = name;
    this.description = description;
    this.representative = representative;
    this.priority = priority;
    this.status = status;
    this.menus.addAll(additives);
  }
}
