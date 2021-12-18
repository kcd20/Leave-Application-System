package nus.iss.ca.leave_application.services;

import nus.iss.ca.leave_application.model.Employee;

import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface EmployeeService {

    ArrayList<Employee> findEmployeesByManager(String s);

    Employee findEmployeeByName(String s);

    ArrayList<Employee> findAllEmployees();

    Employee findEmployee(String empid);

    Employee createEmployee(Employee emp);

    Employee changeEmployee(Employee emp);

    void removeEmployee(Employee emp);

    ArrayList<String> findAllManagerNames();

    ArrayList<Employee> findAllManagers();

    ArrayList<Employee> findSubordinates(String employeeId);

    ArrayList<String> findAllEmployeeIDs();

}
