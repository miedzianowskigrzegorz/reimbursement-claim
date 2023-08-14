package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Class representing an updated total reimbursement cost limit.
 */
public class UpdatedTotalReimbursementCostLimit {

    // Private field to store the total reimbursement cost limit.
    private double totalReimbursementLimit;

    /**
     * Default constructor for the UpdatedTotalReimbursementCostLimit class.
     */
    public UpdatedTotalReimbursementCostLimit() {
    }

    /**
     * Constructor for the UpdatedTotalReimbursementCostLimit class with a specified limit.
     *
     * @param totalReimbursementLimit The total reimbursement cost limit.
     */
    public UpdatedTotalReimbursementCostLimit(double totalReimbursementLimit) {
        this.totalReimbursementLimit = totalReimbursementLimit;
    }

    /**
     * Method to get the current total reimbursement cost limit.
     *
     * @return The current total reimbursement cost limit.
     */
    public double getTotalReimbursementLimit() {
        return totalReimbursementLimit;
    }

    /**
     * Method to set a new total reimbursement cost limit.
     *
     * @param totalReimbursementLimit The new total reimbursement cost limit.
     */
    public void setTotalReimbursementLimit(double totalReimbursementLimit) {
        this.totalReimbursementLimit = totalReimbursementLimit;
    }

    /**
     * Static method for deserializing an object from JSON format.
     *
     * @param json JSON containing object data.
     * @return UpdatedTotalReimbursementCostLimit object deserialized from JSON.
     * @throws IOException Exception thrown in case of errors during deserialization.
     */
    public static UpdatedTotalReimbursementCostLimit fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, UpdatedTotalReimbursementCostLimit.class);
    }
}
