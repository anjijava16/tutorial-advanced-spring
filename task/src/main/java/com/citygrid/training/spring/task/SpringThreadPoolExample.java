package com.citygrid.training.spring.task;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.TaskExecutor;

public class SpringThreadPoolExample {
    private final static Logger LOGGER = Logger.getLogger(SpringThreadPoolExample.class.getName());

    public static void main(final String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringThreadPoolExampleConfig.class);
        ctx.refresh();

        TaskExecutor taskExecutor = (TaskExecutor) ctx.getBean("threadPoolTaskExecutor");
        @SuppressWarnings("unchecked")
        BlockingQueue<Integer> integerChannel = ctx.getBean("integerChannel", BlockingQueue.class);

        taskExecutor.execute(ctx.getBean("smallIntegerGenerator", IntegerGenerator.class));
        taskExecutor.execute(ctx.getBean("largeIntegerGenerator", IntegerGenerator.class));
        
        while (true) {
            Integer value = integerChannel.poll();
            if (value != null) {
                LOGGER.info(value);
            }
            
            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
