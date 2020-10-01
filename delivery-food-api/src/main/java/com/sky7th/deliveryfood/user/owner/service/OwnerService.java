package com.sky7th.deliveryfood.user.owner.service;

import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.owner.dto.OwnerDetailResponseDto;
import com.sky7th.deliveryfood.user.owner.dto.OwnerRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerService {

  private final OwnerInternalService ownerInternalService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @PreAuthorize("@ownerInternalService.isMyself(#ownerId, #userContext)")
  public OwnerDetailResponseDto findById(Long ownerId, UserContext userContext) {
    return OwnerDetailResponseDto.of(ownerInternalService.findById(ownerId));
  }

  public OwnerDetailResponseDto register(OwnerRegisterRequestDto requestDto) {
    String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());

    return OwnerDetailResponseDto.of(ownerInternalService.save(requestDto, encodedPassword));
  }
}
