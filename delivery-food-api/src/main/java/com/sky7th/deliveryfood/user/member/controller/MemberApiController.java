package com.sky7th.deliveryfood.user.member.controller;

import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.dto.LoginRequestDto;
import com.sky7th.deliveryfood.user.dto.LoginResponseDto;
import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.user.member.dto.MemberDetailResponseDto;
import com.sky7th.deliveryfood.user.member.dto.MemberRegisterRequestDto;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberApiController {

  private final AuthService authService;
  private final MemberService memberService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
      @ModelAttribute("ipAddress") String ipAddress, HttpServletResponse response) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new MemberUsernamePasswordAuthenticationToken(
            loginRequestDto.getEmail(),
            loginRequestDto.getPassword()));

    return ResponseEntity.ok(authService.createJwtToken(customUserDetails, ipAddress, response));
  }

  @PostMapping("/register")
  public ResponseEntity<MemberDetailResponseDto> register(@RequestBody MemberRegisterRequestDto registerRequestDto) {
    return ResponseEntity.ok(memberService.register(registerRequestDto));
  }

  @PostMapping("/resend/verification-email")
  public ResponseEntity<Void> registerMailResend(@RequestBody LoginRequestDto loginRequestDto) {
    memberService.resendVerificationEmail(loginRequestDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{memberId}")
  public ResponseEntity<MemberDetailResponseDto> show(
      @PathVariable Long memberId, UserContext userContext) {
    return ResponseEntity.ok(memberService.findById(memberId, userContext));
  }
}
