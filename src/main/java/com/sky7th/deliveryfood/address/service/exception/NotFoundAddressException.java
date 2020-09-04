package com.sky7th.deliveryfood.address.service.exception;

public class NotFoundAddressException extends RuntimeException {
    public NotFoundAddressException() {
        super("존재하지 않는 주소 코드 입니다.");
    }
}
