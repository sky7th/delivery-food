package com.sky7th.deliveryfood.security.provider;

import com.sky7th.deliveryfood.security.token.MemberUsernamePasswordAuthenticationToken;
import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.member.Member;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAuthenticationProvider extends AbstractAuthenticationProvider {

  private final MemberService memberService;

  public CustomUserDetails getCustomUserDetails(String email) {
    Member member = memberService.findByEmail(email);

    return new CustomUserDetails(member, UserRole.ROLE_MEMBER);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(MemberUsernamePasswordAuthenticationToken.class);
  }
}