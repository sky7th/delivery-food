package com.sky7th.deliveryfood.user.member.controller;

import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.LoginRequestDto;
import com.sky7th.deliveryfood.user.LoginResponseDto;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.TokenRefreshRequestDto;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.member.service.MemberService;
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
@RequestMapping("/members")
public class MemberApiController {

  private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);

  private final AuthService authService;
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberService memberService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new MemberUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    logger.info("Logged in User: {}", customUserDetails.getUsername());

    String jwtAccessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
    String jwtRefreshToken = jwtTokenProvider.generateRefreshToken();
    long expiryDuration = jwtTokenProvider.getExpiryDuration();

    return ResponseEntity.ok(new LoginResponseDto(jwtAccessToken, jwtRefreshToken, expiryDuration));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {
    logger.info("Register Request: {}", registerRequestDto.toString());

    return ResponseEntity.ok(memberService.save(registerRequestDto));
  }

  @PostMapping("/refresh")
  public ResponseEntity<LoginResponseDto> refreshJwtToken(@RequestBody TokenRefreshRequestDto tokenRefreshRequestDto,
      UserContext userContext) {
    logger.info("Refresh Token Request: {}", tokenRefreshRequestDto.getRefreshToken());

    String jwtAccessToken = authService.refreshJwtToken(tokenRefreshRequestDto, userContext);
    long expiryDuration = jwtTokenProvider.getExpiryDuration();

    return ResponseEntity
        .ok(new LoginResponseDto(jwtAccessToken, tokenRefreshRequestDto.getRefreshToken(), expiryDuration));
  }
}
