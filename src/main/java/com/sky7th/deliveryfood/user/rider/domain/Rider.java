package com.sky7th.deliveryfood.user.rider.domain;

import com.sky7th.deliveryfood.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Getter
@Entity
@Table(name = "RIDERS", uniqueConstraints = {
    @UniqueConstraint(columnNames = "EMAIL")
})
public class Rider extends User {

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  public Rider() {
    super();
  }

  public Rider(String email, String password, String username) {
    super(email, password);
    this.username = username;
  }
}