package nus.iss.ca.leave_application.services;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.repositories.ApplicationRepository;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public ArrayList<Application> findAllApplications() {
		return (ArrayList<Application>) aRepo.findAll();
	}

	@Override
	@Transactional
	public Application findApplication(Integer id) {
		return aRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Application createApplication(Application app) {
		return  aRepo.saveAndFlush(app);
	}

	@Override
	@Transactional
	public Application changeApplication(Application app) {
		return  aRepo.saveAndFlush(app);
	}

	@Override
	@Transactional
	public void removeApplication(Application app) {
		aRepo.delete(app);
	}

	@Override
	@Transactional
	public ArrayList<Application> findApplicationByEmployee(String id) {
		return aRepo.findApplicationByEmployee(id);
	}

	@Override
	@Transactional
	public ArrayList<Application> findPendingApplicationByEmployee(String id) {
		return aRepo.findPendingApplicationByEmployee(id);
	}
}
