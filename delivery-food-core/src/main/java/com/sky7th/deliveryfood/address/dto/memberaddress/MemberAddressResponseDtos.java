package com.sky7th.deliveryfood.address.dto.memberaddress;

import com.sky7th.deliveryfood.address.domain.MemberAddress;
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
