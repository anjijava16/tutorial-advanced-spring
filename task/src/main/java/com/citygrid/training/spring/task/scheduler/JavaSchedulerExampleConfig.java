package com.citygrid.training.spring.task.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaSchedulerExampleConfig {
    @Bean(name = "integerGenerator")
    public SpringIntegerGenerator integerGenerator() {
        return new SpringIntegerGenerator();
    }
}
