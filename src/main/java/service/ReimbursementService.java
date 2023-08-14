package service;

import model.ReimbursementClaim;
import utils.ReimbursementRatesSetting;

/**
 * Interface representing a service for calculating total reimbursements.
 */
public interface ReimbursementService {

    /**
     * Calculates the total reimbursement amount based on the provided claim and limits.
     *
     * @param claim The reimbursement claim containing receipts, periods, and mileage.
     * @param limits The reimbursement rates and limits setting.
     * @return The calculated total reimbursement amount.
     */
    double calculateTotalReimbursement(ReimbursementClaim claim, ReimbursementRatesSetting limits);

}
