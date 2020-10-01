package com.sky7th.deliveryfood.security.service;

import com.sky7th.deliveryfood.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserValidateService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public void validateUser(User user, String enteredPassword) {
    if (!bCryptPasswordEncoder.matches(enteredPassword, user.getPassword())) {
      throw new BadCredentialsException(user.getEmail());

    } else if (!user.getActive()) {
      throw new LockedException(user.getEmail());
    }
  }
}