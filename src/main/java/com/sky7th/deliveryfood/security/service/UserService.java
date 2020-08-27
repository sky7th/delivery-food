package com.sky7th.deliveryfood.security.service;

import com.sky7th.deliveryfood.user.CustomUserDetails;
import com.sky7th.deliveryfood.user.User;
import com.sky7th.deliveryfood.user.UserContext;
import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.member.service.MemberService;
import com.sky7th.deliveryfood.user.owner.service.OwnerService;
import com.sky7th.deliveryfood.user.rider.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final MemberService memberService;
  private final OwnerService ownerService;
  private final RiderService riderService;

  public CustomUserDetails loadUserByIdAndRole(UserContext userContext) {
    User user = null;

    UserRole role = userContext.getRole();
    Long userId = userContext.getId();

    if (role == UserRole.ROLE_MEMBER) {
      user = memberService.findById(userId);

    } else if (role == UserRole.ROLE_OWNER) {
      user = ownerService.findById(userId);

    } else if (role == UserRole.ROLE_RIDER) {
      user = riderService.findById(userId);
    }

    return new CustomUserDetails(user, userContext.getRole());
  }
}
