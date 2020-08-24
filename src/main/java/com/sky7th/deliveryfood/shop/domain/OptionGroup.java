package com.sky7th.deliveryfood.shop.domain;

import static java.util.stream.Collectors.toList;

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

@Entity
@Table(name = "OPTION_GROUPS")
@Getter
public class OptionGroup {

  public enum OptionGroupStatus {ACTIVE, INACTIVE, DELETED}

  public static final String FIRST_OPTION_GROUP_NAME = "기본";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OPTION_GROUP_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "BASIC")
  private boolean basic;

  @Column(name = "SELECTABLE")
  private boolean selectable;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private OptionGroupStatus status;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "OPTION_GROUP_ID")
  private Set<Option> options = new LinkedHashSet<>();

  public static OptionGroup basic(Option... options) {
    return new OptionGroup(FIRST_OPTION_GROUP_NAME, true, false, Menu.FIRST_PRIORITY, options);
  }

  public static OptionGroup additive(String name, boolean selectable, Option... options) {
    return new OptionGroup(name, false, selectable, Menu.LAST_PRIORITY, options);
  }

  private OptionGroup(String name, boolean basic, boolean selectable, Integer priority,
      Option... options) {
    this(null, name, selectable, basic, priority, OptionGroupStatus.INACTIVE,
        Arrays.asList(options));
  }

  @Builder
  public OptionGroup(Long id, String name, boolean basic, boolean selectable,
      Integer priority, OptionGroupStatus status, List<Option> options) {
    this.id = id;
    this.name = name;
    this.basic = basic;
    this.selectable = selectable;
    this.priority = priority;
    this.status = status;
    this.options.addAll(options);
  }

  private OptionGroup() {
  }

  public String getName() {
    return name;
  }

  public boolean isSatisfiedBy(OptionGroupValidation optionGroupValidation) {
    if (!name.equals(optionGroupValidation.getName())) {
      return false;
    }

    List<OptionValidation> satisfiedOptionValidations = satisfied(optionGroupValidation.getOptionValidations());
    if (satisfiedOptionValidations.isEmpty() || (satisfiedOptionValidations.size() != optionGroupValidation.getOptionValidations()
        .size())) {
      return false;
    }

    if (!selectable && satisfiedOptionValidations.size() > 1) {
      throw new IllegalArgumentException(
          String.format("다중 선택이 불가능한 메뉴 옵션 그룹입니다. (메뉴 옵션 그룹명: %s", this.name));
    }

    return true;
  }

  private List<OptionValidation> satisfied(List<OptionValidation> optionValidations) {
    return this.options
        .stream()
        .flatMap(option -> optionValidations.stream().filter(option::isSatisfiedBy))
        .collect(toList());
  }
}
