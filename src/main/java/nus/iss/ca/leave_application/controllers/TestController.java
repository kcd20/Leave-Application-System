package nus.iss.ca.leave_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nus.iss.ca.leave_application.model.Application;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Controller
public class TestController {
	
	@RequestMapping(value="/report", method = RequestMethod.GET)
	public String showReport(Model model) {
		Application app = new Application();
		return "report";
	}
	
	@RequestMapping(value="/annualreport", method = RequestMethod.GET)
	public String showAnnualReport(Model model) {
		return "annualreport";
	}
	
	@RequestMapping(value="/medicalreport", method = RequestMethod.GET)
	public String showMedicalReport(Model model) {
		return "medicalreport";
	}
	
	@RequestMapping(value="/compensationreport", method = RequestMethod.GET)
	public String showCompensationReport(Model model) {
		return "compensationreport";
	}
	
	
	@RequestMapping(value="/compensation", method = RequestMethod.GET)
	public String showPeriod(Model model) {
		return "compensation";
	}
}

