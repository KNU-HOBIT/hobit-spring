package com.hobit.domain.sensor.exception;

import com.hobit.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum SensorErrorCode implements ErrorCode {

    SENSOR_NOT_FOUND(HttpStatus.NOT_FOUND,"NonExistent sensor");

    private final HttpStatus status;
    private final String message;

    SensorErrorCode(HttpStatus status, String message) {
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
