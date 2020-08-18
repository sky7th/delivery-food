package com.sky7th.deliveryfood.shop.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "OPTION_GROUPS")
@NoArgsConstructor
@Getter
public class OptionGroup {

  public enum OptionGroupStatus {ACTIVE, INACTIVE, DELETED}

  public static final String FIRST_OPTION_GROUP_NAME = "기본";

  @Id
  @GeneratedValue
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
  private OptionGroupStatus status;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "OPTION_GROUP_ID")
  private List<Option> options = new ArrayList<>();

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

  public String getName() {
    return name;
  }

  public boolean isSatisfiedBy(OptionGroupValidation cartOptionGroup) {
    if (!name.equals(cartOptionGroup.getName())) {
      return false;
    }

    List<Option> satisfiedOptions = satisfied(cartOptionGroup.getOptions());
    if (satisfiedOptions.isEmpty() || (satisfiedOptions.size() != cartOptionGroup.getOptions()
        .size())) {
      return false;
    }

    if (!selectable && satisfiedOptions.size() > 1) {
      throw new IllegalArgumentException(
          String.format("다중 선택이 불가능한 메뉴 옵션 그룹입니다. (메뉴 옵션 그룹명: %s", this.name));
    }

    return true;
  }

  private List<Option> satisfied(List<Option> cartOptions) {
    return this.options
        .stream()
        .flatMap(option -> cartOptions.stream().filter(option::isSatisfiedBy))
        .collect(toList());
  }
}
