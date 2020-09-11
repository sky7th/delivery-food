package com.sky7th.deliveryfood.user.rider.service;

import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.exception.MismatchUserException;
import com.sky7th.deliveryfood.user.exception.NotFoundUserException;
import com.sky7th.deliveryfood.user.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.rider.domain.Rider;
import com.sky7th.deliveryfood.user.rider.domain.RiderRepository;
import com.sky7th.deliveryfood.user.rider.dto.RiderRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RiderInternalService {

  private final RiderRepository riderRepository;

  @Transactional(readOnly = true)
  public Rider findById(Long riderId) {
    return riderRepository.findById(riderId).orElseThrow(NotFoundUserException::new);
  }

  @Transactional(readOnly = true)
  public Rider findByEmail(String email) {
    return riderRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
  }

  public Rider save(RiderRegisterRequestDto requestDto, String encodedPassword) {
    if (riderRepository.existsByEmail(requestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    return riderRepository.save(new Rider(requestDto.getEmail(), encodedPassword, requestDto.getUsername()));
  }

  public void isMyself(Long riderId, UserContext userContext) {
    if (!riderId.equals(userContext.getId())) {
      throw new MismatchUserException();
    }
  }
}
