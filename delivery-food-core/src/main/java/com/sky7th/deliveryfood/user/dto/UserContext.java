package com.sky7th.deliveryfood.user.dto;

import com.sky7th.deliveryfood.user.UserRole;
import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.owner.domain.Owner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContext {

  private Long id;
  private UserRole role;

  private UserContext() {
  }

  public UserContext(Long id, UserRole role) {
    this.id = id;
    this.role = role;
  }

  public Member toMember() {
    return new Member(this.id);
  }

  public Owner toOwner() {
    return new Owner(this.id);
  }
}
