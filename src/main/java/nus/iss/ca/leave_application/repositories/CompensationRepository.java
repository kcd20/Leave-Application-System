package nus.iss.ca.leave_application.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.CompensationClaim;

@Repository
public interface CompensationRepository extends JpaRepository<CompensationClaim, Integer> {

    @Query("SELECT c FROM CompensationClaim c WHERE c.employeeId=:name")
    public List<CompensationClaim> findAllClaimsByEmployeeName(@Param("name") String name);    

    @Query("SELECT c FROM CompensationClaim c WHERE c.claimId=:id")
    public CompensationClaim findClaimById(@Param("id") Integer id);

    @Query("SELECT c FROM CompensationClaim c JOIN Employee e ON c.employeeId=e.name WHERE e.managerId=:managerId AND c.status='APPLIED'")
    public List<CompensationClaim> findAllClaimsBySubordinates(@Param("managerId") String managerId);
    
    @Query("SELECT c FROM CompensationClaim c WHERE c.employeeId LIKE :keyword")
    public List<Application> findAllCom(String keyword);
    
    @Query("SELECT c FROM CompensationClaim c")
    public List<Application> findAllCom();
}
