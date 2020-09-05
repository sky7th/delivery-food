package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.shop.domain.OptionGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class OptionGroupRequestDto {

  private String name;
  private boolean selectable;

  private OptionGroupRequestDto() {
  }

  public static OptionGroup toEntity(OptionGroupRequestDto requestDto) {
    return OptionGroup.additive(requestDto.getName(), requestDto.selectable);
  }
}
