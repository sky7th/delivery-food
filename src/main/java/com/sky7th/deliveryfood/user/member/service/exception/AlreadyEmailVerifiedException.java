package com.sky7th.deliveryfood.user.member.service.exception;

public class AlreadyEmailVerifiedException extends RuntimeException {
    public AlreadyEmailVerifiedException() {
        super("이미 이메일 인증된 회원입니다.");
    }
}
