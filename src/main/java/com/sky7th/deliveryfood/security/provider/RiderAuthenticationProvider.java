package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.security.token.RiderUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.rider.Rider;
import com.sky7th.deliveryfood.user.rider.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RiderAuthenticationProvider extends AbstractAuthenticationProvider {

  private final RiderService riderService;

  public CustomUserDetails getCustomUserDetails(String email) {
    Rider rider = riderService.findByEmail(email);

    return new CustomUserDetails(rider, UserRole.ROLE_RIDER);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(RiderUsernamePasswordAuthenticationToken.class);
  }
}