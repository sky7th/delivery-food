package com.sky7th.deliveryfood.shop.dto.Option;

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
public class OptionUpdateRequestDto {

  private Long id;
  private String name;
  private Long price;

  private OptionUpdateRequestDto() {
  }

  private static Option toEntity(OptionUpdateRequestDto requestDto) {
    return new Option(requestDto.getId(), null, requestDto.getName(), Money.wons(requestDto.getPrice()));
  }

  public static List<Option> toEntities(List<OptionUpdateRequestDto> requestDto) {
    return requestDto.stream()
        .map(OptionUpdateRequestDto::toEntity)
        .collect(Collectors.toList());
  }
}
