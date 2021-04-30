/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.thrift;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.core.Application;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerTransport;

@Startup
@Singleton 
/**
 *
 * @author 186368
 */
public class TimeServer {
    
    public static TimeServerHandler handler;
    public static TimeService.Processor processor;
    public static TServerTransport serverTransport;
    public static TServer server;
    
    
//    private void main(String[] args) {
//        try {
//            handler = new TimeServerHandler();
//            processor = new TimeService.Processor(handler);
//            
//            Runnable simple = new Runnable() {
//                @Override
//                public void run() {
//                    simple(processor);
//                }
//            };
//
//            new Thread(simple).start();
//            System.in.read();
//            server.serve();
//            //server.stop();
//            
//        } catch (Exception x) {
//            System.err.println(x);
//        }
//    }
    
    public static void simple(TimeService.Processor processor) {
        try {
            serverTransport = new TServerSocket(10001);
            server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server in Thread " + Thread.currentThread().getId());
            server.serve();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
//    public void TimeServer(){
//        handler = new TimeServerHandler();
//        
//    }
    @PostConstruct
    public void init() {
        try {
            handler = new TimeServerHandler();
            processor = new TimeService.Processor(handler);
            
            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();
            System.in.read();
            server.serve();
            //server.stop();
            
        } catch (Exception x) {
            System.err.println(x);
        }
        System.out.println("Singleton Object for this Time Server Service has been created!!");
    }

    @PreDestroy
    public void clean() {
        System.out.println("Singleton Object for this Time Server Web Service has been cleaned!!");
    }
}