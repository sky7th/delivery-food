package com.sky7th.deliveryfood.user.member.controller;

import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.LoginRequestDto;
import com.sky7th.deliveryfood.user.LoginResponseDto;
import com.sky7th.deliveryfood.user.RegisterRequestDto;
import com.sky7th.deliveryfood.user.TokenRefreshRequestDto;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.member.dto.MemberResponseDto;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApiController {

  private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);

  private final AuthService authService;
  private final MemberService memberService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new MemberUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    logger.info("Logged in User: {}", customUserDetails.getUsername());

    return ResponseEntity.ok(authService.createJwtToken(customUserDetails));
  }

  @PostMapping("/register")
  public ResponseEntity<MemberResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
    logger.info("Register Request: {}", registerRequestDto.toString());

    return ResponseEntity.ok(memberService.register(registerRequestDto));
  }

  @PostMapping("/refresh")
  public ResponseEntity<LoginResponseDto> refreshJwtToken(
      @RequestBody TokenRefreshRequestDto tokenRefreshRequestDto, UserContext userContext) {
    logger.info("Refresh Token Request: {}", tokenRefreshRequestDto.getRefreshToken());

    return ResponseEntity.ok(authService.refreshJwtToken(tokenRefreshRequestDto, userContext));
  }

  @GetMapping("/register/confirm")
  public ResponseEntity<MemberResponseDto> registerConfirm(@RequestParam("key") String key) {

    return ResponseEntity.ok(memberService.emailVerify(key));
  }
}
