package com.sky7th.deliveryfood.shop.dto.Menu;

import com.sky7th.deliveryfood.shop.dto.Option.OptionUpdateRequestDto;
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
public class MenuUpdateRequestDto {

  @NotBlank
  private String name;
  private String description;
  private String imageUrl; // TODO: 이미지 파일 받기
  private List<OptionUpdateRequestDto> basicOptions;
}
