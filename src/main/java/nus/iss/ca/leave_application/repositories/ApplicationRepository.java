package nus.iss.ca.leave_application.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.ca.leave_application.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	@Query("select a from Application a where a.employeeId=:name")
	ArrayList<Application> findHistByEmpName(@Param("name") String EmpName); 
	
	@Query("select a from Application a where a.employeeId=:name and a.status in ('APPLIED', 'UPDATED')")
	ArrayList<Application> findPendingByEmpName(@Param("name") String EmpName); 
		
	@Query("SELECT a from Application a WHERE :date between a.fromDate AND a.toDate AND a.leaveType = :leaveType")
	public ArrayList<Application> findAnnualLeave(@Param("date") LocalDate date, @Param("leaveType") String leaveType);
		
		
	
}
