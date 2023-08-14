package utils;

/**
 * Singleton class representing the setting for the total mileage distance limit.
 */
public class TotalMileageDistanceLimitSetting {

    // Private static instance of the class.
    private static TotalMileageDistanceLimitSetting instance = null;

    // Private field to store the mileage limit.
    private double mileageLimit;

    /**
     * Private constructor for the TotalMileageDistanceLimitSetting class.
     * Initializes the mileage limit to a default value.
     */
    private TotalMileageDistanceLimitSetting() {
        this.mileageLimit = 400;
    }

    /**
     * Method to get the singleton instance of the TotalMileageDistanceLimitSetting class.
     *
     * @return The singleton instance of the class.
     */
    public static synchronized TotalMileageDistanceLimitSetting getInstance() {
        if (instance == null) {
            instance = new TotalMileageDistanceLimitSetting();
        }
        return instance;
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
     * Method to set the mileage limit.
     *
     * @param mileageLimit The new mileage limit.
     */
    public void setMileageLimit(double mileageLimit) {
        this.mileageLimit = mileageLimit;
    }
}
