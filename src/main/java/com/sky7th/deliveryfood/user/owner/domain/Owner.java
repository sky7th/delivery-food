package com.sky7th.deliveryfood.user.owner.domain;

import com.sky7th.deliveryfood.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "OWNERS")
public class Owner extends User {

  @Column(name = "USERNAME", nullable = false)
  private String username;

  public Owner() {
    super();
  }

  public Owner(String email, String password, String username) {
    super(email, password);
    this.username = username;
  }
}
