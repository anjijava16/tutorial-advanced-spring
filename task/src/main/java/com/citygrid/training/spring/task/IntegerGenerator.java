package com.citygrid.training.spring.task;

import java.util.concurrent.BlockingQueue;

public class IntegerGenerator implements Runnable {
    private BlockingQueue<Integer> blockingQueue;
    private int minValue;

    public IntegerGenerator(final BlockingQueue<Integer> blockingQueue, int minValue) {
        this.blockingQueue = blockingQueue;
        this.minValue = minValue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                blockingQueue.put((int) (Math.random() * 1000) + minValue);
                
                Thread.sleep((int) (Math.random() * 1000));
                Thread.yield();
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
