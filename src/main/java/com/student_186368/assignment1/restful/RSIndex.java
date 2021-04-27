/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.restful;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

// The Singleton annotaion denotes that there will be a single object of the RSEmployee class - don't change that
@Singleton

/**
 *
 * @author PacoShum
 */
/*** Annotate the class so that it exports Employee resources under the relative path /employee ***/
@Path("/")
public class RSIndex {

    // for testing purposes, a sample employee is added in the hashmap where all Employee objects are stored
    // when the Singleton object is instantiated
    // after it is instantiated, the @PostConstruct method will be called
    public RSIndex() {

    }

    /**********************************************************************************************************************/
    // Annotate this method so that it can serve GET requests for any relative path under /employee
    // you need to specify a path template for this annotation - this path under /employee should be passed to the getEmployee method by using the PathParam annotation
    // The method Produces either a JSON or XML representation of an Employee object
    // builds and returns an HTTP response (200 ok along with object or 404 NOT FOUND if the employee does not exist)
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getIndex() {
        URI uri;
        try {
            int port = 8181;
            //URI tempuri = new URI("/186368/faces");
            URI tempuri = new URI("https://localhost:8181/186368/faces/index.xhtml");
            //uri = new URI(tempuri.getScheme(), tempuri.getUserInfo(), tempuri.getHost(), 8181, tempuri.getPath(), tempuri.getQuery(), tempuri.getFragment());
            return Response.temporaryRedirect(tempuri).build();
        } catch (URISyntaxException ex) {
            Logger.getLogger(RSExchange.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    /**********************************************************************************************************************/

    
    @PostConstruct
    public void init() {
        System.out.println("Singleton Object for this RESTfull Web Service has been created!!");
    }

    @PreDestroy
    public void clean() {
        System.out.println("Singleton Object for this RESTfull Web Service has been cleaned!!");
    }
}
