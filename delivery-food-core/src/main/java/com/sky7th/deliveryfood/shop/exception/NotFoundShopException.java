package com.sky7th.deliveryfood.shop.exception;

public class NotFoundShopException extends RuntimeException {
    public NotFoundShopException() {
        super("존재하는 가게가 아닙니다.");
    }
}
