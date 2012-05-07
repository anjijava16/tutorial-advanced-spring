package com.citygrid.training.spring.task.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:thread-pool-example.properties")
public class JavaSchedulerExampleConfig {
    @Autowired
    private Environment env;

    @Bean(name = "integerGenerator")
    public SingleIntegerGenerator integerGenerator() {
        return new SingleIntegerGenerator(integerChannel(), 100);
    }

    @Bean(name = "integerChannel")
    public BlockingQueue<Integer> integerChannel() {
        return new LinkedBlockingQueue<Integer>();
    }
    
    @Bean(name="scheduledThreadPool")
    public ExecutorService scheduledThreadPool() {
        return Executors.newScheduledThreadPool(Integer.parseInt(env.getProperty("thread.pool.size")));
    }
}
