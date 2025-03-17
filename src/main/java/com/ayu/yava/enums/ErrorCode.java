package com.ayu.yava.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 通用错误码

    SUCCESS(0, "ok"),
    INNER_ERROR(500, "内部错误");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}