package com.sky7th.deliveryfood.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "MENU_OPTION_GROUPS")
@Getter
public class MenuOptionGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MENU_OPTION_GROUP_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "MENU_ID")
  private Menu menu;

  @ManyToOne
  @JoinColumn(name = "OPTION_GROUP_ID")
  private OptionGroup optionGroup;

  public MenuOptionGroup(Menu menu, OptionGroup optionGroup) {
    this.menu = menu;
    this.optionGroup = optionGroup;
  }

  public MenuOptionGroup(Long id) {
    this.id = id;
  }

  private MenuOptionGroup() {
  }


}
