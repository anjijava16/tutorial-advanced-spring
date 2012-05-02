package com.citygrid.training.spring.task.scheduler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSchedulerExample {
    public static void main(final String[] args) {
        new ClassPathXmlApplicationContext("spring-scheduler-context.xml");
        
        while (true) {
            
        }
    }
}
