package service;

import model.Period;
import model.Receipt;
import model.ReimbursementClaim;
import utils.ReimbursementRatesSetting;

/**
 * Implementation of the ReimbursementService interface for calculating total reimbursements.
 */
public class ReimbursementServiceImpl implements ReimbursementService {

    /**
     * Calculates the total reimbursement amount based on the provided claim and limits.
     *
     * @param claim The reimbursement claim containing receipts, periods, and mileage.
     * @param limits The reimbursement rates and limits setting.
     * @return The calculated total reimbursement amount.
     */
    @Override
    public double calculateTotalReimbursement(ReimbursementClaim claim, ReimbursementRatesSetting limits) {
        double totalReimbursement = 0;

        // Calculate reimbursement from receipts.
        for (Receipt receipt : claim.getReceipts()) {
            totalReimbursement += receipt.getAmount();
        }

        // Calculate reimbursement from daily allowances.
        for (Period day : claim.getPeriods()) {
            totalReimbursement += day.getDays() * limits.getDailyAllowanceRate();
        }

        // Calculate reimbursement from personal car mileage.
        totalReimbursement += claim.getPersonalCarMileage() * limits.getMileageRate();

        return totalReimbursement;
    }
}
