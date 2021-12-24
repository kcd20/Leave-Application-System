package nus.iss.ca.leave_application.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.services.ApplicationService;
import nus.iss.ca.leave_application.services.CompensationService;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Controller
public class TestController {
	
    @Autowired
    private ApplicationService appService;
    
    @Autowired
    private CompensationService cService;
    
	@RequestMapping("/compensation")///{pageNo}/{pageSize}")
	public String showCompensation(Model model, @Param("keyword") String keyword) {
		
        	List<Application> listCompensations = cService.listAllCom(keyword);
		
			model.addAttribute("listCompensations", listCompensations);
			model.addAttribute("keyword", keyword);
		return "compensation";
     }
	
	@GetMapping("/compensation/export")
    public void exportToCSVCom(HttpServletResponse response, Model model, @Param("keyword") String keyword) throws IOException
    {
    	response.setContentType("text/csv");
    	String fileName = "Compensation.csv";
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=" + fileName;
    	
    	response.setHeader(headerKey, headerValue);
    	
    	List<Application> listCompensations = cService.listAllCom(keyword);
    	
    	
    	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    
    	String[] csvHeader = {"Claim ID", "Employee ID", "Overtimes", "Date Applied", "Date Approved", "Status", "Manager Comments"};
    	
    	String[] nameMapping = {"claimId", "employeeId", "", "overtimes", "dateApplied", "dateApproved", "status", "managerComment"};
    	
    	csvWriter.writeHeader(csvHeader);
    	for(Application compensation : listCompensations) {
    		csvWriter.write(compensation, nameMapping);
    	}
    	
    	csvWriter.close();
    }
    
	@RequestMapping("/report")///{pageNo}/{pageSize}")
	public String showReport(Model model, @Param("keyword") String keyword, 
			@Param("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, 
			@Param("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) {
		
        	List<Application> listApplications = appService.listAll(keyword, d1, d2);
		
			model.addAttribute("listApplications", listApplications);
			model.addAttribute("keyword", keyword);
		return "report";
     }
        
	
    @GetMapping("/report/export")
    public void exportToCSV(HttpServletResponse response, Model model, @Param("keyword") String keyword, 
    		@Param("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, 
			@Param("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) throws IOException
    {
    	response.setContentType("text/csv");
    	String fileName = "users.csv";
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=" + fileName;
    	
    	response.setHeader(headerKey, headerValue);
    	
    	List<Application> listApplications = appService.listAll(keyword,d1, d2);
    	
    	
    	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    
    	String[] csvHeader = {"Application ID", "Employee ID", "Days", "Reason", "Status"};
    	
    	String[] nameMapping = {"applicationId", "employeeId", "leavePeriod", "reason", "status"};
    	
    	csvWriter.writeHeader(csvHeader);
    	for(Application application : listApplications) {
    		csvWriter.write(application, nameMapping);
    	}
    	
    	csvWriter.close();
    }
	
    
	@RequestMapping(value="/annualreport", method = RequestMethod.GET)
	public String showAnnualReport(Model model,  @Param("keyword") String keyword, 
			@Param("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, 
			@Param("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) {
    	List<Application> listApplicationsA = appService.listAllAnnual(keyword, d1, d2);

	
		model.addAttribute("listApplicationsA", listApplicationsA);
		model.addAttribute("keyword", keyword);
		return "annualreport";
	}
	
    @GetMapping("/annualreport/export")
    public void annualexportToCSV(HttpServletResponse response, Model model, @Param("keyword") String keyword, 
    		@Param("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, 
			@Param("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) throws IOException
    {
    	response.setContentType("text/csv");
    	String fileName = "users.csv";
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=" + fileName;
    	
    	response.setHeader(headerKey, headerValue);
    	
    	List<Application> listApplications = appService.listAllAnnual(keyword,d1, d2);
    	
    	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    
    	String[] csvHeader = {"Application ID", "Employee ID", "Days", "Reason", "Status"};
    	
    	String[] nameMapping = {"applicationId", "employeeId", "leavePeriod", "reason", "status"};
    	
    	csvWriter.writeHeader(csvHeader);
    	for(Application application : listApplications) {
    		csvWriter.write(application, nameMapping);
    	}
    	
    	csvWriter.close();
    }
	
	@RequestMapping(value="/medicalreport", method = RequestMethod.GET)
	public String showMedicalReport(Model model, @Param("keyword") String keyword,
			@Param("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, 
			@Param("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) {
    	List<Application> listApplicationsM = appService.listAllMedical(keyword, d1, d2);
    	
		model.addAttribute("listApplicationsM", listApplicationsM);
		model.addAttribute("keyword", keyword);

		return "medicalreport";
	}
	
    @GetMapping("/medicalreport/export")
    public void medicalexportToCSV(HttpServletResponse response, Model model, @Param("keyword") String keyword, 
    		@Param("d1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d1, 
			@Param("d2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d2) throws IOException
    {
    	response.setContentType("text/csv");
    	String fileName = "users.csv";
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=" + fileName;
    	
    	response.setHeader(headerKey, headerValue);
    	
    	String thekey = keyword;
    	
    	List<Application> listApplications = appService.listAllMedical(thekey,d1, d2);
    	
    	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    
    	String[] csvHeader = {"Application ID", "Employee ID", "Days", "Reason", "Status"};
    	
    	String[] nameMapping = {"applicationId", "employeeId", "leavePeriod", "reason", "status"};
    	
    	csvWriter.writeHeader(csvHeader);
    	for(Application application : listApplications) {
    		csvWriter.write(application, nameMapping);
    	}
    	
    	csvWriter.close();
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

