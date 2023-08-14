package repository;

import model.ReimbursementClaim;

import java.util.ArrayList;
import java.util.List;

/**
 * The ReimbursementRepository class represents a singleton repository for managing reimbursement claims.
 * It provides methods to add and retrieve reimbursement claims.
 */
public class ReimbursementRepository {
    private static ReimbursementRepository instance;
    private final List<ReimbursementClaim> claims;

    /**
     * Private constructor to initialize the ReimbursementRepository instance.
     * Initializes an empty list of reimbursement claims.
     */
    private ReimbursementRepository() {
        this.claims = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of the ReimbursementRepository class.
     *
     * @return The ReimbursementRepository instance.
     */
    public static synchronized ReimbursementRepository getInstance() {
        if (instance == null) {
            instance = new ReimbursementRepository();
        }
        return instance;
    }

    /**
     * Adds a reimbursement claim to the repository.
     *
     * @param claim The reimbursement claim to be added.
     */
    public void addClaim(ReimbursementClaim claim) {
        claims.add(claim);
    }

    /**
     * Retrieves a copy of all reimbursement claims stored in the repository.
     *
     * @return A list containing all reimbursement claims.
     */
    public List<ReimbursementClaim> getAllClaims() {
        return new ArrayList<>(claims);
    }
}
