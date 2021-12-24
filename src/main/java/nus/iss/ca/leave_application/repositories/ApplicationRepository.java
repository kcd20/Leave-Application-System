package nus.iss.ca.leave_application.repositories;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.ca.leave_application.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	
	@Query("select a from Application a where a.employeeId=:name")
	ArrayList<Application> findHistByEmpName(@Param("name") String EmpName); 
	
	@Query("select a from Application a where a.employeeId=:name and a.status in ('APPLIED', 'UPDATED')")
	ArrayList<Application> findPendingByEmpName(@Param("name") String EmpName); 
		
	@Query("SELECT a from Application a WHERE :date between a.fromDate AND a.toDate AND a.leaveType = :leaveType")
	public ArrayList<Application> findAnnualLeave(@Param("date") LocalDate date, @Param("leaveType") String leaveType);

	@Query("Select a from Application a where a.employeeId = :eid")
	ArrayList<Application> findApplicationByEmployee( @Param("eid") String eid);

	@Query("Select a from Application a where a.employeeId =:eid And (a.status='APPLIED' or a.status = 'UPDATED')")
	ArrayList<Application> findPendingApplicationByEmployee(@Param("eid") String eid);

  @Query(nativeQuery = true, value="SELECT e.name, a.from_date, a.to_date, a.leave_type, a.reason, a.status FROM Application a JOIN Employee e ON a.employee_id LIKE e.name WHERE e.name NOT LIKE :empName AND ((DATE(:fromDate) BETWEEN a.from_date AND a.to_date) OR (DATE(:toDate) BETWEEN a.from_date AND a.to_date))")
  ArrayList<Object> findApplicationsWithinDate(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("empName") String empName);

  @Query("SELECT a from Application a WHERE a.employeeId = :eid")
  Page<Application> pageFindApplicationByEID(@Param("eid") String eid, Pageable pageable);
    
	@Query("SELECT a from Application a WHERE Month(a.fromDate) = :currentMonth AND a.status ='APPROVED'")
	ArrayList<Application> findAllLeaves(@Param("currentMonth") int currentMonth);
	
	@Query("SELECT a from Application a WHERE Month(a.fromDate) = :currentMonth AND a.status ='APPROVED'")
	Page<Application> findAllLeaves(@Param("currentMonth") int currentMonth,Pageable pageable);
  
  @Query("SELECT a FROM Application a WHERE a.employeeId LIKE :keyword" +  " AND a.fromDate BETWEEN (DATE(:d1)) AND (DATE(:d2))")
  public List<Application> findAll(String keyword, Date d1, Date d2);

  @Query("SELECT a FROM Application a WHERE a.employeeId LIKE :keyword" + " AND a.leaveType = 'Annual Leave'" +  " AND a.fromDate BETWEEN (DATE(:d1)) AND (DATE(:d2))")
  public List<Application> findAllAnnual(String keyword, Date d1, Date d2);

  @Query("SELECT a FROM Application a WHERE a.leaveType = 'Annual Leave'")
  public List<Application> findAllAnnual();

  @Query("SELECT a FROM Application a WHERE a.employeeId LIKE :keyword" + " AND a.leaveType = 'Medical Leave'" +  " AND a.fromDate BETWEEN (DATE(:d1)) AND (DATE(:d2))")
  public List<Application> findAllMedical(String keyword, Date d1, Date d2);

  @Query("SELECT a FROM Application a WHERE a.leaveType = 'Medical Leave'")
  public List<Application> findAllMedical();

}
