package nus.iss.ca.leave_application.services;

import java.util.List;


import nus.iss.ca.leave_application.model.Overtime;

public interface OvertimeService {
    
     public List<Overtime> findAllOvertimeInAClaim(Integer claimId);

     public List<Overtime> findAllOvertimeByEmployeeId(String id);

     public void updateOvertime(Overtime overtime);

     public void deleteOvertime(Overtime overtime);

     public Overtime findOvertimeById(Integer id);

     public void createOvertime(Overtime overtime);

     public List<Overtime> findAllUnclaimedRecords(String id);
}
