package com.sky7th.deliveryfood.user.rider.controller;

import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.RiderUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.dto.LoginRequestDto;
import com.sky7th.deliveryfood.user.dto.LoginResponseDto;
import com.sky7th.deliveryfood.user.rider.dto.RiderRegisterRequestDto;
import com.sky7th.deliveryfood.user.rider.dto.RiderResponseDto;
import com.sky7th.deliveryfood.user.rider.service.RiderService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
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
  private final RiderService riderService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> authenticateUser(@RequestBody LoginRequestDto requestDto,
      @ModelAttribute("ipAddress") String ipAddress, HttpServletResponse response) {
    CustomUserDetails customUserDetails = authService.authenticateUser(
        new RiderUsernamePasswordAuthenticationToken(
            requestDto.getEmail(),
            requestDto.getPassword()));

    logger.info("Logged in User: {}", customUserDetails.getUsername());

    return ResponseEntity.ok(authService.createJwtToken(customUserDetails, ipAddress, response));
  }

  @PostMapping("/register")
  public ResponseEntity<RiderResponseDto> register(@RequestBody RiderRegisterRequestDto requestDto) {
    logger.info("Register Request: {}", requestDto.toString());

    return ResponseEntity.ok(riderService.register(requestDto));
  }
}
