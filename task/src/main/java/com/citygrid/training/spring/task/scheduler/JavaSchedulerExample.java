package com.citygrid.training.spring.task.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaSchedulerExample {
    private final static Logger LOGGER = Logger.getLogger(JavaSchedulerExample.class.getName());

    public static void main(final String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JavaSchedulerExampleConfig.class);
        ctx.refresh();

        ScheduledExecutorService scheduler = (ScheduledExecutorService) ctx.getBean("scheduledThreadPool");
        @SuppressWarnings("unchecked")
        BlockingQueue<Integer> integerChannel = ctx.getBean("integerChannel", BlockingQueue.class);

        scheduler.scheduleAtFixedRate(
                ctx.getBean("singleIntegerGenerator", SingleIntegerGenerator.class), 0,
                (int) (Math.random() * 1000), TimeUnit.MILLISECONDS);

        while (true) {
            Integer value = integerChannel.poll();
            if (value != null) {
                LOGGER.info("value: " + value);
            }
        }
    }
}
