package com.sky7th.deliveryfood.user.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("유저 정보를 찾을 수 없습니다.");
    }
}
