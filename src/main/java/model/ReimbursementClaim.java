package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Class representing a reimbursement claim with trip details, receipts, periods, and mileage.
 */
public class ReimbursementClaim {

    // Private fields to store trip details, receipts, periods, personal car mileage, and total cost.
    private String tripDate;
    private List<Receipt> receipts;
    private List<Period> periods;
    private double personalCarMileage;
    private double totalCost;

    /**
     * Default constructor for the ReimbursementClaim class.
     */
    public ReimbursementClaim() {
    }

    /**
     * Constructor for the ReimbursementClaim class with specified trip details, receipts, periods, and mileage.
     *
     * @param tripDate The trip date.
     * @param receipts List of receipts.
     * @param periods List of periods.
     * @param personalCarMileage Personal car mileage.
     */
    public ReimbursementClaim(String tripDate, List<Receipt> receipts, List<Period> periods, double personalCarMileage) {
        this.tripDate = tripDate;
        this.receipts = receipts;
        this.periods = periods;
        this.personalCarMileage = personalCarMileage;
    }

    // Getters and setters for the private fields.
    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public double getPersonalCarMileage() {
        return personalCarMileage;
    }

    public void setPersonalCarMileage(double personalCarMileage) {
        this.personalCarMileage = personalCarMileage;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Static method to deserialize a ReimbursementClaim object from JSON.
     *
     * @param json JSON representation of the object.
     * @return The deserialized ReimbursementClaim object.
     * @throws IOException Exception thrown in case of errors during deserialization.
     */
    public static ReimbursementClaim fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ReimbursementClaim.class);
    }

    /**
     * Static method to serialize a ReimbursementClaim object to JSON.
     *
     * @param reimbursementClaim The ReimbursementClaim object to serialize.
     * @return JSON representation of the object.
     * @throws IOException Exception thrown in case of errors during serialization.
     */
    public static String toJson(ReimbursementClaim reimbursementClaim) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(reimbursementClaim);
    }

    /**
     * Method to generate a string representation of the object.
     *
     * @return A string representation of the object's fields.
     */
    @Override
    public String toString() {
        return "ReimbursementClaim{" +
                "tripDate='" + tripDate + '\'' +
                ", receipts=" + receipts +
                ", periods=" + periods +
                ", personalCarMileage=" + personalCarMileage +
                ", totalCost=" + totalCost +
                '}';
    }
}
