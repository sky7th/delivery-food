package com.sky7th.deliveryfood.shop.dto.MenuGroup;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MenuGroupResponseDtos {

  private List<MenuGroupResponseDto> menuGroups;

  public static MenuGroupResponseDtos of(List<MenuGroup> entities) {
    return new MenuGroupResponseDtos(MenuGroupResponseDto.of(entities));
  }
}
