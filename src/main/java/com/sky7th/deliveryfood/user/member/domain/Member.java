package com.sky7th.deliveryfood.user.member.domain;

import com.sky7th.deliveryfood.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Getter
@Entity
@Table(name = "MEMBERS")
public class Member extends User {

  @Column(name = "USERNAME", nullable = false)
  private String username;

  @Column(name = "IMAGE_URL")
  private String imageUrl;

  @Column(nullable = false)
  private Boolean emailVerified = false;

  public Member() {
    super();
  }

  public Member(String email, String password, String username) {
    super(email, password);
    this.username = username;
  }

  public void emailVerify() {
    this.emailVerified = true;
  }
}
