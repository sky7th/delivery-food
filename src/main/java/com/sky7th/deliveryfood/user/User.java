package com.sky7th.deliveryfood.user;

import com.sky7th.deliveryfood.common.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "IS_ACTIVE", nullable = false)
  private Boolean active;

  @Transient
  private UserRole role;

  public User(User user) {
    this.id = user.id;
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.active = user.getActive();
    this.role = user.getRole();
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
    this.active = true;
  }

  public User() {
  }

  public void inActice() {
    this.active = false;
  }
}
