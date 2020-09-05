package com.sky7th.deliveryfood.shop.domain;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "MENU_GROUPS")
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
  @Enumerated(EnumType.STRING)
  private MenuGroupStatus status;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuGroup", fetch = FetchType.EAGER)
  private Set<Menu> menus = new LinkedHashSet<>();

  public static MenuGroup representative(Long shopId, String description) {
    return new MenuGroup(shopId, FIRST_OPTION_GROUP_NAME, description, true, Menu.FIRST_PRIORITY);
  }

  public static MenuGroup additive(Long shopId, String name, String description) {
    return new MenuGroup(shopId, name, description, false, Menu.LAST_PRIORITY);
  }

  private MenuGroup(Long shopId, String name, String description, boolean representative,
      Integer priority) {
    this(null, shopId, name, description, representative, priority, MenuGroupStatus.INACTIVE);
    this.priority = representative ? 0 : Menu.LAST_PRIORITY;
  }

  private MenuGroup() {
  }

  @Builder
  public MenuGroup(Long id, Long shopId, String name, String description, boolean representative,
      Integer priority, MenuGroupStatus status) {
    this.id = id;
    this.shopId = shopId;
    this.name = name;
    this.description = description;
    this.representative = representative;
    this.priority = priority;
    this.status = status;
  }
}
