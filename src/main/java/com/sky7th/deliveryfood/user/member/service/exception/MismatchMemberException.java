package com.sky7th.deliveryfood.user.member.service.exception;

public class MismatchMemberException extends RuntimeException {
    public MismatchMemberException() {
        super("유저가 일치하지 않습니다.");
    }
}
