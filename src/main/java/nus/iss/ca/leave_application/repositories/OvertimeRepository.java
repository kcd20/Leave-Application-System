package nus.iss.ca.leave_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.ca.leave_application.model.Overtime;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

    @Query("SELECT o FROM Overtime o WHERE o.employeeId = :id")
     public List<Overtime> findAllOvertimeByEmployeeId(@Param("id") String id);

    @Query("SELECT o FROM Overtime o, CompensationClaim c WHERE c.claimId=:claimId AND o.overtimeId MEMBER OF c.overtimes")
     public List<Overtime> findAllOvertimeInAClaim(@Param("claimId") Integer claimId);

    @Query("SELECT o FROM Overtime o WHERE o.overtimeId=:id")
     public Overtime findOvertimeById(@Param("id") Integer id);

     @Query("SELECT o FROM Overtime o WHERE o.employeeId=:id AND o.status='UPDATED'")
     public List<Overtime> findAllUnclaimedRecords(@Param("id") String id);
}
