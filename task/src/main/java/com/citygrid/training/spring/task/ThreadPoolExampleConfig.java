package com.citygrid.training.spring.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:thread-pool-example.properties")
public class ThreadPoolExampleConfig {
    @Autowired
    private Environment env;
    
    @Bean(name="singleThreadExecutor")
    public ExecutorService singleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean(name="fixedThreadPool")
    public ExecutorService fixedThreadPool() {
        return Executors.newFixedThreadPool(Integer.parseInt(env.getProperty("thread.pool.size")));
    }

    @Bean(name="cachedThreadPool")
    public ExecutorService cachedThreadPool() {
        return Executors.newCachedThreadPool();
    }

    /**
     * Commands submitted using the Executor.execute(java.lang.Runnable) 
     * and ExecutorService submit methods are scheduled with a requested delay of zero. 
     * Zero and negative delays (but not periods) are also allowed in schedule methods, 
     * and are treated as requests for immediate execution.
     */
    @Bean(name="scheduledThreadPool")
    public ExecutorService scheduledThreadPool() {
        return Executors.newScheduledThreadPool(Integer.parseInt(env.getProperty("thread.pool.size")));
    }
}
