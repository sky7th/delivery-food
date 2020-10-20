package com.sky7th.deliveryfood.address.dto.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AddressSearchRequestDto {

  public enum AddressSearchType { town, road, building }

  private AddressSearchType searchType;
  private String townName = "";
  private String buildingNumber = "";
  private String buildingSideNumber = "";
  private String roadName = "";
  private String buildingName = "";
}
