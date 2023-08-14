package repository;

import model.ReimbursementClaim;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReimbursementRepositoryTest {

    private ReimbursementRepository repository;

    @BeforeEach
    void setUp() {
        repository = ReimbursementRepository.getInstance();
    }

    @Test
    void testAddClaim() {
        ReimbursementClaim claim = new ReimbursementClaim();
        repository.addClaim(claim);

        List<ReimbursementClaim> claims = repository.getAllClaims();
        assertEquals(1, claims.size());
        assertEquals(claim, claims.get(0));
    }

    @Test
    void testGetAllClaims() {
        ReimbursementClaim claim1 = new ReimbursementClaim();
        ReimbursementClaim claim2 = new ReimbursementClaim();

        repository.addClaim(claim1);
        repository.addClaim(claim2);

        List<ReimbursementClaim> claims = repository.getAllClaims();
        assertEquals(3, claims.size());
        assertEquals(claim1, claims.get(1));
        assertEquals(claim2, claims.get(2));
    }

    @Test
    void testGetAllClaimsCopy() {
        ReimbursementClaim claim = new ReimbursementClaim();
        repository.addClaim(claim);

        List<ReimbursementClaim> claims = repository.getAllClaims();
        assertEquals(4, claims.size());
    }
}