package com.sky7th.deliveryfood.user.rider.service;

import com.sky7th.deliveryfood.security.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.rider.domain.Rider;
import com.sky7th.deliveryfood.user.rider.domain.RiderRepository;
import com.sky7th.deliveryfood.user.rider.dto.RiderResponseDto;
import com.sky7th.deliveryfood.user.rider.service.exception.NotFoundRiderException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RiderService {

  private final RiderRepository riderRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional(readOnly = true)
  public Rider findById(Long memberId) {
    return riderRepository.findById(memberId).orElseThrow(NotFoundRiderException::new);
  }

  @Transactional(readOnly = true)
  public Rider findByEmail(String email) {
    return riderRepository.findByEmail(email).orElseThrow(NotFoundRiderException::new);
  }

  public RiderResponseDto save(RegisterRequestDto registerRequestDto) {
    if (riderRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    Rider rider = new Rider(
        registerRequestDto.getEmail(),
        bCryptPasswordEncoder.encode(registerRequestDto.getPassword()),
        registerRequestDto.getUsername());

    return RiderResponseDto.of(riderRepository.save(rider));
  }
}
