package com.sky7th.deliveryfood.security.filter;


import com.sky7th.deliveryfood.security.JwtTokenProvider;
import com.sky7th.deliveryfood.security.JwtTokenValidator;
import com.sky7th.deliveryfood.security.service.UserService;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserContext;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
  private final JwtTokenValidator jwtTokenValidator;
  private final UserService userService;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt) && jwtTokenValidator.validateToken(jwt)) {
        UserContext userContext = jwtTokenProvider.getUserContextFromJwt(jwt);
        CustomUserDetails customUserDetails = userService.loadUserByIdAndRole(userContext);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            customUserDetails.getId(), null, customUserDetails.getAuthorities());

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
}