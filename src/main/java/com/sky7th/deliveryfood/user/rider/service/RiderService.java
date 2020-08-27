package com.sky7th.deliveryfood.user.rider.service;

import com.sky7th.deliveryfood.security.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.rider.domain.Rider;
import com.sky7th.deliveryfood.user.rider.domain.RiderRepository;
import com.sky7th.deliveryfood.user.rider.service.exception.NotFoundRiderException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RiderService {

  private final RiderRepository riderRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Rider findById(Long memberId) {
    return riderRepository.findById(memberId).orElseThrow(NotFoundRiderException::new);
  }

  public Rider findByEmail(String email) {
    return riderRepository.findByEmail(email).orElseThrow(NotFoundRiderException::new);
  }

  public Rider save(RegisterRequestDto registerRequestDto) {
    if (riderRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    Rider rider = new Rider(
        registerRequestDto.getEmail(),
        bCryptPasswordEncoder.encode(registerRequestDto.getPassword()),
        registerRequestDto.getUsername());

    return riderRepository.save(rider);
  }
}
