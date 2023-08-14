package model;

/**
 * Class representing a period with a specified number of days.
 */
public class Period {

    // Private field to store the number of days in the period.
    private int days;

    /**
     * Default constructor for the Period class.
     */
    public Period() {
    }

    /**
     * Constructor for the Period class with a specified number of days.
     *
     * @param days The number of days in the period.
     */
    public Period(int days) {
        this.days = days;
    }

    /**
     * Method to get the number of days in the period.
     *
     * @return The number of days in the period.
     */
    public int getDays() {
        return days;
    }

    /**
     * Method to set the number of days in the period.
     *
     * @param days The new number of days in the period.
     */
    public void setDays(int days) {
        this.days = days;
    }
}
