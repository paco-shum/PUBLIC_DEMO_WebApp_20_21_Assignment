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
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;

@Startup
@Singleton 
/**
 *
 * @author 186368
 */
public class TimeServer {
    
    public void TimeServer(){
        try {
            TServerSocket serverTransport = new TServerSocket(10001);
            //serverTransport.listen();
            TimeServerHandler handler = new TimeServerHandler();
            TimeService.Processor processor = new TimeService.Processor(new TimeServerHandler());
            //Factory protFactory = new TBinaryProtocol.Factory(true, true);
//            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
//            
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
                    
            System.out.println("Starting server on port 10001 ...");
            server.serve();
            //server.stop();
            System.out.println("Starting the simple server (time server) in Thread ");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    @PostConstruct
    public void init() {
        //TimeServer();
        System.out.println("Singleton Object for this Time Server Service has been created!!");
    }

    @PreDestroy
    public void clean() {
        System.out.println("Singleton Object for this Time Server Web Service has been cleaned!!");
    }
}