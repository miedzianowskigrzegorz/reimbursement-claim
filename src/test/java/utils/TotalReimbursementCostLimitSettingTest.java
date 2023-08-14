package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalReimbursementCostLimitSettingTest {

    @Test
    public void testGetInstance() {
        TotalReimbursementCostLimitSetting instance1 = TotalReimbursementCostLimitSetting.getInstance();
        TotalReimbursementCostLimitSetting instance2 = TotalReimbursementCostLimitSetting.getInstance();

        assertEquals(instance1, instance2, "Instances should be the same");
    }

    @Test
    public void testSetAndGetTotalReimbursementLimit() {
        TotalReimbursementCostLimitSetting instance = TotalReimbursementCostLimitSetting.getInstance();
        double newLimit = 15000.0;

        instance.setTotalReimbursementLimit(newLimit);
        assertEquals(newLimit, instance.getTotalReimbursementLimit(), 0.001, "Limit should be set and retrieved correctly");
    }

}