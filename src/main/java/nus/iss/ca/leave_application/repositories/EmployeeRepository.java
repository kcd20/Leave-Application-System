package nus.iss.ca.leave_application.repositories;

import nus.iss.ca.leave_application.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e where e.name = :name")
    Employee findEmployeeByName(@Param("name") String name);

    @Query("SELECT e FROM Employee e where e.managerId = :mgr")
    ArrayList<Employee> findEmployeesByManagerId(@Param("mgr") String managerName);

    @Query("SELECT DISTINCT m FROM Employee e, Employee m where e.managerId = m.name")
    ArrayList<Employee> findAllManagers();

    @Query("SELECT DISTINCT m.name FROM Employee e, Employee m where e.managerId = m.name ")
    ArrayList<String> findAllManagerNames();

    @Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.name = e2.managerId AND e1.name= :eid")
    ArrayList<Employee> findSubordinates(@Param("eid") String eid);

    @Query("SELECT DISTINCT e.name FROM Employee e")
    ArrayList<String> findAllEmployeeIDs();
    
}
