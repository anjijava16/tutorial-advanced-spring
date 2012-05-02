package com.citygrid.training.spring.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ThreadPoolExample {
    private final static Logger LOGGER = Logger.getLogger(ThreadPoolExample.class.getName());
    
    public static void main(final String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ThreadPoolExampleConfig.class);
        ctx.refresh();
        
        ExecutorService threadPool = (ExecutorService)ctx.getBean("fixedThreadPool");
        
        try {
            Collection<Future<FactorialResult>> results = new ArrayList<Future<FactorialResult>>();

            for (int i = 0; i < 10; i++) {
                results.add(threadPool.submit(new FactorialCalculator((int) (Math.random() * 10) + 1)));
            }
            
            for (Future<FactorialResult> result : results) {
                try {
                    LOGGER.info(result.get());
                } catch (InterruptedException e) {
                    break;
                } catch (ExecutionException e) {
                    LOGGER.warn(e.getMessage(), e);
                }
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
