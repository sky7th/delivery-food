package com.sky7th.deliveryfood.shop.dto.Menu;

import com.sky7th.deliveryfood.shop.domain.Menu;
import com.sky7th.deliveryfood.shop.domain.OptionGroup;
import com.sky7th.deliveryfood.shop.dto.Option.OptionRequestDto;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class MenuRequestDto {

  @NotBlank
  private String name;
  private String description;
  private String imageUrl;
  private List<OptionRequestDto> basicOptions;

  public static Menu toEntity(Long menuGroupId, MenuRequestDto requestDto) {
    return new Menu(
        menuGroupId,
        requestDto.getName(),
        requestDto.getDescription(),
        requestDto.getImageUrl(),
        OptionGroup.basic(OptionRequestDto.toEntities(requestDto.getBasicOptions())));
  }
}
