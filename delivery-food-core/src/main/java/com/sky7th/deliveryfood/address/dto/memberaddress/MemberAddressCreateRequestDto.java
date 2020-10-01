package com.sky7th.deliveryfood.address.dto.memberaddress;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberAddressCreateRequestDto {

  @NotBlank
  private String addressId;

  @NotBlank
  private String detailedAddress;

  private MemberAddressCreateRequestDto() {
  }
}
