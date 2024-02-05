package com.tms.lesson23.hw.web;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.tms.lesson23.hw.model.Operation;
import com.tms.lesson23.hw.service.OperationService;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class OperationHttpHandler implements HttpHandler {
    private final OperationService operationService = new OperationService();
    private final Gson gson = new Gson();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        byte[] bytes = requestBody.readAllBytes();

        String s = new String(bytes,"UTF-8");

        Operation operation = gson.fromJson(s,Operation.class);
        Operation calculate = operationService.calculate(operation);

        String json = gson.toJson(calculate);

        exchange.sendResponseHeaders(200, json.length());
        exchange.getResponseHeaders().add("Content-Type", "application/json");

        PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
        printWriter.print(json);
        printWriter.flush();
    }
}
