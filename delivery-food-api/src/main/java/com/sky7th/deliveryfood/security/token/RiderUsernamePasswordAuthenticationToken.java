package com.sky7th.deliveryfood.security.token;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RiderUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = 791224964095940520L;

  public RiderUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public RiderUsernamePasswordAuthenticationToken(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
