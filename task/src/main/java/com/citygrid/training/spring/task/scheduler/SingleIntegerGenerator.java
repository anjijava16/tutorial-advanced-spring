package com.citygrid.training.spring.task.scheduler;

import java.util.concurrent.BlockingQueue;

public class SingleIntegerGenerator implements Runnable {
    private BlockingQueue<Integer> blockingQueue;
    private int minValue;

    public SingleIntegerGenerator(final BlockingQueue<Integer> blockingQueue, int minValue) {
        this.blockingQueue = blockingQueue;
        this.minValue = minValue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put((int) (Math.random() * 1000) + minValue);
        } catch (InterruptedException ex) {
        }
    }
}
