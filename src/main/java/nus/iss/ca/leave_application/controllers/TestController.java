package nus.iss.ca.leave_application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Controller
public class TestController {
	
	@RequestMapping("/report")
	public String showReport(Model model) {
		return "report";
	}
	
	@RequestMapping("/compensation")
	public String showPeriod(Model model) {
		return "compensation";
	}
}
