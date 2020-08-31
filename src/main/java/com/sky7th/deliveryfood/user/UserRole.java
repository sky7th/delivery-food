package com.sky7th.deliveryfood.user;

public enum UserRole {

  ROLE_MEMBER("MEMBER"),
  ROLE_OWNER("OWNER"),
  ROLE_RIDER("RIDER"),
  ROLE_ADMIN("ADMIN");

  private final String roleName;

  UserRole(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleName() {
    return roleName;
  }
}
