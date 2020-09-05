package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.shop.domain.OptionGroup;
import com.sky7th.deliveryfood.shop.domain.OptionGroup.OptionGroupStatus;
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
public class OptionGroupResponseDto {

  private Long id;
  private String name;
  private boolean basic;
  private boolean selectable;
  private int priority;
  private OptionGroupStatus status;
  private List<OptionResponseDto> options;

  public static OptionGroupResponseDto of(OptionGroup entity) {
    return OptionGroupResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .basic(entity.isBasic())
        .selectable(entity.isSelectable())
        .priority(entity.getPriority())
        .status(entity.getStatus())
        .options(OptionResponseDto.of(entity.getOptions()))
        .build();
  }

  public static List<OptionGroupResponseDto> of(List<OptionGroup> entities) {
    return entities.stream().map(OptionGroupResponseDto::of).collect(Collectors.toList());
  }
}
