package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.MemberAddress;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberAddressResponseDto {

  private Long id;
  private AddressResponseDto address;
  private String detailedAddress;

  public static MemberAddressResponseDto of(MemberAddress entity) {
    return new MemberAddressResponseDto(
        entity.getId(),
        AddressResponseDto.of(entity.getAddress()),
        entity.getDetailedAddress());
  }

  public static List<MemberAddressResponseDto> of(List<MemberAddress> entities) {
    return entities.stream()
        .map(MemberAddressResponseDto::of)
        .collect(Collectors.toList());
  }
}
