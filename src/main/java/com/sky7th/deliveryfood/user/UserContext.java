package com.sky7th.deliveryfood.user;

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
}
