package com.citygrid.training.spring.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@PropertySource("classpath:thread-pool-example.properties")
public class SpringThreadPoolExampleConfig {
    @Autowired
    private Environment env;
    
    @Bean(name="threadPoolTaskExecutor")
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Integer.parseInt(env.getProperty("core.pool.size")));
        taskExecutor.setMaxPoolSize(Integer.parseInt(env.getProperty("max.pool.size")));
        taskExecutor.setQueueCapacity(Integer.parseInt(env.getProperty("queue.capacity")));
        
        return taskExecutor;
    }
    
    @Bean(name="integerChannel")
    public BlockingQueue<Integer> integerChannel() {
        return new LinkedBlockingQueue<Integer>();
    }
    
    @Bean(name="smallIntegerGenerator")
    public IntegerGenerator smallIntegerGenerator() {
        return new IntegerGenerator(integerChannel(), 100);
    }

    @Bean(name="largeIntegerGenerator")
    public IntegerGenerator largeIntegerGenerator() {
        return new IntegerGenerator(integerChannel(), 10000);
    }
}
