package com.sky7th.deliveryfood.shop.domain;

import java.util.LinkedHashSet;
import java.util.List;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MENUS")
@NoArgsConstructor
@Getter
public class Menu {

  public enum MenuStatus {ACTIVE, INACTIVE, DELETED, OUT_OF_STOCK}

  public static final Integer FIRST_PRIORITY = 0;
  public static final Integer LAST_PRIORITY = 999;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MENU_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MENU_GROUP_ID")
  private MenuGroup menuGroup;

  @Column(name = "NAME")
  private String name;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "IMAGE_URL")
  private String imageUrl;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private MenuStatus status;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
  private Set<MenuOptionGroup> menuOptionGroups = new LinkedHashSet<>();

  public Menu(Long menuGroupId, String name, String description, String imageUrl, OptionGroup basic) {
    this(null, menuGroupId, name, description, imageUrl, Menu.LAST_PRIORITY, MenuStatus.INACTIVE, basic);
  }

  @Builder
  public Menu(Long id, Long menuGroupId, String name, String description, String imageUrl, Integer priority,
      MenuStatus status, OptionGroup basic) {
    this.id = id;
    this.menuGroup = new MenuGroup(menuGroupId);
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.priority = priority;
    this.status = status;
    this.menuOptionGroups.add(new MenuOptionGroup(this, basic));
  }

  public void update(String name, String description, String imageUrl, List<Option> requestOptions) {
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    getBasicOptionGroup().updateBasicOptions(requestOptions);
  }

  private OptionGroup getBasicOptionGroup() {
    return menuOptionGroups
        .stream()
        .filter(menuOptionGroup -> menuOptionGroup.getOptionGroup().isBasic())
        .findFirst()
        .orElseThrow(IllegalStateException::new)
        .getOptionGroup();
  }
}
