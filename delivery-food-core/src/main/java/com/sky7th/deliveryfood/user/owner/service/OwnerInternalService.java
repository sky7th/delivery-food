package com.sky7th.deliveryfood.user.owner.service;

import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.exception.MismatchUserException;
import com.sky7th.deliveryfood.user.exception.NotFoundUserException;
import com.sky7th.deliveryfood.user.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.owner.domain.Owner;
import com.sky7th.deliveryfood.user.owner.domain.OwnerRepository;
import com.sky7th.deliveryfood.user.owner.dto.OwnerRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OwnerInternalService {

  private final OwnerRepository ownerRepository;

  @Transactional(readOnly = true)
  public Owner findById(Long ownerId) {
    return ownerRepository.findById(ownerId).orElseThrow(NotFoundUserException::new);
  }

  @Transactional(readOnly = true)
  public Owner findByEmail(String email) {
    return ownerRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
  }

  public Owner save(OwnerRegisterRequestDto requestDto, String encodedPassword) {
    if (ownerRepository.existsByEmail(requestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    return ownerRepository.save(new Owner(requestDto.getEmail(), encodedPassword, requestDto.getUsername()));
  }

  public void isMyself(Long ownerId, UserContext userContext) {
    if (!ownerId.equals(userContext.getId())) {
      throw new MismatchUserException();
    }
  }
}
