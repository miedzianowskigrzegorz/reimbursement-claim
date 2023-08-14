package utils;

import model.ReceiptType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Singleton class representing the available reimbursement receipt types.
 */
public class ReimbursementReceiptTypes {

    // Private static instance of the class.
    private static ReimbursementReceiptTypes instance = null;

    // Private field to store the list of receipt types.
    private List<ReceiptType> types;

    /**
     * Private constructor for the ReimbursementReceiptTypes class.
     * Initializes the list of receipt types with default values.
     */
    private ReimbursementReceiptTypes() {
        types = new ArrayList<>(Arrays.asList(
                new ReceiptType("Taxi", 200),
                new ReceiptType("Hotel", 2000),
                new ReceiptType("Plane Ticket", 3000),
                new ReceiptType("Train", 500),
                new ReceiptType("Meals", 150)
        ));
    }

    /**
     * Method to get the singleton instance of the ReimbursementReceiptTypes class.
     *
     * @return The singleton instance of the class.
     */
    public static synchronized ReimbursementReceiptTypes getInstance() {
        if (instance == null) {
            instance = new ReimbursementReceiptTypes();
        }
        return instance;
    }

    /**
     * Method to get the current list of receipt types.
     *
     * @return The current list of receipt types.
     */
    public List<ReceiptType> getTypes() {
        return types;
    }

    /**
     * Method to set the list of receipt types.
     *
     * @param types The new list of receipt types.
     */
    public void setTypes(List<ReceiptType> types) {
        this.types = types;
    }
}
