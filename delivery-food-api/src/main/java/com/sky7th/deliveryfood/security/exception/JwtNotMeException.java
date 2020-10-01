package com.sky7th.deliveryfood.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class JwtNotMeException extends RuntimeException {

  public JwtNotMeException() {
    super("본인 확인에 실패했습니다.");
  }
}