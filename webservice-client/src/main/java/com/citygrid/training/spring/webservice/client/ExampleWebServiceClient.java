package com.citygrid.training.spring.webservice.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.citygrid.training.spring.advanced.webservice.model.Employee;

public class ExampleWebServiceClient {
    private final static Logger LOGGER = Logger.getLogger(ExampleWebServiceClient.class.getName());

    public static void main(final String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

        RestTemplate employeeWebService = ctx.getBean("employeeWebService", RestTemplate.class);

        try {
            try {
                URI uri = new URI("http://localhost:8080/rest/employee/names");
                LOGGER.info(uri);
                LOGGER.info(employeeWebService.getForObject(uri, String.class));
            } catch (RestClientException ex) {
                LOGGER.warn(ex.getMessage());
            }

            try {
                URI uri = new URI("http://localhost:8080/rest/employee/vera");
                LOGGER.info(uri);
                LOGGER.info(employeeWebService.getForObject(uri, Employee.class));
            } catch (RestClientException ex) {
                LOGGER.warn(ex.getMessage());
            }

            try {
                URI uri = new URI("http://localhost:8080/rest/employee/johnny");
                LOGGER.info(uri);
                LOGGER.info(employeeWebService.getForObject(uri, Employee.class));
            } catch (RestClientException ex) {
                LOGGER.warn(ex.getMessage());
            }

            try {
                URI uri = new URI("http://localhost:8080/rest/employee");
                LOGGER.info(uri);
                Employee employee = new Employee(500, "john");
                LOGGER.info("Request: " + employee);
                LOGGER.info(employeeWebService.postForObject(uri, employee, String.class));
            } catch (RestClientException ex) {
                LOGGER.warn(ex.getMessage());
            }

        } catch (URISyntaxException e) {
        }
    }
}
