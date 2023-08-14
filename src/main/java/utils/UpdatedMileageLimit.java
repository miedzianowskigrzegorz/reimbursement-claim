package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Class representing an updated mileage limit.
 */
public class UpdatedMileageLimit {

    // Private field to store the updated mileage limit.
    private double mileageLimit;

    /**
     * Default constructor for the UpdatedMileageLimit class.
     */
    public UpdatedMileageLimit() {
    }

    /**
     * Constructor for the UpdatedMileageLimit class with a specified mileage limit.
     *
     * @param mileageLimit The updated mileage limit.
     */
    public UpdatedMileageLimit(double mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    /**
     * Method to get the current mileage limit.
     *
     * @return The current mileage limit.
     */
    public double getMileageLimit() {
        return mileageLimit;
    }

    /**
     * Method to set a new mileage limit.
     *
     * @param mileageLimit The new mileage limit.
     */
    public void setMileageLimit(double mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    /**
     * Static method for deserializing an object from JSON format.
     *
     * @param json JSON containing object data.
     * @return UpdatedMileageLimit object deserialized from JSON.
     * @throws IOException Exception thrown in case of errors during deserialization.
     */
    public static UpdatedMileageLimit fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, UpdatedMileageLimit.class);
    }
}
