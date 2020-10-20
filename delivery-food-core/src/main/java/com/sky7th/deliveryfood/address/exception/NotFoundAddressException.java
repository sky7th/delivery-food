package com.sky7th.deliveryfood.address.exception;

public class NotFoundAddressException extends RuntimeException {
    public NotFoundAddressException() {
        super("주소를 찾을 수 없습니다.");
    }
}
