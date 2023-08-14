package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ReceiptType;

import java.io.IOException;
import java.util.List;

/**
 * Class representing updated receipt types.
 */
public class UpdatedReceiptTypes {

    // Private field to store a list of receipt types.
    private List<ReceiptType> types;

    /**
     * Default constructor for the UpdatedReceiptTypes class.
     */
    public UpdatedReceiptTypes() {
    }

    /**
     * Constructor for the UpdatedReceiptTypes class with specified receipt types.
     *
     * @param types The updated list of receipt types.
     */
    public UpdatedReceiptTypes(List<ReceiptType> types) {
        this.types = types;
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
     * Method to set a new list of receipt types.
     *
     * @param types The new list of receipt types.
     */
    public void setTypes(List<ReceiptType> types) {
        this.types = types;
    }

    /**
     * Static method for deserializing an object from JSON format.
     *
     * @param json JSON containing object data.
     * @return UpdatedReceiptTypes object deserialized from JSON.
     * @throws IOException Exception thrown in case of errors during deserialization.
     */
    public static UpdatedReceiptTypes fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, UpdatedReceiptTypes.class);
    }
}
