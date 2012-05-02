package com.citygrid.training.spring.task.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class SpringIntegerGenerator {
    private final static Logger LOGGER = Logger.getLogger(SpringIntegerGenerator.class.getName());
    
    private BlockingQueue<Integer> channel;
    
    public SpringIntegerGenerator() {
        channel = new LinkedBlockingQueue<Integer>();
    }

    @Scheduled(fixedDelay = 500)
    public void generateInteger() {
        int value = (int) (Math.random() * 1000);
        
        LOGGER.info("Generated Integer Value = " + value);
        
        try {
            channel.put(value);
        } catch (InterruptedException e) {
        }
    }
    
    @Scheduled(cron="*/5 * * * * *")
    public void printInteger() {
        while (!channel.isEmpty()) {
            LOGGER.info("Retrieved Integer: " + channel.poll());
        }
    }
}
