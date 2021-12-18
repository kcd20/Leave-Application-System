package nus.iss.ca.leave_application.services;

import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeRepository employeeRepo;

    @Override
    @Transactional
    public ArrayList<Employee> findEmployeesByManager(String name) {
        return employeeRepo.findEmployeesByManagerId(name);
    }

    @Override
    @Transactional
    public Employee findEmployeeByName(String name) {
        return employeeRepo.findEmployeeByName(name);
    }

    @Override
    @Transactional
    public ArrayList<Employee> findAllEmployees() {
        return (ArrayList<Employee>) employeeRepo.findAll();
    }

    @Override
    @Transactional
    public Employee findEmployee(String name) {
        return employeeRepo.findEmployeeByName(name);
    }

    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepo.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public Employee changeEmployee(Employee employee) {
        return employeeRepo.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public void removeEmployee(Employee employee) {
        employeeRepo.delete(employee);
    }

    @Override
    @Transactional
    public ArrayList<String> findAllManagerNames() {
        return employeeRepo.findAllManagerNames();
    }

    @Override
    @Transactional
    public ArrayList<Employee> findAllManagers() {
        return employeeRepo.findAllManagers();
    }

    @Override
    @Transactional
    public ArrayList<Employee> findSubordinates(String employeeId) {
        return employeeRepo.findSubordinates(employeeId);
    }

    @Override
    @Transactional
    public ArrayList<String> findAllEmployeeIDs() {
        return employeeRepo.findAllEmployeeIDs();
    }
}
