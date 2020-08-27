package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public final void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String password = (String) authentication.getCredentials();

    CustomUserDetails userDetails = getCustomUserDetails(email);

    if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException(email);

    } else if (!userDetails.isAccountNonLocked()) {
      throw new LockedException(email);

    } else if (!userDetails.isEnabled()) {
      throw new DisabledException(email);
    }

    userDetails.setPassword(null);

    return new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
  }

  abstract CustomUserDetails getCustomUserDetails(String email);
}