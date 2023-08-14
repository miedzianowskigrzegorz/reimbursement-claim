package model;

/**
 * Class representing a receipt with a specific type and amount.
 */
public class Receipt {

    // Private fields to store the type of receipt and its amount.
    private String type;
    private double amount;

    /**
     * Default constructor for the Receipt class.
     */
    public Receipt() {
    }

    /**
     * Constructor for the Receipt class with specified type and amount.
     *
     * @param type The type of receipt.
     * @param amount The amount of the receipt.
     */
    public Receipt(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Method to get the type of the receipt.
     *
     * @return The type of the receipt.
     */
    public String getType() {
        return type;
    }

    /**
     * Method to set the type of the receipt.
     *
     * @param type The new type of the receipt.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to get the amount of the receipt.
     *
     * @return The amount of the receipt.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Method to set the amount of the receipt.
     *
     * @param amount The new amount of the receipt.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
