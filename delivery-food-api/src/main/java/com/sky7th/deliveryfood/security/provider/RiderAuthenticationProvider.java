package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.security.service.UserValidateService;
import com.sky7th.deliveryfood.security.token.RiderUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.rider.domain.Rider;
import com.sky7th.deliveryfood.user.rider.service.RiderInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RiderAuthenticationProvider extends AbstractAuthenticationProvider {

  private final RiderInternalService riderInternalService;
  private final UserValidateService userValidateService;

  public CustomUserDetails getCustomUserDetails(String email, String password) {
    Rider rider = riderInternalService.findByEmail(email);
    userValidateService.validateUser(rider, password);

    return new CustomUserDetails(rider, UserRole.ROLE_RIDER);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(RiderUsernamePasswordAuthenticationToken.class);
  }
}