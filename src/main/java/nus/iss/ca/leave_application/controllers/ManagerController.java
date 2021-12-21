package nus.iss.ca.leave_application.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.services.ApplicationService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ApplicationService aService;
	
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
	
}