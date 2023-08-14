package utils;

/**
 * Class representing the setting for reimbursement rates.
 */
public class ReimbursementRatesSetting {

    // Private fields to store daily allowance and mileage rates.
    private double dailyAllowanceRate;
    private double mileageRate;

    // Private static instance of the class.
    private static ReimbursementRatesSetting instance;

    /**
     * Private constructor for the ReimbursementRatesSetting class.
     * Initializes the rates to default values.
     */
    private ReimbursementRatesSetting() {
        this.dailyAllowanceRate = 15;
        this.mileageRate = 0.3;
    }

    /**
     * Method to get the singleton instance of the ReimbursementRatesSetting class.
     *
     * @return The singleton instance of the class.
     */
    public static ReimbursementRatesSetting getInstance() {
        if (instance == null) {
            instance = new ReimbursementRatesSetting();
        }
        return instance;
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
     * Method to set the daily allowance rate.
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
     * Method to set the mileage rate.
     *
     * @param mileageRate The new mileage rate.
     */
    public void setMileageRate(double mileageRate) {
        this.mileageRate = mileageRate;
    }

    /**
     * Method to generate a string representation of the object.
     *
     * @return A string representation of the object's fields.
     */
    @Override
    public String toString() {
        return "ReimbursementRatesSetting{" +
                "dailyAllowanceRate=" + dailyAllowanceRate +
                ", mileageRate=" + mileageRate +
                '}';
    }
}
