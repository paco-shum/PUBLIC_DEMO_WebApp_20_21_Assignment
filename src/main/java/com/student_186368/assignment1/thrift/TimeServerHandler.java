/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student_186368.assignment1.thrift;

import org.apache.thrift.TException;
import com.student_186368.assignment1.thrift.*;

/**
 *
 * @author 186368
 */
public class TimeServerHandler implements TimeService.Iface {
    @Override
    public long time() throws TException {
        long time = System.currentTimeMillis();
        System.out.println("time() called: " + time);
        return time;
    }
}