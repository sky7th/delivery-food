package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.MemberAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberAddressResponseDto {

  private String addressCode;
  private String detailedAddress;

  public static MemberAddressResponseDto of(MemberAddress entity) {
    return new MemberAddressResponseDto(entity.getAddress().getBuildingManagementNumber(), entity.getDetailedAddress());
  }
}
