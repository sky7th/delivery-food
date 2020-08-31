package com.sky7th.deliveryfood.security.service;

import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.exception.UserLoginException;
import com.sky7th.deliveryfood.security.refreshtoken.RefreshToken;
import com.sky7th.deliveryfood.security.refreshtoken.RefreshTokenService;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.LoginResponseDto;
import com.sky7th.deliveryfood.user.TokenRefreshRequestDto;
import com.sky7th.deliveryfood.user.User;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final RefreshTokenService refreshTokenService;
  private final JwtTokenProvider jwtTokenProvider;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public void validateUser(User user, String enteredPassword) {
    if (!bCryptPasswordEncoder.matches(enteredPassword, user.getPassword())) {
      throw new BadCredentialsException(user.getEmail());

    } else if (!user.getActive()) {
      throw new LockedException(user.getEmail());
    }

    if (user.getRole() == UserRole.ROLE_MEMBER) {
      Member member = (Member) user;

      if (!member.getEmailVerified()) {
        user.setRole(UserRole.ROLE_GUEST_MEMBER);
      }
    }
  }

  public CustomUserDetails authenticateUser(
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
    Authentication authentication = Optional
        .ofNullable(authenticationManager.authenticate(usernamePasswordAuthenticationToken))
        .orElseThrow(UserLoginException::new);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return (CustomUserDetails) authentication.getPrincipal();
  }

  public LoginResponseDto createJwtToken(CustomUserDetails customUserDetails) {
    String jwtAccessToken = jwtTokenProvider.generateAccessToken(customUserDetails);
    String jwtRefreshToken = jwtTokenProvider.generateRefreshToken();
    refreshTokenService.save(new RefreshToken(jwtRefreshToken, jwtTokenProvider.getRefreshTokenExpiryDate()));

    return new LoginResponseDto(jwtAccessToken, jwtRefreshToken,
        jwtTokenProvider.getAccessTokenExpiryDuration());
  }

  public LoginResponseDto refreshJwtToken(TokenRefreshRequestDto tokenRefreshRequestDto, UserContext userContext) {
    RefreshToken refreshToken = refreshTokenService.findById(tokenRefreshRequestDto.getRefreshToken());
    refreshToken.verifyExpiration();

    String jwtAccessToken = jwtTokenProvider.generateAccessToken(userContext);

    return new LoginResponseDto(jwtAccessToken, tokenRefreshRequestDto.getRefreshToken(),
        jwtTokenProvider.getAccessTokenExpiryDuration());
  }
}