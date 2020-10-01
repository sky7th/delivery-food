package com.sky7th.deliveryfood.user.member.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MemberTest {

  @Test
  void Member의_이메일_인증_상태를_확인_상태로_바꾼다() {
    Member member = new Member("email", "password", "username");

    member.emailVerify();

    assertThat(member.getEmailVerified()).isEqualTo(true);
  }
}