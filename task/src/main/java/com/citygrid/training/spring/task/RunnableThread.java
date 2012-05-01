package com.citygrid.training.spring.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.citygrid.training.spring.task.RunnableThread.Result;
import com.google.common.base.Objects;

public class RunnableThread implements Callable<Result> {
    private int threadId;
    private int value;

    public class Result {
        private int threadId;
        private int value;
        private long result;

        public Result(int threadId, int value, long result) {
            this.threadId = threadId;
            this.value = value;
            this.result = result;
        }

        public String toString() {
            return Objects.toStringHelper(this)
                    .add("threadId", threadId)
                    .add("value", value)
                    .add("factorial", result)
                    .toString();
        }
    }

    public RunnableThread(int threadId, int value) {
        this.threadId = threadId;
        this.value = value;
    }

    @Override
    public Result call() throws Exception {
        if (value <= 0) {
            throw new IllegalArgumentException("The value must larger than 0.");
        }

        System.out.println("Running Thread ID: " + threadId);

        long factorial = 1;
        for (int i = 1; i < value; i++) {
            factorial *= i;

            Thread.sleep((int) (Math.random() * 10));
        }
        
        Result result = new Result(threadId, value, factorial);
        
        System.out.println(result);

        return result;
    }

    public static void main(final String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            Collection<Future<Result>> results = new ArrayList<Future<Result>>();

            for (int i = 0; i < 20; i++) {
                results.add(threadPool.submit(new RunnableThread(i, (int) (Math.random() * 64))));
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
