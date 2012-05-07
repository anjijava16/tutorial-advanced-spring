package com.citygrid.training.spring.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class SimpleThreadExample extends Thread {
    private final static Logger LOGGER = Logger.getLogger(SimpleThreadExample.class.getName());
    
    public static void main(final String[] args) {
        BlockingQueue<Integer> integerChannel = new LinkedBlockingQueue<Integer>();
        IntegerGenerator integerGenerator = new IntegerGenerator(integerChannel, 0);
        
        Thread thread = new Thread(integerGenerator);
        thread.start();

        while (true) {
            Integer value = integerChannel.poll();
            if (value != null) {
                LOGGER.info("Value: " + value);
            }
            
            Thread.yield();
        }
    }
}
