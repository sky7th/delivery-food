package com.sky7th.deliveryfood.shop.exception;

public class MismatchOwnerException extends RuntimeException {
    public MismatchOwnerException() {
        super("사장님이 일치하지 않습니다.");
    }
}
