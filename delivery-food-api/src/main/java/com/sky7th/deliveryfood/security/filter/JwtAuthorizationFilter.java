package com.sky7th.deliveryfood.security.filter;


import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.exception.JwtNotMeException;
import com.sky7th.deliveryfood.user.dto.UserContext;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

  @Value("${app.jwt.header.name}")
  private String tokenRequestHeader;

  @Value("${app.jwt.header.prefix}")
  private String tokenRequestHeaderPrefix;

  private final JwtTokenProvider jwtTokenProvider;
  private final LiteDeviceResolver liteDeviceResolver;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt)) {
        Device clientDevice = liteDeviceResolver.resolveDevice(request);
        String clientIp = getClientIp(request);
        String clientUuid = getClientUuid(request);

        UserContext userContext = jwtTokenProvider.getUserContextFromJwt(jwt, clientIp, clientUuid, clientDevice);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            userContext, null, Arrays.asList(new SimpleGrantedAuthority(userContext.getRole().toString())));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception ex) {
      log.error("Failed to set user authentication in security context: ", ex);
      throw ex;
    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(tokenRequestHeader);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenRequestHeaderPrefix)) {
      log.info("Extracted Token: " + bearerToken);
      return bearerToken.replace(tokenRequestHeaderPrefix, "");
    }
    return null;
  }

  public String getClientIp(HttpServletRequest request) {
    String clientIp = request.getHeader("X-Forwarded-For");

    if (clientIp == null) {
      clientIp = request.getRemoteAddr();
    }

    return clientIp;
  }

  public String getClientUuid(HttpServletRequest request) {
    String clientUuid = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(JwtTokenProvider.CLIENT_UUID)) {
          clientUuid = cookie.getValue();
        }
      }
    }

    if (clientUuid == null) {
      throw new JwtNotMeException();
    }

    return clientUuid;
  }
}