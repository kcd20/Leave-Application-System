package nus.iss.ca.leave_application.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import nus.iss.ca.leave_application.model.Application;

public interface ApplicationService {

	ArrayList<Application> findPendingByEmpName(String EmpName);

	ArrayList<Application> findHistoryByEmpName(String EmpName);

	ArrayList<Application> findAllApplications();

	Application findApplication(Integer id);

	Application createApplication(Application app);

	Application changeApplication(Application app);

	void removeApplication(Application app);

	ArrayList<Application> findApplicationByEmployee(String eid);

	ArrayList<Application> findPendingApplicationByEmployee(String eid);
	
	Page<Application> findPaginated(int pageNo, int pageSize, String employeeId, String sortField, String sortDirection);
}
