package com.sky7th.deliveryfood.address.dto.address;

import com.sky7th.deliveryfood.address.domain.Address;
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
public class AddressSearchResponseDto {

  private String buildingManagementNumber;
  private String townName;
  private Integer buildingNumber;
  private Integer buildingSideNumber;
  private String roadName;

  public static AddressSearchResponseDto of(Address entity) {
    return AddressSearchResponseDto.builder()
        .buildingManagementNumber(entity.getBuildingManagementNumber())
        .townName(entity.getTownName())
        .roadName(entity.getRoadName())
        .buildingNumber(entity.getBuildingNumber())
        .buildingSideNumber(entity.getBuildingSideNumber())
        .build();
  }

  public static List<AddressSearchResponseDto> of(List<Address> entities) {
    return entities.stream()
        .map(AddressSearchResponseDto::of)
        .collect(Collectors.toList());
  }
}
