package com.sky7th.deliveryfood.security.service;

import com.sky7th.deliveryfood.security.exception.UserLoginException;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final AuthenticationManager authenticationManager;

  public CustomUserDetails authenticateUser(
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    Authentication authentication = Optional
        .ofNullable(authenticationManager.authenticate(usernamePasswordAuthenticationToken))
        .orElseThrow(UserLoginException::new);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return (CustomUserDetails) authentication.getPrincipal();
  }
}