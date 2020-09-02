package com.sky7th.deliveryfood.generic.address.dto;

import com.sky7th.deliveryfood.generic.address.domain.MemberAddress;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberAddressResponseDtos {

  private List<MemberAddressResponseDto> memberAddresses;

  public static MemberAddressResponseDtos of(List<MemberAddress> entities) {
    return new MemberAddressResponseDtos(MemberAddressResponseDto.of(entities));
  }
}
