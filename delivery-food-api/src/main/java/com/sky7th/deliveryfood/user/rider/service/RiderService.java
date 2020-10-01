package com.sky7th.deliveryfood.user.rider.service;

import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.rider.dto.RiderDetailResponseDto;
import com.sky7th.deliveryfood.user.rider.dto.RiderRegisterRequestDto;
import com.sky7th.deliveryfood.user.rider.dto.RiderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RiderService {

  private final RiderInternalService riderInternalService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @PreAuthorize("@riderInternalService.isMyself(#riderId, #userContext)")
  public RiderDetailResponseDto findById(Long riderId, UserContext userContext) {
    return RiderDetailResponseDto.of(riderInternalService.findById(riderId));
  }

  public RiderResponseDto register(RiderRegisterRequestDto requestDto) {
    String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());

    return RiderResponseDto.of(riderInternalService.save(requestDto, encodedPassword));
  }
}
