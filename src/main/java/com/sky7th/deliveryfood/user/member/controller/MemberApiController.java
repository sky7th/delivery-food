package com.sky7th.deliveryfood.user.member.controller;

import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.LoginRequestDto;
import com.sky7th.deliveryfood.user.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApiController {

  private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);

  private final AuthService authService;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity authenticateUser(LoginRequestDto loginRequestDto) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new MemberUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    logger.info("Logged in User: {}", customUserDetails.getUsername());

    String jwtAccessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
    String jwtRefreshToken = jwtTokenProvider.generateRefreshToken(customUserDetails);
    long expiryDuration = jwtTokenProvider.getExpiryDuration();

    return ResponseEntity.ok(new LoginResponseDto(jwtAccessToken, jwtRefreshToken, expiryDuration));
  }
}
