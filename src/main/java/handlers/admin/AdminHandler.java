package handlers.admin;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * HttpHandler implementation for serving admin resources.
 */
public class AdminHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        String resourcePath;

        // Determine the resource path based on the request path.
        if (requestPath.equals("/admin")) {
            resourcePath = "static/admin.html";
        } else if (requestPath.equals("/admin.js")) {
            resourcePath = "static/admin.js";
        } else {
            // Return if the request path is not recognized.
            return;
        }

        // Load the resource URL using the class loader.
        URL resourceUrl = getClass().getClassLoader().getResource(resourcePath);

        if (resourceUrl != null) {
            try (InputStream inputStream = resourceUrl.openStream()) {
                // Send a successful response header (200 OK).
                exchange.sendResponseHeaders(200, 0);

                // Write the resource content to the response body.
                OutputStream outputStream = exchange.getResponseBody();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
            }
        } else {
            // If the resource is not found, send a 404 response with an error message.
            String response = "Resource not found.";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}
