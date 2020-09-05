package com.sky7th.deliveryfood.shop.domain;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
  private Set<OptionGroup> optionGroups = new LinkedHashSet<>();

  public Menu(MenuGroup menuGroup, String name, String description, String imageUrl, OptionGroup basic,
      OptionGroup... groups) {
    this(null, menuGroup, name, description, imageUrl, Menu.LAST_PRIORITY, MenuStatus.INACTIVE, basic,
        Arrays.asList(groups));
  }

  private Menu() {
  }

  @Builder
  public Menu(Long id, MenuGroup menuGroup, String name, String description, String imageUrl, Integer priority,
      MenuStatus status, OptionGroup basic, List<OptionGroup> additives) {
    this.id = id;
    this.menuGroup = menuGroup;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
    this.priority = priority;
    this.status = status;
    this.optionGroups.add(basic);
    this.optionGroups.addAll(additives);
  }

  private OptionGroup getBasicOptionGroup() {
    return optionGroups
        .stream()
        .filter(OptionGroup::isBasic)
        .findFirst()
        .orElseThrow(IllegalStateException::new);
  }
}
