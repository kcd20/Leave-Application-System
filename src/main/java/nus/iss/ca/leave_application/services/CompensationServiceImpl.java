package nus.iss.ca.leave_application.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.CompensationClaim;
import nus.iss.ca.leave_application.repositories.CompensationRepository;

@Service
public class CompensationServiceImpl implements CompensationService {
    @Autowired
    private CompensationRepository cRepo;
    @Override
    public List<CompensationClaim> findAllClaimsByEmployeeName(String name) {        
        return cRepo.findAllClaimsByEmployeeName(name);
    }

    @Override
    @Transactional
    public void createCompensationClaim(CompensationClaim claim) {
        cRepo.saveAndFlush(claim);
        
    }

    @Override
    @Transactional
    public void deleteCompensationClaim(CompensationClaim claim) {
        cRepo.delete(claim);
    }

    @Override
    @Transactional
    public void updateCompensationClaim(CompensationClaim claim) {
        cRepo.saveAndFlush(claim);        
    }

    @Override
    public CompensationClaim findClaimById(Integer id) {
        return cRepo.findClaimById(id);
    }

    @Override
    public List<CompensationClaim> findAllClaimsBySubordinates(String managerId) {
        return cRepo.findAllClaimsBySubordinates(managerId);
    }
    
	public List<Application> listAllCom(String keyword){
		if(keyword != null) {
			return cRepo.findAllCom(keyword);
		}
		return cRepo.findAllCom();
	}
    
}
