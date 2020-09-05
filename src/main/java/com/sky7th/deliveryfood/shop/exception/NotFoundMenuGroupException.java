package com.sky7th.deliveryfood.shop.exception;

public class NotFoundMenuGroupException extends RuntimeException {
    public NotFoundMenuGroupException() {
        super("존재하지 않는 메뉴 그룹입니다.");
    }
}
