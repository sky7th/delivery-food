package com.sky7th.deliveryfood.user.owner.service;

import com.sky7th.deliveryfood.security.exception.UserAlreadyInUseException;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.owner.domain.Owner;
import com.sky7th.deliveryfood.user.owner.domain.OwnerRepository;
import com.sky7th.deliveryfood.user.owner.dto.OwnerResponseDto;
import com.sky7th.deliveryfood.user.owner.service.exception.NotFoundOwnerException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OwnerService {

  private final OwnerRepository ownerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Owner findById(Long memberId) {
    return ownerRepository.findById(memberId).orElseThrow(NotFoundOwnerException::new);
  }

  public Owner findByEmail(String email) {
    return ownerRepository.findByEmail(email).orElseThrow(NotFoundOwnerException::new);
  }

  public OwnerResponseDto save(RegisterRequestDto registerRequestDto) {
    if (ownerRepository.existsByEmail(registerRequestDto.getEmail())) {
      throw new UserAlreadyInUseException();
    }

    Owner owner = new Owner(
        registerRequestDto.getEmail(),
        bCryptPasswordEncoder.encode(registerRequestDto.getPassword()),
        registerRequestDto.getUsername());

    return OwnerResponseDto.of(ownerRepository.save(owner));
  }
}
