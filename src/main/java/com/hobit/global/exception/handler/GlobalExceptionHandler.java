package com.hobit.global.exception.handler;


import com.hobit.global.exception.GlobalException;
import com.hobit.global.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiUtil.ApiErrorResult> businessLogicException(final GlobalException globalException){
        HttpStatus httpStatus = globalException.getStatus();
        String code = globalException.getCode();
        String message = globalException.getMessage();
        return ResponseEntity.status(httpStatus).body(ApiUtil.error(httpStatus,code,message));
                    }
}
