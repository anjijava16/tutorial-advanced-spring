package com.citygrid.training.spring.webservice;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/** To run the example, "mvn clean package -Djetty" **/

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/example")
public class ExampleResource {
    private Map<String, Integer> samples;
    
    public ExampleResource() {
        samples = ImmutableMap.of("chris", 100, "jesse", 384, "tommy", 3479874, "vera", 8374, "joey", 15834);
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("names")
    public Response getNames() {
        ImmutableSet<String> result = ImmutableSet.copyOf(samples.keySet());
        
        return Response.status(200).entity(result.toString()).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("id/{name}")
    public Response getName(@PathParam("name") final String name) {
        Integer id = samples.get(name.toLowerCase());
        
        return Response.status(200).entity(id != null ? String.valueOf(id) : "Not Found").build();
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    @Path("add")
    public Response addNames(final String names) {        
        return Response.status(200).entity(names).build();
    }
    
}