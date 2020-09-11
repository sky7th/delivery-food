package com.sky7th.deliveryfood.user.member.controller;

import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressCreateRequestDto;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressResponseDto;
import com.sky7th.deliveryfood.address.dto.memberaddress.MemberAddressResponseDtos;
import com.sky7th.deliveryfood.address.service.MemberAddressService;
import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.dto.LoginRequestDto;
import com.sky7th.deliveryfood.user.dto.LoginResponseDto;
import com.sky7th.deliveryfood.user.dto.TokenRefreshRequestDto;
import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.member.dto.MemberDetailResponseDto;
import com.sky7th.deliveryfood.user.member.dto.MemberRegisterRequestDto;
import com.sky7th.deliveryfood.user.member.dto.MemberResponseDto;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApiController {

  private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);

  private final AuthService authService;
  private final MemberService memberService;
  private final MemberAddressService memberAddressService;

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
  public ResponseEntity<MemberResponseDto> register(@RequestBody MemberRegisterRequestDto registerRequestDto) {
    logger.info("Register Request: {}", registerRequestDto.toString());

    return ResponseEntity.ok(memberService.register(registerRequestDto));
  }

  @PostMapping("/refresh")
  public ResponseEntity<LoginResponseDto> refreshJwtToken(
      @RequestBody TokenRefreshRequestDto tokenRefreshRequestDto, UserContext userContext) {
    logger.info("Refresh Token Request: {}", tokenRefreshRequestDto.getRefreshToken());

    return ResponseEntity.ok(authService.refreshJwtToken(tokenRefreshRequestDto, userContext));
  }

  @PostMapping("/resend/verificationEmail")
  public ResponseEntity<MemberResponseDto> registerMailResend(@RequestBody LoginRequestDto loginRequestDto) {
    memberService.resendVerificationEmail(loginRequestDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{memberId}")
  public ResponseEntity<MemberDetailResponseDto> show(
      @PathVariable Long memberId, UserContext userContext) {
    return ResponseEntity.ok(memberService.findById(memberId, userContext));
  }

  @GetMapping("/members/{memberId}/address")
  public ResponseEntity<MemberAddressResponseDtos> list(@PathVariable Long memberId, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.findAllByMemberId(memberId, userContext));
  }

  @PostMapping("/members/{memberId}/address")
  public ResponseEntity<MemberAddressResponseDto> createMemberAddress(@PathVariable Long memberId,
      @RequestBody MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.save(memberId, requestDto, userContext));
  }

  @PutMapping("/members/{memberId}/address/{memberAddressId}")
  public ResponseEntity<MemberAddressResponseDto> updateMemberAddress(@PathVariable Long memberId,
      @PathVariable Long memberAddressId, @RequestBody MemberAddressCreateRequestDto requestDto, UserContext userContext) {
    return ResponseEntity.ok(memberAddressService.update(memberId, memberAddressId, requestDto, userContext));
  }

  @DeleteMapping("/members/{memberId}/address/{memberAddressId}")
  public ResponseEntity<MemberAddressResponseDto> deleteMemberAddress(@PathVariable Long memberId,
      @PathVariable Long memberAddressId, UserContext userContext) {
    memberAddressService.delete(memberId, memberAddressId, userContext);
    return ResponseEntity.ok().build();
  }
}
