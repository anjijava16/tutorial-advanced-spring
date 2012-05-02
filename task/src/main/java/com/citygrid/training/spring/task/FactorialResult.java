package com.citygrid.training.spring.task;

import com.google.common.base.Objects;

public class FactorialResult {
    private int value;
    private long result;
    
    public FactorialResult(int value, long result) {
        if (value <= 0) {
            throw new IllegalArgumentException("The value needs to be larger than 0.");
        }
        
        if (result <= 0) {
            throw new IllegalArgumentException("The factorial result needs to be larger than 0.");
        }
        
        this.value = value;
        this.result = result;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("value", value).add("result", result).toString();
    }
}
