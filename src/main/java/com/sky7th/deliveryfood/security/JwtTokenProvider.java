package com.sky7th.deliveryfood.security;

import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.accessTokenExpiration}")
  private long jwtAccessTokenExpirationInMs;

  @Value("${app.jwt.refreshTokenExpiration}")
  private long jwtRefreshTokenExpirationInMs;

  public String generateAccessToken(CustomUserDetails customUserDetails) {
    Claims claims = Jwts.claims().setId(Long.toString(customUserDetails.getId()));
    claims.put("role", customUserDetails.getRole().getRoleName());
    Instant expiryDate = Instant.now().plusMillis(jwtAccessTokenExpirationInMs);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(expiryDate))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String generateRefreshToken(CustomUserDetails customUserDetails) {
    Claims claims = Jwts.claims().setId(Long.toString(customUserDetails.getId()));
    Instant expiryDate = Instant.now().plusMillis(jwtRefreshTokenExpirationInMs);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(expiryDate))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public UserContext getUserContextFromJwt(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    UserRole role = UserRole.valueOf((String) claims.get("role"));

    return new UserContext(Long.parseLong(claims.getId()), role);
  }

  public long getExpiryDuration() {
    return jwtAccessTokenExpirationInMs;
  }
}
