/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.ejb;

import com.student_186368.assignment1.thrift.TimeService;
import javax.ejb.Stateless;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *Time client to get time
 * @author 186368
 */
@Stateless
public class TimeServiceEJB {

    public TimeServiceEJB() {
    }
    
    public static long getTime() {
        long time = 0;

        try {
            TTransport transport;

            transport = new TSocket("localhost", 10001);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            TimeService.Client client = new TimeService.Client(protocol);

            transport.open();
            time = client.time();
            System.out.println("Time from server:" + time);

            //close transport
            transport.close();

        } catch (TException x) {
            System.err.println(x);
        }
        return time;
    }
}
