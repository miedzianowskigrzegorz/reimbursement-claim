package handlers.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.ReimbursementRatesSetting;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A HTTP handler responsible for retrieving the reimbursement rates setting.
 * This handler responds to GET requests by providing the current rates setting as JSON.
 */
public class GetReimbursementRatesSettingHandler implements HttpHandler {

    /**
     * Handles the incoming HTTP GET request to retrieve the reimbursement rates setting.
     * Converts the current rates setting to JSON and sends it as the response.
     *
     * @param exchange The HTTP exchange representing the incoming request and outgoing response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ReimbursementRatesSetting ratesSetting = ReimbursementRatesSetting.getInstance();

        // Convert the rates setting to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(ratesSetting);

        // Set response headers and send response
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, json.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(json.getBytes());
        }
    }
}
