package handlers.settings;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.ReimbursementRatesSetting;
import utils.UpdatedReimbursementRates;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A HTTP handler responsible for updating the reimbursement rate settings.
 * This handler processes POST requests containing JSON data to update the rates setting.
 */
public class UpdateReimbursementRateSettingsHandler implements HttpHandler {

    /**
     * Handles the incoming HTTP request to update the reimbursement rate settings.
     * Parses the incoming JSON data, updates the rates setting, and sends a response.
     *
     * @param exchange The HTTP exchange representing the incoming request and outgoing response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {

            // Read the incoming JSON data from the request body
            InputStreamReader reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            StringBuilder requestBody = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                requestBody.append(buffer, 0, bytesRead);
            }
            reader.close();

            // Parse JSON data and update the rates setting
            UpdatedReimbursementRates updatedReimbursementRates = UpdatedReimbursementRates.fromJson(requestBody.toString());
            ReimbursementRatesSetting ratesSetting = ReimbursementRatesSetting.getInstance();
            ratesSetting.setDailyAllowanceRate(updatedReimbursementRates.getDailyAllowanceRate());
            ratesSetting.setMileageRate(updatedReimbursementRates.getMileageRate());

            // Prepare and send the response
            String jsonResponse = "{\"message\": \"Settings saved successfully!\"}";
            byte[] jsonResponseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponseBytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponseBytes);
            os.close();
        } else {
            // Send an error response for invalid request method
            String response = "Invalid request method.";
            exchange.sendResponseHeaders(405, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}






