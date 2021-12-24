package nus.iss.ca.leave_application.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.ca.leave_application.model.Overtime;
import nus.iss.ca.leave_application.repositories.OvertimeRepository;

@Service
public class OvertimeServiceImpl implements OvertimeService {
    @Autowired
    private OvertimeRepository oRepo;

    @Override
    public List<Overtime> findAllOvertimeInAClaim(Integer claimId) {        
        return oRepo.findAllOvertimeInAClaim(claimId);
    }

    @Override
    public List<Overtime> findAllOvertimeByEmployeeId(String id) {        
        return oRepo.findAllOvertimeByEmployeeId(id);
    }

    @Override
    @Transactional
    public void updateOvertime(Overtime overtime) {
        oRepo.saveAndFlush(overtime);        
    }

    @Override
    @Transactional
    public void deleteOvertime(Overtime overtime) {
        oRepo.delete(overtime);        
    }

    @Override
    public Overtime findOvertimeById(Integer id) {
        return oRepo.findOvertimeById(id);
    }

    @Override
    @Transactional
    public void createOvertime(Overtime overtime) {
        oRepo.saveAndFlush(overtime);
    }
    
    @Override
    public List<Overtime> findAllUnclaimedRecords(String id) {
        return oRepo.findAllUnclaimedRecords(id);
    }
}
