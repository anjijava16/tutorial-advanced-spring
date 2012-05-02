package com.citygrid.training.spring.task.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.citygrid.training.spring.task.FactorialCalculator;
import com.citygrid.training.spring.task.FactorialResult;

public class JavaDelaySchedulerExample {
    private final static Logger LOGGER = Logger.getLogger(JavaDelaySchedulerExample.class.getName());
    
    public static void main(final String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JavaSchedulerExampleConfig.class);
        ctx.refresh();
        
        ScheduledExecutorService scheduler = (ScheduledExecutorService)ctx.getBean("scheduledThreadPool");
        
        try {
            Collection<ScheduledFuture<FactorialResult>> results = new ArrayList<ScheduledFuture<FactorialResult>>();

            for (int i = 0; i < 10; i++) {
                results.add(scheduler.schedule(new FactorialCalculator((int) (Math.random() * 10) + 1), (int) (Math.random() * 10000), TimeUnit.MILLISECONDS));
            }

            for (ScheduledFuture<FactorialResult> result : results) {
                try {
                    LOGGER.info(result.get());
                } catch (InterruptedException ex) {
                } catch (ExecutionException ex) {
                    LOGGER.warn(ex.getMessage(), ex);
                }
            }
        } finally {
            scheduler.shutdown();
        }
    }
}
