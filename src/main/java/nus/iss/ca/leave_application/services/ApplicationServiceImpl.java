package nus.iss.ca.leave_application.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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

	@Override
	public ArrayList<Object> findApplicationsWithinDate(Date fromDate, Date toDate, String empName) {
        return aRepo.findApplicationsWithinDate(fromDate, toDate, empName);
    }
	
	@Override
	@Transactional
	public Page<Application> findPaginated(int pageNo, int pageSize, String employeeId, String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return aRepo.pageFindApplicationByEID(employeeId,pageable);
	}
	
	@Override
	@Transactional
	public ArrayList<Application> findAllApps(int currentMonth){
		return aRepo.findAllLeaves(currentMonth);
	}
	
	@Override
	@Transactional
	public Page<Application> findAllApps(int pageNo, int pageSize, String sortField, String sortDirection, int currentMonth){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
		return aRepo.findAllLeaves(currentMonth, pageable);
	}

	@Transactional
	public List<Application> listAll(String keyword, Date d1, Date d2){
		if(keyword != null) {
			return aRepo.findAll(keyword, d1, d2);
		}
		return aRepo.findAll();
	}
	
	@Transactional
	public List<Application> listAllAnnual(String keyword, Date d1, Date d2){
		if(keyword != null) {
			return aRepo.findAllAnnual(keyword, d1, d2);
		}
		return aRepo.findAllAnnual();
	}
	
	@Transactional
	public List<Application> listAllMedical(String keyword, Date d1, Date d2){
		if(keyword != null) {
			return aRepo.findAllMedical(keyword, d1, d2);
		}
		return aRepo.findAllMedical();
	}
	
//	@Transactional
//	public List<Application> listPeriod(Integer month){
//		if(month != null) {
//			return aRepo.findMonth(month);
//		}
//		return aRepo.findMonth();
//	}

}
