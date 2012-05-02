package com.citygrid.training.spring.task.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringSchedulerExampleConfig {

    @Bean(name = "scheduledThreadPool")
    public ScheduledExecutorService scheduledThreadPool() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean(name = "integerChannel")
    public BlockingQueue<Integer> integerChannel() {
        return new LinkedBlockingQueue<Integer>();
    }

    @Bean(name = "singleIntegerGenerator")
    public SingleIntegerGenerator singleIntegerGenerator() {
        return new SingleIntegerGenerator(integerChannel(), 100);
    }
}
