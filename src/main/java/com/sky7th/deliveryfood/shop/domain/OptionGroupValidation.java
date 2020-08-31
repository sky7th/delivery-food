package com.sky7th.deliveryfood.shop.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionGroupValidation {

  private String name;
  private List<OptionValidation> optionValidations;

  @Builder
  public OptionGroupValidation(String name, List<OptionValidation> optionValidations) {
    this.name = name;
    this.optionValidations = optionValidations;
  }
}
