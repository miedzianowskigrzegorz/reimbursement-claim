package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Class representing updated reimbursement rates for daily allowances and mileage.
 */
public class UpdatedReimbursementRates {

    // Private fields to store daily allowance and mileage rates.
    private double dailyAllowanceRate;
    private double mileageRate;

    /**
     * Default constructor for the UpdatedReimbursementRates class.
     */
    public UpdatedReimbursementRates() {
    }

    /**
     * Constructor for the UpdatedReimbursementRates class with specified rates.
     *
     * @param dailyAllowanceRate The updated daily allowance rate.
     * @param mileageRate The updated mileage rate.
     */
    public UpdatedReimbursementRates(double dailyAllowanceRate, double mileageRate) {
        this.dailyAllowanceRate = dailyAllowanceRate;
        this.mileageRate = mileageRate;
    }

    /**
     * Method to get the current daily allowance rate.
     *
     * @return The current daily allowance rate.
     */
    public double getDailyAllowanceRate() {
        return dailyAllowanceRate;
    }

    /**
     * Method to set a new daily allowance rate.
     *
     * @param dailyAllowanceRate The new daily allowance rate.
     */
    public void setDailyAllowanceRate(double dailyAllowanceRate) {
        this.dailyAllowanceRate = dailyAllowanceRate;
    }

    /**
     * Method to get the current mileage rate.
     *
     * @return The current mileage rate.
     */
    public double getMileageRate() {
        return mileageRate;
    }

    /**
     * Method to set a new mileage rate.
     *
     * @param mileageRate The new mileage rate.
     */
    public void setMileageRate(double mileageRate) {
        this.mileageRate = mileageRate;
    }

    /**
     * Static method for deserializing an object from JSON format.
     *
     * @param json JSON containing object data.
     * @return UpdatedReimbursementRates object deserialized from JSON.
     * @throws IOException Exception thrown in case of errors during deserialization.
     */
    public static UpdatedReimbursementRates fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, UpdatedReimbursementRates.class);
    }
}
