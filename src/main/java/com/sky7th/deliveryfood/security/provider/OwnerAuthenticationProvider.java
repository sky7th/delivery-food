package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.security.token.OwnerUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.owner.Owner;
import com.sky7th.deliveryfood.user.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OwnerAuthenticationProvider extends AbstractAuthenticationProvider {

  private final OwnerService ownerService;

  public CustomUserDetails getCustomUserDetails(String email) {
    Owner owner = ownerService.findByEmail(email);

    return new CustomUserDetails(owner, UserRole.ROLE_OWNER);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(OwnerUsernamePasswordAuthenticationToken.class);
  }
}