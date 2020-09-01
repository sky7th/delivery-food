package com.sky7th.deliveryfood.generic.address.service.exception;

public class NotFoundMemberAddressException extends RuntimeException {
    public NotFoundMemberAddressException() {
        super("해당 고객 주소지를 찾을 수 없습니다.");
    }
}
