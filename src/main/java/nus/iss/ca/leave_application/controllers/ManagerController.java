package nus.iss.ca.leave_application.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.ca.leave_application.helper.Decision;
import nus.iss.ca.leave_application.helper.LeaveStatusEnum;
import nus.iss.ca.leave_application.helper.LeaveTypeEnum;
import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.services.ApplicationService;
import nus.iss.ca.leave_application.services.EmployeeService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ApplicationService aService;
	@Autowired
    private EmployeeService eService;

	@RequestMapping("/pending")
	public String pendingApprovals(HttpSession session, Model model) {
		UserSession usession = (UserSession)session.getAttribute("usession");
		ArrayList<Application> pList = new ArrayList<>(); 
		if(usession.getUser()!=null) {
			for(Employee sub: usession.getSubordinates()) {
				ArrayList<Application> li = aService.findPendingByEmpName(sub.getName());
				pList.addAll(li);
			}
			model.addAttribute("pending", pList);
			return "pendinglist";
		}
		return "login";
	}
	
	@RequestMapping("/apphistory")
	public ModelAndView subordinatesHistory(HttpSession session) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		HashMap<Employee, ArrayList<Application>> submap = new HashMap<Employee, ArrayList<Application>>();		
		for (Employee sub : usession.getSubordinates()) {
			submap.put(sub, aService.findHistoryByEmpName(sub.getName()));
		}
		ModelAndView mav = new ModelAndView("login");
		if (usession.getUser() != null && usession.getSubordinates() != null) {
			mav = new ModelAndView("sub-app-history");
			mav.addObject("submap", submap);
			return mav;
		}
		return mav;		
	}

    @RequestMapping(value="/application/view/{id}", method=RequestMethod.GET)
    public ModelAndView viewApplication(@PathVariable Integer id) {
        Application app = aService.findApplication(id);
        System.out.println(app);
        ModelAndView mView = new ModelAndView("manager-application-detail", "app", app);
        mView.addObject("decision", new Decision());
        Employee emp = eService.findEmployeeByName(app.getEmployeeId());
        mView.addObject("employee", emp);
        ArrayList<Object> otherApps = aService.findApplicationsWithinDate(app.getFromDate(), app.getToDate(), emp.getName());        
        mView.addObject("otherApps", otherApps);
        return mView;
    }

    @RequestMapping(value="/application/edit/{id}", method=RequestMethod.POST)
    public ModelAndView processApplication(@ModelAttribute("decision")
                                           @Valid Decision decision,
                                           BindingResult result,
                                           @PathVariable Integer id,
                                           HttpSession session) {
        UserSession usession = (UserSession) session.getAttribute("usession");
        if (result.hasErrors()) {
            return new ModelAndView("manager-application-detail");
        }
        Application application = aService.findApplication(id);
        String approval = decision.getDecision().trim();
        LeaveStatusEnum newStatus = null;
        if (approval.equals(LeaveStatusEnum.REJECTED.toString()))
            {
                newStatus = LeaveStatusEnum.REJECTED;
            }
        else if (approval.equals(LeaveStatusEnum.APPROVED.toString())) {
            newStatus = LeaveStatusEnum.APPROVED;
            Employee emp = eService.findEmployeeByName(application.getEmployeeId());
            if (application.getLeaveType().equals(LeaveTypeEnum.MEDICAL_LEAVE.getDisplayValue())) {
                emp.setMedicalLeaveRemaining(emp.getMedicalLeaveRemaining() - application.getCountedLeaveDays());
            }
            else if (application.getLeaveType().equals(LeaveTypeEnum.ANNUAL_LEAVE.getDisplayValue())) {
                emp.setAnnualLeaveRemaining(emp.getAnnualLeaveRemaining() - application.getCountedLeaveDays());
            }
        }
        application.setStatus(newStatus);
        application.setManagerComment(decision.getComment());
        aService.changeApplication(application);
        ModelAndView mView = new ModelAndView("forward:/manager/pending");
        // optional TODO: update popup to user
        return mView;
    }
}
