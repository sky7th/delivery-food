package com.sky7th.deliveryfood.user.owner.domain;

import com.sky7th.deliveryfood.user.User;
import java.util.Objects;
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

  public Owner(Long id) {
    super(id);
  }

  public Owner(String email, String password, String username) {
    super(email, password);
    this.username = username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Owner owner = (Owner) o;
    return Objects.equals(this.getId(), owner.getId()) &&
        this.getRole() == owner.getRole();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId(), this.getRole());
  }
}
