package com.hobit.domain.member.exception;

import com.hobit.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"NoneExistent Member");
    private final HttpStatus status;
    private final String message;

    MemberErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String code() {
        return this.name();
    }

    @Override
    public String message() {
        return this.message;
    }
}
