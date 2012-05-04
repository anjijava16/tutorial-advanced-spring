package com.citygrid.training.spring.advanced.webservice;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.citygrid.training.spring.advanced.webservice.model.Employee;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.sun.jersey.api.json.JSONWithPadding;

/** To run the example, "mvn clean package -Djetty" **/

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/employee")
public class EmployeeResource {
    private Map<String, Integer> samples;

    public EmployeeResource() {
        samples = ImmutableMap.of("chris", 100, "jesse", 384, "tommy", 3479874, "vera", 8374,
                "joey", 15834);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/names")
    public Response getNames() {
        ImmutableSet<String> result = ImmutableSet.copyOf(samples.keySet());

        return Response.status(200).entity(result.toString()).build();
    }

    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{name}")
    public Response getEmployee(@PathParam("name") final String name) {
        Response response = Response.status(404).build();
        
        Integer id = samples.get(name.toLowerCase());

        if (id != null) {
            response = Response.status(200).entity(new Employee(id, name)).build();
        }

        return response;
    }

    @GET
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/x-javascript"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "application/x-javascript"})
    @Path("jsonp/{name}")
    public Response getEmployee(@PathParam("name") final String name, @QueryParam("jsoncallback") String callback) {
        Employee employee = null;
        
        Integer id = samples.get(name.toLowerCase());

        if (id != null) {
            employee = new Employee(id, name);
        }

        return Response.status(202).entity(new JSONWithPadding(new GenericEntity<Employee>(employee) {}, callback)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response addEmployee(final Employee employee) {
        return Response.status(200).entity(employee).build();
    }
}
