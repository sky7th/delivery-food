package com.sky7th.deliveryfood.user.member.service.exception;

public class NotFoundMemberException extends RuntimeException {
    public NotFoundMemberException() {
        super("회원 정보를 찾을 수 없습니다.");
    }
}
