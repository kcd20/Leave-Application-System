package nus.iss.ca.leave_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.ca.leave_application.model.CompensationClaim;

@Repository
public interface CompensationRepository extends JpaRepository<CompensationClaim, Integer> {

    @Query("SELECT c FROM CompensationClaim c WHERE c.employeeId=:name")
    public List<CompensationClaim> findAllClaimsByEmployeeName(@Param("name") String name);    

    @Query("SELECT c FROM CompensationClaim c WHERE c.claimId=:id")
    public CompensationClaim findClaimById(@Param("id") Integer id);

    @Query("SELECT c FROM CompensationClaim c JOIN Employee e ON c.employeeId=e.name WHERE e.managerId=:managerId")
    public List<CompensationClaim> findAllClaimsBySubordinates(@Param("managerId") String managerId);
}
