package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTypeTest {

    @Test
    public void testReceiptType() {
        ReceiptType receiptType = new ReceiptType("Taxi", 200);

        assertEquals("Taxi", receiptType.getType());
        assertEquals(200, receiptType.getLimit(), 0.001); // Using delta for double comparison
    }

}