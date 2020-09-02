package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class AddressResponseDto {

  private String cityName;
  private String countryName;
  private String townName;
  private String administrativeBuildingName;
  private String zipCode;
  private String roadName;
  private String buildingDetailName;
  private String buildingNameForCountry;
  private Double buildingCenterPointX;
  private Double buildingCenterPointY;

  public static AddressResponseDto of(Address entity) {
    return AddressResponseDto.builder()
        .cityName(entity.getCityName())
        .countryName(entity.getCountryName())
        .townName(entity.getTownName())
        .administrativeBuildingName(entity.getAdministrativeBuildingName())
        .zipCode(entity.getZipCode())
        .roadName(entity.getRoadName())
        .buildingDetailName(entity.getBuildingDetailName())
        .buildingNameForCountry(entity.getBuildingNameForCountry())
        .buildingCenterPointX(entity.getBuildingCenterPointX())
        .buildingCenterPointY(entity.getBuildingCenterPointY())
        .build();
  }
}
