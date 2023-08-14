package utils;

/**
 * Singleton class representing the setting for the total reimbursement cost limit.
 */
public class TotalReimbursementCostLimitSetting {

    // Private static instance of the class.
    private static TotalReimbursementCostLimitSetting instance = null;

    // Private field to store the total reimbursement limit.
    private double totalReimbursementLimit;

    /**
     * Private constructor for the TotalReimbursementCostLimitSetting class.
     * Initializes the total reimbursement limit to a default value.
     */
    private TotalReimbursementCostLimitSetting() {
        totalReimbursementLimit = 10000;
    }

    /**
     * Method to get the singleton instance of the TotalReimbursementCostLimitSetting class.
     *
     * @return The singleton instance of the class.
     */
    public static synchronized TotalReimbursementCostLimitSetting getInstance() {
        if (instance == null) {
            instance = new TotalReimbursementCostLimitSetting();
        }
        return instance;
    }

    /**
     * Method to set the total reimbursement limit.
     *
     * @param limit The new total reimbursement limit.
     */
    public void setTotalReimbursementLimit(double limit) {
        totalReimbursementLimit = limit;
    }

    /**
     * Method to get the current total reimbursement limit.
     *
     * @return The current total reimbursement limit.
     */
    public double getTotalReimbursementLimit() {
        return totalReimbursementLimit;
    }
}
