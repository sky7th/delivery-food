package com.sky7th.deliveryfood.shop.domain;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OPTION_GROUPS")
@NoArgsConstructor
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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "optionGroup", fetch = FetchType.EAGER)
  private Set<Option> options = new LinkedHashSet<>();

  public static OptionGroup basic(List<Option> options) {
    return new OptionGroup(null, FIRST_OPTION_GROUP_NAME, true, false, Menu.FIRST_PRIORITY,
        OptionGroupStatus.INACTIVE, options);
  }

  public static OptionGroup additive(String name, boolean selectable) {
    return new OptionGroup(null, name, false, selectable, Menu.LAST_PRIORITY,
        OptionGroupStatus.INACTIVE, Collections.emptyList());
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
    addOptions(options);
  }

  private void addOptions(List<Option> options) {
    options.forEach(option -> {
      option.setOptionGroup(this);
    });
    this.options.addAll(options);
  }

  public void updateBasicOptions(List<Option> requestOptions) {
    this.options.removeIf(option -> !requestOptions.contains(option));

    requestOptions.forEach(requestOption -> {
      updatExistedOptions(requestOption);
      addNewOptions(requestOption);
    });
  }

  private void updatExistedOptions(Option requestOption) {
    this.options.stream()
      .filter(option -> option.equals(requestOption))
      .forEach(option -> option.update(requestOption.getName(), requestOption.getPrice()));
  }

  private void addNewOptions(Option requestOption) {
    if (!this.options.contains(requestOption)) {
      requestOption.setOptionGroup(this);
      this.options.add(requestOption);
    }
  }

  public boolean isSatisfiedBy(OptionGroupValidation optionGroupValidation) {
    if (!name.equals(optionGroupValidation.getName())) {
      return false;
    }

    List<OptionValidation> satisfiedOptionValidations = satisfied(
        optionGroupValidation.getOptionValidations());
    if (satisfiedOptionValidations.isEmpty() || (satisfiedOptionValidations.size()
        != optionGroupValidation.getOptionValidations()
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

  public OptionGroup(Long id) {
    this.id = id;
  }
}
