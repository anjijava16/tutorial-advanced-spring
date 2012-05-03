package com.citygrid.training.spring.webservice.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.citygrid.training.spring.advanced.webservice.model.Employee;

public class ExampleWebServiceClient {
    private final static Logger LOGGER = Logger.getLogger(ExampleWebServiceClient.class.getName());
    
    public static void main(final String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        
        RestTemplate exampleWebService = ctx.getBean("exampleWebService", RestTemplate.class);
        
        try {
            URI uri = new URI("http://localhost:8080/rest/example/names");
            LOGGER.info(uri);
            LOGGER.info(exampleWebService.getForObject(uri, String.class));
            
            uri = new URI("http://localhost:8080/rest/example/employee/vera");
            LOGGER.info(uri);
            LOGGER.info(exampleWebService.getForObject(uri, Employee.class));

            uri = new URI("http://localhost:8080/rest/example/employee");
            LOGGER.info(uri);
            Employee employee = new Employee(500, "john");
            LOGGER.info("Request: " + employee);
            LOGGER.info(exampleWebService.postForObject(uri, employee, String.class));
        } catch (URISyntaxException e) {
        }
    }
}
