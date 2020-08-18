package com.sky7th.deliveryfood.shop.domain;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class OptionGroupValidation {

  private String name;
  private List<Option> options;

  @Builder
  public OptionGroupValidation(String name, List<Option> options) {
    this.name = name;
    this.options = options;
  }
}
