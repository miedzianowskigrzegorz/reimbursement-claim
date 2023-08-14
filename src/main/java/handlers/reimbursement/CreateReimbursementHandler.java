package handlers.reimbursement;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.ReimbursementClaim;
import repository.ReimbursementRepository;
import service.ReimbursementServiceImpl;
import utils.ReimbursementRatesSetting;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A HTTP handler responsible for creating reimbursement claims.
 * This handler responds to POST requests by processing incoming reimbursement data and returning the result.
 */
public class CreateReimbursementHandler implements HttpHandler {

    private final ReimbursementServiceImpl reimbursementService;

    /**
     * Constructs the handler and initializes the reimbursement service.
     */
    public CreateReimbursementHandler() {
        this.reimbursementService = new ReimbursementServiceImpl();
    }

    /**
     * Handles the incoming HTTP POST request for creating a reimbursement claim.
     * Processes the incoming JSON data, calculates total reimbursement cost, and sends the response.
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

            // Deserialize JSON into a ReimbursementClaim object
            ReimbursementClaim reimbursementClaim = ReimbursementClaim.fromJson(requestBody.toString());

            // Calculate total reimbursement cost using the service
            double totalCost = reimbursementService.calculateTotalReimbursement(reimbursementClaim, ReimbursementRatesSetting.getInstance());
            reimbursementClaim.setTotalCost(totalCost);

            // Add reimbursement to repository
            ReimbursementRepository reimbursementRepository = ReimbursementRepository.getInstance();
            reimbursementRepository.addClaim(reimbursementClaim);

            // Prepare the response in JSON format
            String jsonResponse = ReimbursementClaim.toJson(reimbursementClaim);
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
