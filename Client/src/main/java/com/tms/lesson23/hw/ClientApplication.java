package com.tms.lesson23.hw;

import com.google.gson.Gson;
import com.tms.lesson23.hw.console.ConsoleReader;
import com.tms.lesson23.hw.console.ConsoleWriter;
import com.tms.lesson23.hw.model.Operation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientApplication {
    private static ConsoleWriter consoleWriter = new ConsoleWriter();

    public static void main(String[] args) {
        Gson gson = new Gson();

        consoleWriter.writer("Enter num1: ");
        double num1 = ConsoleReader.readNum();
        consoleWriter.writer("Enter num2: ");
        double num2 = ConsoleReader.readNum();
        consoleWriter.writer("Enter operation: ");
        String type = ConsoleReader.readOperationType();
        Operation operation = new Operation(num1, num2, type);

        String json = gson.toJson(operation);
        HttpRequest httpRequest;
        HttpResponse<String> httpResponse;
        try {
            httpRequest = HttpRequest.newBuilder().
                    uri(new URI("http://localhost:8080/calc")).
                    header("Content-type", "Application/json").
                    POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Operation operation1 = gson.fromJson(httpResponse.body(), Operation.class);
            System.out.println(operation1);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
