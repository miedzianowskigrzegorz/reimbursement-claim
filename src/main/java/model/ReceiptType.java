package model;

/**
 * Class representing a type of receipt with a specific spending limit.
 */
public class ReceiptType {

    // Private fields to store the type of receipt and its spending limit.
    private String type;
    private double limit;

    /**
     * Default constructor for the ReceiptType class.
     */
    public ReceiptType() {
    }

    /**
     * Constructor for the ReceiptType class with specified type and limit.
     *
     * @param type The type of receipt.
     * @param limit The spending limit for the receipt type.
     */
    public ReceiptType(String type, double limit) {
        this.type = type;
        this.limit = limit;
    }

    /**
     * Method to get the type of receipt.
     *
     * @return The type of receipt.
     */
    public String getType() {
        return type;
    }

    /**
     * Method to set the type of receipt.
     *
     * @param type The new type of receipt.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to get the spending limit for the receipt type.
     *
     * @return The spending limit for the receipt type.
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Method to set the spending limit for the receipt type.
     *
     * @param limit The new spending limit for the receipt type.
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }
}
