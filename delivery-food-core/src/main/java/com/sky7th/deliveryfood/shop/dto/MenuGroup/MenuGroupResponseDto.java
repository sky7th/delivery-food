package com.sky7th.deliveryfood.shop.dto.MenuGroup;

import com.sky7th.deliveryfood.shop.domain.MenuGroup;
import com.sky7th.deliveryfood.shop.domain.MenuGroup.MenuGroupStatus;
import com.sky7th.deliveryfood.shop.dto.Menu.MenuResponseDto;
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
public class MenuGroupResponseDto {

  private Long id;
  private Long shopId;
  private String name;
  private String description;
  private boolean representative;
  private int priority;
  private MenuGroupStatus status;
  private List<MenuResponseDto> menus;

  public static MenuGroupResponseDto of(MenuGroup entity) {
    return MenuGroupResponseDto.builder()
        .id(entity.getId())
        .shopId(entity.getShopId())
        .description(entity.getDescription())
        .name(entity.getName())
        .priority(entity.getPriority())
        .representative(entity.isRepresentative())
        .status(entity.getStatus())
        .menus(MenuResponseDto.of(entity.getMenus()))
        .build();
  }

  public static List<MenuGroupResponseDto> of(List<MenuGroup> entities) {
    return entities.stream().map(MenuGroupResponseDto::of).collect(Collectors.toList());
  }
}
