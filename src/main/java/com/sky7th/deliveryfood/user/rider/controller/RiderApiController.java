package com.sky7th.deliveryfood.user.rider.controller;

import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.RiderUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.LoginRequestDto;
import com.sky7th.deliveryfood.user.LoginResponseDto;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.rider.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/riders")
public class RiderApiController {

  private static final Logger logger = LoggerFactory.getLogger(
      RiderApiController.class);

  private final AuthService authService;
  private final JwtTokenProvider jwtTokenProvider;
  private final RiderService riderService;

  @PostMapping("/login")
  public ResponseEntity authenticateUser(LoginRequestDto loginRequestDto) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new RiderUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    logger.info("Logged in User: {}", customUserDetails.getUsername());

    String jwtAccessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
    String jwtRefreshToken = jwtTokenProvider.generateRefreshToken(customUserDetails);
    long expiryDuration = jwtTokenProvider.getExpiryDuration();

    return ResponseEntity.ok(new LoginResponseDto(jwtAccessToken, jwtRefreshToken, expiryDuration));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {
    logger.info("Register Request: {}", registerRequestDto.toString());

    return ResponseEntity.ok(riderService.save(registerRequestDto));
  }
}
