package handlers.receipt;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.ReimbursementReceiptTypes;
import utils.UpdatedReceiptTypes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A HTTP handler responsible for updating reimbursement receipt types.
 * This handler responds to POST requests by updating the list of receipt types and returning a response.
 */
public class UpdateReceiptTypeHandler implements HttpHandler {

    /**
     * Handles the incoming HTTP POST request for updating reimbursement receipt types.
     * Processes the incoming JSON data, updates the receipt types, and sends the response.
     *
     * @param exchange The HTTP exchange representing the incoming request and outgoing response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // Read data from the request body in JSON format
            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            StringBuilder requestBody = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                requestBody.append(buffer, 0, bytesRead);
            }
            reader.close();

            // Deserialize JSON into UpdatedReceiptTypes object
            UpdatedReceiptTypes updatedReceiptTypes = UpdatedReceiptTypes.fromJson(requestBody.toString());

            // Update the list of receipt types
            ReimbursementReceiptTypes receiptTypes = ReimbursementReceiptTypes.getInstance();
            receiptTypes.setTypes(updatedReceiptTypes.getTypes());

            // Prepare response message
            String jsonResponse = "{\"message\": \"Receipt types updated successfully!\"}";
            byte[] jsonResponseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

            // Set appropriate headers for JSON response
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponseBytes.length);

            // Send the JSON response
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponseBytes);
            os.close();
        } else {
            // Unsupported request method
            String response = "Invalid request method.";
            exchange.sendResponseHeaders(405, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}
