package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.user.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = (String) authentication.getCredentials();
    CustomUserDetails customUserDetails = getCustomUserDetails(email, password);
    customUserDetails.setPassword(null);

    return new UsernamePasswordAuthenticationToken(
        customUserDetails, null, customUserDetails.getAuthorities());
  }

  abstract CustomUserDetails getCustomUserDetails(String email, String password);
}