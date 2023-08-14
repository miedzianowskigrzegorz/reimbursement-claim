package service;

import model.Period;
import model.Receipt;
import model.ReimbursementClaim;
import org.junit.jupiter.api.Test;
import utils.ReimbursementRatesSetting;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceImplTest {

    @Test
    public void calculateTotalReimbursement() {
        List<Receipt> receipts = new ArrayList<>();
        receipts.add(new Receipt("Taxi", 150));
        receipts.add(new Receipt("Hotel", 1800));

        List<Period> periods = new ArrayList<>();
        periods.add(new Period(3));
        periods.add(new Period(2));

        double personalCarMileage = 150;

        ReimbursementClaim claim = new ReimbursementClaim("2023-08-14", receipts, periods, personalCarMileage);

        ReimbursementRatesSetting limits = ReimbursementRatesSetting.getInstance();
        limits.setDailyAllowanceRate(20);
        limits.setMileageRate(0.35);

        ReimbursementService reimbursementService = new ReimbursementServiceImpl();

        double totalReimbursement = reimbursementService.calculateTotalReimbursement(claim, limits);

        // expected: (150 + 1800) + (3 * 20 + 2 * 20) + (150 * 0.35)
        double expectedReimbursement = (150 + 1800) + (3 * 20 + 2 * 20) + (150 * 0.35);

        assertEquals(expectedReimbursement, totalReimbursement, 0.001);
    }
}