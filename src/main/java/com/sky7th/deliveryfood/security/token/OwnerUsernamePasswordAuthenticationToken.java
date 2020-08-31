package com.sky7th.deliveryfood.security.token;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class OwnerUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = 891224964095940520L;

  public OwnerUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public OwnerUsernamePasswordAuthenticationToken(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
