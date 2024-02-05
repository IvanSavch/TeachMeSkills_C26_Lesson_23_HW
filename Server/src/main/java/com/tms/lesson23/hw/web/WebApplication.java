package com.tms.lesson23.hw.web;

import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

public class WebApplication {

    public void run() {
        HttpServer httpServer;
        try {
            httpServer = HttpServer.create(new InetSocketAddress("localhost", 8080), 1);
            httpServer.createContext("/calc",new OperationHttpHandler());
            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
