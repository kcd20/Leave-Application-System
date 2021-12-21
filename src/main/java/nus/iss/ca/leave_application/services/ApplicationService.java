package nus.iss.ca.leave_application.services;

import java.util.ArrayList;

import nus.iss.ca.leave_application.model.Application;

public interface ApplicationService {

	ArrayList<Application> findPendingByEmpName(String EmpName);

	ArrayList<Application> findHistoryByEmpName(String EmpName);
	
}
