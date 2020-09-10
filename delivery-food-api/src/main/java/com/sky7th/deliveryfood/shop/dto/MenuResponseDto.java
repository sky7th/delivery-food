package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.Menu.MenuStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class MenuResponseDto {

  private Long id;
  private Long menuGroupId;
  private String name;
  private String description;
  private String imageUrl;
  private int priority;
  private MenuStatus status;
  private List<OptionGroupResponseDto> optionGroups;

  public static MenuResponseDto of(Menu menu) {
    return MenuResponseDto.builder()
        .id(menu.getId())
        .menuGroupId(menu.getMenuGroup().getId())
        .name(menu.getName())
        .description(menu.getDescription())
        .imageUrl(menu.getImageUrl())
        .priority(menu.getPriority())
        .status(menu.getStatus())
        .optionGroups(menu.getMenuOptionGroups().stream()
            .map(menuOptionGroup -> OptionGroupResponseDto.of(menuOptionGroup.getOptionGroup()))
            .collect(Collectors.toList()))
        .build();
  }

  public static List<MenuResponseDto> of(List<Menu> menus) {
    return menus.stream().map(MenuResponseDto::of).collect(Collectors.toList());
  }
}
