package com.sky7th.deliveryfood.shop.exception;

public class NotFoundMenuException extends RuntimeException {
    public NotFoundMenuException() {
        super("존재하지 않는 메뉴 입니다.");
    }
}
