

//The application architecture is: Docker -> GitHub -> Jenkins -> AWS -> DataDog.

package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

public class ExpressionWebServer {

    private static final Random random = new Random();

    public static int calculateRandomSum(int userNumber) {
        if (userNumber <= 0) {
            throw new IllegalArgumentException("Input must be a positive number.");
        }
        int randomNumberSum = 0;
        for (int i = 0; i < userNumber; i++) {
            randomNumberSum += random.nextInt(100) + 1;
        }
        return randomNumberSum;
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new IndexHandler());

        // http://localhost:8080/calculate?n=10
        server.createContext("/calculate", new CalculationHandler());

        server.createContext("/crash", new CrashHandler());

        server.setExecutor(null);
        System.out.println("Server started on port " + port);
        server.start();
    }


    static class IndexHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Final Project is Running! \nUse /calculate?n=50 to calculate sum.\nUse /crash to kill the server.";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class CalculationHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String response;
            int statusCode = 200;

            try {
                if (query == null || !query.contains("n=")) {
                    throw new IllegalArgumentException("Missing parameter 'n'");
                }

                String paramValue = query.split("=")[1];
                int number = Integer.parseInt(paramValue);

                int sum = calculateRandomSum(number);
                response = "User number: " + number + "\nSum of randoms: " + sum + "\nAverage: " + ((double)sum/number);

                System.out.println("Calculated for n=" + number); // Лог в консоль

            } catch (Exception e) {
                statusCode = 400;
                response = "Error: " + e.getMessage();
                System.err.println("Calculation error: " + e.getMessage());
            }

            exchange.sendResponseHeaders(statusCode, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class CrashHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Simulating Critical Failure... Server stopping.";
            exchange.sendResponseHeaders(500, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

            System.err.println("CRITICAL FAILURE INITIATED BY USER");
            System.exit(1);
        }
    }
}
