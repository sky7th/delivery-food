package com.sky7th.deliveryfood.security;

import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

  private static final String ROLE = "role";

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.accessTokenExpiration}")
  private long jwtAccessTokenExpirationInMs;

  @Value("${app.jwt.refreshTokenExpiration}")
  private long jwtRefreshTokenExpirationInMs;

  private final JwtTokenValidator jwtTokenValidator;

  public String generateAccessToken(CustomUserDetails customUserDetails) {
    return generateAccessToken(customUserDetails.getId(), customUserDetails.getRole());
  }

  public String generateAccessToken(UserContext userContext) {
    return generateAccessToken(userContext.getId(), userContext.getRole());
  }

  private String generateAccessToken(Long id, UserRole userRole) {
    Claims claims = Jwts.claims().setId(Long.toString(id));
    claims.put(ROLE, userRole);
    Instant expiryDate = Instant.now().plusMillis(jwtAccessTokenExpirationInMs);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(expiryDate))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String generateRefreshToken() {
    return UUID.randomUUID().toString();
  }

  public UserContext getUserContextFromJwt(String token) {
    Claims claims = jwtTokenValidator.validateToken(token);
    Long userId = Long.parseLong(claims.getId());
    UserRole role = UserRole.valueOf(claims.get(ROLE, String.class));

    return new UserContext(userId, role);
  }

  public long getAccessTokenExpiryDuration() {
    return jwtAccessTokenExpirationInMs;
  }

  public Instant getRefreshTokenExpiryDate() {
    return Instant.now().plusMillis(jwtRefreshTokenExpirationInMs);
  }
}
