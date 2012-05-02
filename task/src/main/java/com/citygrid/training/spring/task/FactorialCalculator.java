package com.citygrid.training.spring.task;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

public class FactorialCalculator implements Callable<FactorialResult> {
    private final static Logger LOGGER = Logger.getLogger(FactorialCalculator.class.getName());
    
    private int value;    
    
    public FactorialCalculator(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("The value needs to be larger than 0.");
        }
        
        this.value = value;
    }
    
    public FactorialResult call() throws Exception {
        long result = 1l;
        
        LOGGER.info(Thread.currentThread().getName() + " is running...");
        
        for (int i = 1; i <= value; i++) {
            result = result * i;
        }
        
        return new FactorialResult(value, result);
    }
}
