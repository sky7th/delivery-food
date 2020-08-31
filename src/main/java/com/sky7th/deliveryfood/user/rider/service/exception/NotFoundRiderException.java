package com.sky7th.deliveryfood.user.rider.service.exception;

public class NotFoundRiderException extends RuntimeException {
    public NotFoundRiderException() {
        super("라이더 정보를 찾을 수 없습니다.");
    }
}
