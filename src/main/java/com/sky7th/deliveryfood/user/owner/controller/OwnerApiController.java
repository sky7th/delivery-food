package com.sky7th.deliveryfood.user.owner.controller;

import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.OwnerUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.LoginRequestDto;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.owner.dto.OwnerResponseDto;
import com.sky7th.deliveryfood.user.owner.service.OwnerService;
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
@RequestMapping("/owners")
public class OwnerApiController {

  private static final Logger logger = LoggerFactory.getLogger(
      OwnerApiController.class);

  private final AuthService authService;
  private final JwtTokenProvider jwtTokenProvider;
  private final OwnerService ownerService;

  @PostMapping("/login")
  public ResponseEntity authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new OwnerUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    logger.info("Logged in User: {}", customUserDetails.getUsername());

    return ResponseEntity.ok(authService.createJwtToken(customUserDetails));
  }

  @PostMapping("/register")
  public ResponseEntity<OwnerResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
    logger.info("Register Request: {}", registerRequestDto.toString());

    return ResponseEntity.ok(ownerService.save(registerRequestDto));
  }
}
