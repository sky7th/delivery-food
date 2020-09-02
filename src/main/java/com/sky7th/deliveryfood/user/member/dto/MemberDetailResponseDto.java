package com.sky7th.deliveryfood.user.member.dto;

import com.sky7th.deliveryfood.generic.address.dto.MemberAddressResponseDto;
import com.sky7th.deliveryfood.user.member.domain.Member;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberDetailResponseDto {

  private Long id;
  private String email;
  private String username;
  private boolean emailVerified;
  private String imageUrl;
  private List<MemberAddressResponseDto> memberAddresses;

  public static MemberDetailResponseDto of(Member entity) {
    return new MemberDetailResponseDto(entity.getId(), entity.getEmail(), entity.getUsername(),
        entity.getEmailVerified(), entity.getImageUrl(), MemberAddressResponseDto.of(entity.getMemberAddresses()));
  }
}
