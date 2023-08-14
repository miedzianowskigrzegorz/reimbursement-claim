package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @Test
    public void testPeriod() {

        Period period = new Period(5);

        assertEquals(5, period.getDays());
    }

}