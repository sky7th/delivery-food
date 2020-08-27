package com.sky7th.deliveryfood.security;

import com.sky7th.deliveryfood.security.exception.NotLoginException;
import com.sky7th.deliveryfood.user.UserContext;
import java.util.Objects;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextSupport {

  public static UserContext getUserContext() {
    if (isNotLogined()) {
      throw new NotLoginException();
    }
    Authentication authentication = getSecurityContext().getAuthentication();
    return (UserContext) authentication.getPrincipal();
  }

  private static SecurityContext getSecurityContext() {
    return SecurityContextHolder.getContext();
  }

  public static boolean isNotLogined() {
    Authentication authentication = getSecurityContext().getAuthentication();
    return Objects
        .isNull(authentication) || (authentication instanceof AnonymousAuthenticationToken);
  }
}
