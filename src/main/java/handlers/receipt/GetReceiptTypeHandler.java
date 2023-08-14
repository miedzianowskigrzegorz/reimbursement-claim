package handlers.receipt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.ReceiptType;
import utils.ReimbursementReceiptTypes;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * A HTTP handler responsible for retrieving reimbursement receipt types.
 * This handler responds to GET requests by returning the list of receipt types as a JSON response.
 */
public class GetReceiptTypeHandler implements HttpHandler {

    /**
     * Handles the incoming HTTP GET request for retrieving reimbursement receipt types.
     * Returns the list of receipt types as a JSON response.
     *
     * @param exchange The HTTP exchange representing the incoming request and outgoing response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ReimbursementReceiptTypes reimbursementReceiptTypes = ReimbursementReceiptTypes.getInstance();
        List<ReceiptType> receiptTypes = reimbursementReceiptTypes.getTypes();

        // Prepare the JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(receiptTypes);
        byte[] jsonResponseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

        // Set appropriate headers for JSON response
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, jsonResponseBytes.length);

        // Send the JSON response
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponseBytes);
        os.close();
    }
}
