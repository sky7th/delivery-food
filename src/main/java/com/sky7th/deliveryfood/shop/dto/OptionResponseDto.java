package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.shop.domain.Option;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class OptionResponseDto {

  private Long id;
  private String name;
  private Long price;

  public static OptionResponseDto of(Option entity) {
    return new OptionResponseDto(entity.getId(), entity.getName(),
        entity.getPrice().getAmount().longValue());
  }

  public static List<OptionResponseDto> of(Set<Option> entities) {
    return entities.stream().map(OptionResponseDto::of).collect(Collectors.toList());
  }
}
