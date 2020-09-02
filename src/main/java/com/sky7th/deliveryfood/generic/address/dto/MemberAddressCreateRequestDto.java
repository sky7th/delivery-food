package com.sky7th.deliveryfood.generic.address.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberAddressCreateRequestDto {

  @NotBlank
  private String addressCode;

  @NotBlank
  private String detailedAddress;

  private MemberAddressCreateRequestDto() {
  }
}
