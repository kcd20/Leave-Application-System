package nus.iss.ca.leave_application.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.repositories.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Resource
	private ApplicationRepository aRepo;
	
	@Override
	public ArrayList<Application> findPendingByEmpName(String EmpName) {
		ArrayList<Application> pList = new ArrayList<>();
		pList = aRepo.findPendingByEmpName(EmpName);
		return pList;
	}

	@Override
	public ArrayList<Application> findHistoryByEmpName(String EmpName) {
		ArrayList<Application> histList = new ArrayList<>();
		histList = aRepo.findHistByEmpName(EmpName);
		return histList;
	}

	
}
