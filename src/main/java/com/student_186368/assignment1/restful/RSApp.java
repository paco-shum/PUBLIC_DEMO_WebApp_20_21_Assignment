/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.restful;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.webapp.FacesServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 *
 * @author PacoShum
 */
@ApplicationPath("/")
public class RSApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        //register resource
        classes.add(RSIndex.class);
        classes.add(RSExchange.class);
        return classes;
    }
    
//    @GET
//    @Path("/")
//    public Response getIndex() {
//        URI uri;
//        try {
//            uri = new URI("/faces");
//            return Response.seeOther(uri).build();
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(RSExchange.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Response.status(Response.Status.NOT_FOUND).build();
//    }
}

