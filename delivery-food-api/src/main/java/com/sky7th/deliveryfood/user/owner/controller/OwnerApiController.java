package com.sky7th.deliveryfood.user.owner.controller;

import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.OwnerUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.dto.LoginRequestDto;
import com.sky7th.deliveryfood.user.dto.LoginResponseDto;
import com.sky7th.deliveryfood.user.dto.TokenRefreshRequestDto;
import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.owner.dto.OwnerDetailResponseDto;
import com.sky7th.deliveryfood.user.owner.dto.OwnerRegisterRequestDto;
import com.sky7th.deliveryfood.user.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerApiController {

  private final AuthService authService;
  private final OwnerService ownerService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto loginRequestDto) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new OwnerUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    return ResponseEntity.ok(authService.createJwtToken(customUserDetails));
  }

  @PostMapping("/register")
  public ResponseEntity<OwnerDetailResponseDto> register(@RequestBody OwnerRegisterRequestDto registerRequestDto) {
    return ResponseEntity.ok(ownerService.register(registerRequestDto));
  }

  @PostMapping("/refresh")
  public ResponseEntity<LoginResponseDto> refreshJwtToken(
      @RequestBody TokenRefreshRequestDto tokenRefreshRequestDto, UserContext userContext) {
    return ResponseEntity.ok(authService.refreshJwtToken(tokenRefreshRequestDto, userContext));
  }

  @GetMapping("/{ownerId}")
  public ResponseEntity<OwnerDetailResponseDto> show(
      @PathVariable Long ownerId, UserContext userContext) {
    return ResponseEntity.ok(ownerService.findById(ownerId, userContext));
  }
}
