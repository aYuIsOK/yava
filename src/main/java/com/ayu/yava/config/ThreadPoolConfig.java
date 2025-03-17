package com.ayu.yava.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {
    @Bean("baseExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);   // 核心线程数
        executor.setMaxPoolSize(200);   // 最大线程数
        executor.setQueueCapacity(500); // 任务队列容量
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("BaseThread-");
        executor.initialize();
        return executor;
    }
}
