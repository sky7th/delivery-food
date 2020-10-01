package com.sky7th.deliveryfood.security;

import com.sky7th.deliveryfood.security.exception.JwtNotMeException;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.dto.UserContext;
import com.sky7th.deliveryfood.utils.resolver.AES256Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

  public static final String ROLE = "role";
  public static final String CLIENT_IP = "ip";
  public static final String CLIENT_UUID = "uuid";

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.accessTokenExpiration}")
  private long jwtAccessTokenExpirationInMs;

  private final JwtTokenValidator jwtTokenValidator;

  public String generateAccessToken(CustomUserDetails customUserDetails, String ipAddress, HttpServletResponse response) {
    return generateAccessToken(customUserDetails.getId(), customUserDetails.getRole(), ipAddress, response);
  }

  private String generateAccessToken(Long id, UserRole userRole, String ipAddress, HttpServletResponse response) {
    Claims claims = Jwts.claims().setId(Long.toString(id));
    claims.put(ROLE, userRole);

    String encryptedIpAddress = AES256Utils.encrypt(jwtSecret, ipAddress);
    claims.put(CLIENT_IP, encryptedIpAddress);

    String uuid = UUID.randomUUID().toString();
    response.addCookie(makeUuidCookie(uuid));

    String encryptedUuid = AES256Utils.encrypt(jwtSecret, uuid);
    claims.put(CLIENT_UUID, encryptedUuid);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plusMillis(jwtAccessTokenExpirationInMs)))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public Cookie makeUuidCookie(String uuid) {
    Cookie cookie = new Cookie("uuid", uuid);
    cookie.setMaxAge((int) jwtAccessTokenExpirationInMs / 1000);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    return cookie;
  }

  public UserContext getUserContextFromJwt(String token, String clientIp, String clientUuid, Device clientDevice) {
    Claims claims = jwtTokenValidator.validateToken(token);

    String encryptedIp = claims.get(CLIENT_IP, String.class);
    String encryptedUUID = claims.get(CLIENT_UUID, String.class);
    String ip = AES256Utils.decrypt(jwtSecret, encryptedIp);
    String uuid = AES256Utils.decrypt(jwtSecret, encryptedUUID);

    if (((clientDevice.isMobile() || clientDevice.isTablet()) && !clientUuid.equals(uuid))
        || (clientDevice.isNormal() && (!clientIp.equals(ip) || !clientUuid.equals(uuid)))) {
      throw new JwtNotMeException();
    }

    Long userId = Long.parseLong(claims.getId());
    UserRole role = UserRole.valueOf(claims.get(ROLE, String.class));

    return new UserContext(userId, role);
  }

  public long getAccessTokenExpiryDuration() {
    return jwtAccessTokenExpirationInMs;
  }
}
