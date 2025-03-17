package com.ayu.yava.exception;

import com.ayu.yava.enums.ErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public CustomException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
