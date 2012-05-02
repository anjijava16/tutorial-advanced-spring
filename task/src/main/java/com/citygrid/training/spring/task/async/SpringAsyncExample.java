package com.citygrid.training.spring.task.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import com.citygrid.training.spring.task.FactorialResult;

public class SpringAsyncExample {
    private final static Logger LOGGER = Logger.getLogger(SpringAsyncExample.class.getName());
    
    public SpringAsyncExample() {
        
    }
    
    @Async
    public Future<FactorialResult> calcFactorial(int value) {
        long result = 1l;
        
        LOGGER.info("Calculating factorial of " + value + "...");
        
        for (int i = 1; i <= value; i++) {
            result = result * i;
            
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
        
        LOGGER.info("Completed for value " + value);
        
        return new AsyncResult<FactorialResult>(new FactorialResult(value, result));
    }
    
    public static void main(final String[] args) {
        new ClassPathXmlApplicationContext("spring-async-context.xml");

        SpringAsyncExample example = new SpringAsyncExample();
        
        try {
            Collection<Future<FactorialResult>> results = new ArrayList<Future<FactorialResult>>();
            
            for (int i = 0; i < 10; i++) {
                results.add(example.calcFactorial((int) (Math.random() * 20) + 1));
            }
            
            for (Future<FactorialResult> result : results) {
                LOGGER.info(result.get());
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
