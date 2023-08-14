package utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UpdatedTotalReimbursementCostLimitTest {

    @Test
    public void testGetTotalReimbursementLimit() {
        double limit = 20000.0;
        UpdatedTotalReimbursementCostLimit instance = new UpdatedTotalReimbursementCostLimit(limit);

        assertEquals(limit, instance.getTotalReimbursementLimit(), 0.001, "Limit should be retrieved correctly");
    }

    @Test
    public void testSetTotalReimbursementLimit() {
        double limit = 25000.0;
        UpdatedTotalReimbursementCostLimit instance = new UpdatedTotalReimbursementCostLimit();

        instance.setTotalReimbursementLimit(limit);
        assertEquals(limit, instance.getTotalReimbursementLimit(), 0.001, "Limit should be set correctly");
    }

    @Test
    public void testFromJson() throws IOException {
        String json = "{\"totalReimbursementLimit\": 30000.0}";
        UpdatedTotalReimbursementCostLimit instance = UpdatedTotalReimbursementCostLimit.fromJson(json);

        assertEquals(30000.0, instance.getTotalReimbursementLimit(), 0.001, "Object should be deserialized correctly");
    }

}