package com.ayu.yava.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ayu.yava.common.BaseResult;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *  处理自定义异常
     *
     */
    @ExceptionHandler(CustomException.class)
    public BaseResult<?> handleCustomException(CustomException e) {
        log.error("业务异常", e);
        return BaseResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult<?> handleException(Exception e) {
        e.printStackTrace();
        return BaseResult.error(500, "系统异常：" + e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public BaseResult<?> handleBizException(IllegalArgumentException e) {
        return BaseResult.error(400, e.getMessage());
    }
}
