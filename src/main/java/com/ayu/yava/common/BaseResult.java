package com.ayu.yava.common;

import lombok.Data;

@Data
public class BaseResult<T> {
    private Integer code;
    private String message;
    private T data;

    private BaseResult() {}

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(200, "成功", data);
    }

    public static <T> BaseResult<T> error(Integer code, String message) {
        return new BaseResult<>(code, message, null);
    }

    private BaseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
