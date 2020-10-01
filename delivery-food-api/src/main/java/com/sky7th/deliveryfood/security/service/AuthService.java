package com.sky7th.deliveryfood.security.service;

import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.exception.UserLoginException;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.dto.LoginResponseDto;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
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
  private final JwtTokenProvider jwtTokenProvider;

  public CustomUserDetails authenticateUser(
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    Authentication authentication = Optional
        .ofNullable(authenticationManager.authenticate(usernamePasswordAuthenticationToken))
        .orElseThrow(UserLoginException::new);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return (CustomUserDetails) authentication.getPrincipal();
  }

  public LoginResponseDto createJwtToken(CustomUserDetails customUserDetails, String ipAddress, HttpServletResponse response) {
    String jwtAccessToken = jwtTokenProvider.generateAccessToken(customUserDetails, ipAddress, response);

    return new LoginResponseDto(jwtAccessToken, jwtTokenProvider.getAccessTokenExpiryDuration());
  }
}