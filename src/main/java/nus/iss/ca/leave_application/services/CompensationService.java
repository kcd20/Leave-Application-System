package nus.iss.ca.leave_application.services;


import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.CompensationClaim;

import java.util.List;

public interface CompensationService {

    public List<CompensationClaim> findAllClaimsByEmployeeName(String name);

    public void createCompensationClaim(CompensationClaim claim);

    public void deleteCompensationClaim(CompensationClaim claim);

    public void updateCompensationClaim(CompensationClaim claim);

    public CompensationClaim findClaimById(Integer id);

    public List<CompensationClaim> findAllClaimsBySubordinates(String managerId);
    
    public List<Application> listAllCom(String keyword);
}
