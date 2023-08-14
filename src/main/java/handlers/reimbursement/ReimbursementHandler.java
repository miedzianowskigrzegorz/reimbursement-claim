package handlers.reimbursement;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * A HTTP handler responsible for handling requests related to the reimbursement page.
 * This handler responds to specific requests and serves the appropriate static resources.
 */
public class ReimbursementHandler implements HttpHandler {

    /**
     * Handles the incoming HTTP request related to the reimbursement page.
     * Serves the appropriate static resources (HTML, JavaScript) based on the request path.
     *
     * @param exchange The HTTP exchange representing the incoming request and outgoing response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        String resourcePath;
        if (requestPath.equals("/reimbursement") || requestPath.equals("/reimbursement.html")) {
            resourcePath = "static/reimbursement.html";
        } else if (requestPath.equals("/reimbursement.js")) {
            resourcePath = "static/reimbursement.js";
        } else {
            return; // Do nothing if the request path doesn't match expected resources
        }

        // Load and serve the appropriate static resource
        URL resourceUrl = getClass().getClassLoader().getResource(resourcePath);
        if (resourceUrl != null) {
            try (InputStream inputStream = resourceUrl.openStream()) {
                exchange.sendResponseHeaders(200, 0);
                OutputStream outputStream = exchange.getResponseBody();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
            }
        } else {
            // Resource not found
            String response = "Resource not found.";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}
