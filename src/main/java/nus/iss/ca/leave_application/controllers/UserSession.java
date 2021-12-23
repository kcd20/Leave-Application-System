package nus.iss.ca.leave_application.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.model.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user = null;
    private Employee employee = null;
    private ArrayList<Employee> subordinates = null;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public ArrayList<Employee> getSubordinates() {
		return subordinates;
	}
	public void setSubordinates(ArrayList<Employee> subordinates) {
		this.subordinates = subordinates;
	}
    
    
}
