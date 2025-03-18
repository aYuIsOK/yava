package com.ayu.yava.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {

    @Autowired
    private ObjectMapper objectMapper; // 需要配置ObjectMapper

    @Around("execution(* com.ayu.yava.controller..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            // 记录请求信息（直接使用DTO的Jackson注解）
            recordRequest(joinPoint);

            result = joinPoint.proceed();
            // 记录响应结果（直接使用DTO的Jackson注解）
            recordResponse(result, startTime);
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            // 慢请求记录逻辑保持不变
        }
    }
    
    private void recordRequest(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        
        // 获取所有参数（可能包含多个参数）
        Object[] args = joinPoint.getArgs();
        
        // 将参数转换为Map或DTO对象（根据实际参数类型）
        String argsJson = convertArgsToJson(args);
        
        log.info("Request: {} {} | Args: {}",
                request.getMethod(), request.getRequestURI(), argsJson);
    }

    private String convertArgsToJson(Object[] args) {
        try {
            return objectMapper.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            return "Serialization Failed";
        }
    }

    private void recordResponse(Object result, long startTime) {
        long duration = System.currentTimeMillis() - startTime;
        try {
            String resultJson = objectMapper.writeValueAsString(result);
            log.info("Response: Result: {} | Took: {}ms", resultJson, duration);
        } catch (JsonProcessingException e) {
            log.info("Response: Result: [Serialization Failed] | Took: {}ms", duration);
        }
    }

}
