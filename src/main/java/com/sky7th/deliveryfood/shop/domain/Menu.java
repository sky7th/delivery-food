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

  @OneToMany(cascade = CascadeType.DETACH)
  @JoinColumn(name = "MENU_ID")
  private Set<OptionGroup> optionGroups = new LinkedHashSet<>();

  public Menu(String name, String description, String imageUrl, OptionGroup basic,
      OptionGroup... groups) {
    this(null, name, description, imageUrl, Menu.LAST_PRIORITY, MenuStatus.INACTIVE, basic,
        Arrays.asList(groups));
  }

  @Builder
  public Menu(Long id, String name, String description, String imageUrl, Integer priority,
      MenuStatus status, OptionGroup basic, List<OptionGroup> additives) {
    this.id = id;
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

  public void validateOrder(String menuName, List<OptionGroupValidation> optionGroups) {
    if (!this.name.equals(menuName)) {
      throw new IllegalArgumentException("메뉴가 변경되었습니다.");
    }

    if (!isSatisfiedBy(optionGroups)) {
      throw new IllegalArgumentException("메뉴 내의 선택 내용이 변경되었습니다.");
    }
  }

  private boolean isSatisfiedBy(List<OptionGroupValidation> cartOptionGroups) {
    return cartOptionGroups.stream().allMatch(this::isSatisfiedBy);
  }

  private boolean isSatisfiedBy(OptionGroupValidation cartOptionGroup) {
    return optionGroups.stream()
        .anyMatch(optionGroup -> optionGroup.isSatisfiedBy(cartOptionGroup));
  }

}
