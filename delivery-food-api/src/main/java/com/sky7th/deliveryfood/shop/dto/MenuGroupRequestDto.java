package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MenuGroupRequestDto {

  private String name;
  private String description;

  private MenuGroupRequestDto() {
  }

  public static MenuGroup toEntity(Long shopId, MenuGroupRequestDto requestDto) {
    return MenuGroup.additive(shopId, requestDto.getName(), requestDto.getDescription());
  }
}
