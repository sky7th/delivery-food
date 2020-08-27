package com.sky7th.deliveryfood.user.owner;

import com.sky7th.deliveryfood.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Getter
@Entity
@Table(name = "OWNERS", uniqueConstraints = {
    @UniqueConstraint(columnNames = "EMAIL")
})
public class Owner extends User {

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  private Owner() {
    super();
  }
}
