package com.sky7th.deliveryfood.user.owner.exception;

public class NotFoundOwnerException extends RuntimeException {
    public NotFoundOwnerException() {
        super("사장님 정보를 찾을 수 없습니다.");
    }
}
