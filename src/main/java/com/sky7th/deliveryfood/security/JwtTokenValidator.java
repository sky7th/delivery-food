package com.sky7th.deliveryfood.security;


import com.sky7th.deliveryfood.security.exception.InvalidTokenRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenValidator.class);

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);

    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
      throw new InvalidTokenRequestException("JWT", authToken, "Incorrect signature");

    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
      throw new InvalidTokenRequestException("JWT", authToken, "Malformed jwt token");

    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
      throw new InvalidTokenRequestException("JWT", authToken, "Token expired. Refresh required");

    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
      throw new InvalidTokenRequestException("JWT", authToken, "Unsupported JWT token");

    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
      throw new InvalidTokenRequestException("JWT", authToken, "Illegal argument token");
    }
    return true;
  }
}
