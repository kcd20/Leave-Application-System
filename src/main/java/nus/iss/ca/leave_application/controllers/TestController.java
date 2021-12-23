package nus.iss.ca.leave_application.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.springframework.data.repository.query.Param;

import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.services.ApplicationService;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Controller
public class TestController {
	
    @Autowired
    private ApplicationService appService;
    
    
	@RequestMapping("/report")///{pageNo}/{pageSize}")
	public String showReport(Model model, @Param("keyword") String keyword) {
		
        	List<Application> listApplications = appService.listAll(keyword);
//		if (!appService.findApplicationByEmployee(keyword).isEmpty()) {
		
//			Page<Application> page = appService.findPaginated(pageNo, pageSize, keyword,
//				sortField, sortDir);
//		
//			model.addAttribute("currentPage", pageNo);
//			model.addAttribute("pageSize", pageSize);
//			model.addAttribute("totalPages", page.getTotalPages());
//			model.addAttribute("totalItems", page.getTotalElements());
//		
//			model.addAttribute("sortField", sortField);
//			model.addAttribute("sortDir", sortDir);
//			model.addAttribute("reverseSortDir", (sortDir.equals("asc")) ? "desc" : "asc");
		
			model.addAttribute("listApplications", listApplications);
			model.addAttribute("keyword", keyword);
//		}
		return "report";
     }
        
	
    @GetMapping("/report/export")
    public void exportToCSV(HttpServletResponse response, @Param("keyword") String keyword) throws IOException
    {
    	response.setContentType("text/csv");
    	String fileName = "users.csv";
    	
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=" + fileName;
    	
    	response.setHeader(headerKey, headerValue);
    	
    	List<Application> listApplications = appService.listAll(keyword);
    	
    	ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
    
    	String[] csvHeader = {"Application ID", "Employee ID", "Days", "Reason", "Status"};
    	
    	String[] nameMapping = {"application_id", "employee_id", "leave_days", "reason", "status"};
    	
    	csvWriter.writeHeader(csvHeader);
    	for(Application application : listApplications) {
    		csvWriter.write(application, nameMapping);
    	}
    	
    	csvWriter.close();
    }
	
    
	@RequestMapping(value="/annualreport", method = RequestMethod.GET)
	public String showAnnualReport(Model model,  @Param("keyword") String keyword) {
    	List<Application> listApplicationsA = appService.listAllAnnual(keyword);

	
		model.addAttribute("listApplicationsA", listApplicationsA);
		model.addAttribute("keyword", keyword);
		return "annualreport";
	}
	
	@RequestMapping(value="/medicalreport", method = RequestMethod.GET)
	public String showMedicalReport(Model model, @Param("keyword") String keyword) {
    	List<Application> listApplicationsM = appService.listAllMedical(keyword);
    	
		model.addAttribute("listApplicationsM", listApplicationsM);
		model.addAttribute("keyword", keyword);

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

