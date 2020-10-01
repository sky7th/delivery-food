package com.sky7th.deliveryfood.security.exception;

public class EmailNotVerifiedException extends RuntimeException {

  public EmailNotVerifiedException(String email) {
    super("이메일 인증이 되지 않았습니다. email: "+ email);
  }
}
