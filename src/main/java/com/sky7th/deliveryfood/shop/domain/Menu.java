package com.sky7th.deliveryfood.shop.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "MENUS")
@Getter
public class Menu {

  public enum MenuStatus {ACTIVE, INACTIVE, DELETED, OUT_OF_STOCK}

  public static final Integer FIRST_PRIORITY = 0;
  public static final Integer LAST_PRIORITY = 999;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MENU_ID")
  private Long id;

  @ManyToOne
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

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "MENU_ID")
  private Set<MenuOptionGroup> menuOptionGroups = new LinkedHashSet<>();

  public Menu(Long menuGroupId, String name, String description, String imageUrl, OptionGroup basic) {
    this(null, menuGroupId, name, description, imageUrl, Menu.LAST_PRIORITY, MenuStatus.INACTIVE, basic);
  }

  private Menu() {
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

  private MenuOptionGroup getBasicMenuOptionGroup() {
    return menuOptionGroups
        .stream()
        .filter(menuOptionGroup -> menuOptionGroup.getOptionGroup().isBasic())
        .findFirst()
        .orElseThrow(IllegalStateException::new);
  }

  public void updateMenuOptionGroup(List<Long> requestOptionGroupIds, Long requestOwnerId) {
    deleteMenuOptionGroupNotContainedIn(requestOptionGroupIds);
    Set<MenuOptionGroup> requestOptionGroup = requestOptionGroupIds.stream()
        .map(MenuOptionGroup::new)
        .collect(Collectors.toSet());
    this.menuOptionGroups.addAll(requestOptionGroup);
  }

  private void deleteMenuOptionGroupNotContainedIn(List<Long> requestOptionGroupIds) {
    List<MenuOptionGroup> toBeDeletedMenuOptionGroup =
        this.menuOptionGroups.stream()
            .filter(menuOptionGroup -> !requestOptionGroupIds.contains(menuOptionGroup.getId()))
            .collect(Collectors.toList());
    toBeDeletedMenuOptionGroup.forEach(this.menuOptionGroups::remove);
  }
}
