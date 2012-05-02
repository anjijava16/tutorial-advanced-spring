package com.citygrid.training.spring.webservice.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class ExampleWebServiceClient {
    private final static Logger LOGGER = Logger.getLogger(ExampleWebServiceClient.class.getName());
    
    public static void main(final String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        
        RestTemplate exampleWebService = ctx.getBean("exampleWebService", RestTemplate.class);
        
        try {
            URI uri = new URI("http://localhost:8080/rest/example/names");
            LOGGER.info(uri);
            LOGGER.info(exampleWebService.getForObject(uri, String.class));
            
            uri = new URI("http://localhost:8080/rest/example/id/chris");
            LOGGER.info(uri);
            LOGGER.info(exampleWebService.getForObject(uri, String.class));
            
            uri = new URI("http://localhost:8080/rest/example/add");
            LOGGER.info(uri);
            String request = "{name: {a, b, c, d, e}}";
            LOGGER.info("Request: " + request);
            LOGGER.info(exampleWebService.postForObject(uri, request, String.class));
            
        } catch (URISyntaxException e) {
        }
    }
}
