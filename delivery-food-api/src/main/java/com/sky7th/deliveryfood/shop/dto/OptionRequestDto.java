package com.sky7th.deliveryfood.shop.dto;

import com.sky7th.deliveryfood.generic.money.domain.Money;
import com.sky7th.deliveryfood.shop.domain.Option;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class OptionRequestDto {

  private String name;
  private Long price;

  private OptionRequestDto() {
  }

  public static Option toEntity(OptionRequestDto requestDto) {
    return new Option(null, requestDto.getName(), Money.wons(requestDto.getPrice()));
  }

  public static List<Option> toEntities(List<OptionRequestDto> requestDto) {
    return requestDto.stream()
        .map(OptionRequestDto::toEntity)
        .collect(Collectors.toList());
  }
}
