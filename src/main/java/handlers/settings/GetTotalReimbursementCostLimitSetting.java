package handlers.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.TotalReimbursementCostLimitSetting;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A HTTP handler responsible for retrieving the total reimbursement cost limit setting.
 * This handler responds to GET requests by providing the current limit setting as JSON.
 */
public class GetTotalReimbursementCostLimitSetting implements HttpHandler {

    /**
     * Handles the incoming HTTP GET request to retrieve the total reimbursement cost limit setting.
     * Converts the current limit setting to JSON and sends it as the response.
     *
     * @param exchange The HTTP exchange representing the incoming request and outgoing response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        TotalReimbursementCostLimitSetting limit = TotalReimbursementCostLimitSetting.getInstance();

        // Convert the limit setting to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(limit);

        // Set response headers and send response
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, json.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
        }
    }
}






