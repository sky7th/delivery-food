package com.sky7th.deliveryfood.user.exception;

public class MismatchUserException extends RuntimeException {
    public MismatchUserException() {
        super("유저가 일치하지 않습니다.");
    }
}
