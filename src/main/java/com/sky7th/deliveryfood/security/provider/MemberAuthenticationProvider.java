package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.security.exception.EmailNotVerifiedException;
import com.sky7th.deliveryfood.security.service.AuthService;
import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAuthenticationProvider extends AbstractAuthenticationProvider {

  private final MemberService memberService;
  private final AuthService authService;

  public CustomUserDetails getCustomUserDetails(String email, String password) {
    Member member = memberService.findByEmail(email);
    authService.validateUser(member, password);

    if (!member.getEmailVerified()) {
      throw new EmailNotVerifiedException(member.getEmail());
    }

    return new CustomUserDetails(member, UserRole.ROLE_MEMBER);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(MemberUsernamePasswordAuthenticationToken.class);
  }
}