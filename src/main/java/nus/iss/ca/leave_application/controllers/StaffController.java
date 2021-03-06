package nus.iss.ca.leave_application.controllers;

import nus.iss.ca.leave_application.helper.LeaveStatusEnum;
import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.CompensationClaim;
import nus.iss.ca.leave_application.model.Overtime;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.model.User;
import nus.iss.ca.leave_application.services.ApplicationService;
import nus.iss.ca.leave_application.services.CompensationService;
import nus.iss.ca.leave_application.services.EmailService;
import nus.iss.ca.leave_application.services.OvertimeService;
import nus.iss.ca.leave_application.services.EmployeeService;
import nus.iss.ca.leave_application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/** @Author Fusheng Tan @Version 1.0 */
@Controller
@RequestMapping(value = "/staff")
public class StaffController {

    @Autowired
    private ApplicationService appService;
    
    @Autowired private UserService uService;
    @Autowired private OvertimeService oService;
    @Autowired private CompensationService cService;
	@Autowired
	private EmailService eservice;
	
	@Autowired
	private EmployeeService emservice;

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

// localhost:8081/staff/history/1/5?sortField=applicationId&sortDir=desc
    @RequestMapping(value = "/history/{pageNo}/{pageSize}")
    public String employeeApplicationHistory(HttpSession session, @PathVariable int pageNo,
			@PathVariable int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
        UserSession usession = (UserSession) session.getAttribute("usession");
        if (usession.getUser() != null) {
            System.out.println(usession.getEmployee());
            System.out.println(usession.getUser().getEmployeeId());
            if (!appService.findApplicationByEmployee(usession.getUser().getEmployeeId()).isEmpty()) {
//                model.addAttribute("ahistory", appService.findApplicationByEmployee(usession.getEmployee().getName()));
				
            	Page<Application> page = appService.findPaginated(pageNo, pageSize, usession.getEmployee().getName(),
						sortField, sortDir);
				List<Application> listApplication = page.getContent();
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				
				model.addAttribute("sortField", sortField);
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", (sortDir.equals("asc")) ? "desc" : "asc");
				
				model.addAttribute("listApplication", listApplication);
            }

            return "staff_application_history";
        }
        return "forward:/home/login";
    }

    @RequestMapping(value = "/application/create" , method = RequestMethod.GET)
    public ModelAndView newApplicationPage() {
        ModelAndView mav = new ModelAndView("staff_application_new");
        mav.addObject("application", new Application());
        return mav;
    }

    @RequestMapping(value = "/application/create" , method = RequestMethod.POST)
    public ModelAndView createNewApplication(
            @ModelAttribute("application") @Valid Application application, BindingResult result, HttpSession session)
            throws ParseException {
        UserSession usession = (UserSession) session.getAttribute("usession");
        if (result.hasErrors()) return new ModelAndView("staff_application_new");
        ModelAndView mav = new ModelAndView();
        String message = "New leave application" + application.getApplicationId() + "was created.";
        // display for checking
        System.out.println(message);
        System.out.println(application.getLeaveType());
        application.setEmployeeId(usession.getEmployee().getName());
        application.setStatus(LeaveStatusEnum.APPLIED);
        

        //display total calendar days
        System.out.println(application.getFromDate());
        System.out.println(application.getToDate());
        System.out.println("Total Days:");
        int days =
                (int)
                        ((application.getToDate().getTime() - application.getFromDate().getTime())
                                / (1000 * 3600 * 24));
        System.out.println(days);
        //display total counted leave days
        System.out.println("Leave Days:");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String from = df.format(application.getFromDate());
        String to = df.format(application.getToDate());

        System.out.println(from);
        System.out.println(to);

        Date date1 = df.parse(from);
        Date date2 = df.parse(to);

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        System.out.println(numberOfDays);


        //save in database(counted leave days)
        //save in database(calendar days)
        //If Leave period(Calendar days)<=14, weekends are excluded
        if(days<=14){
            application.setCountedLeaveDays(numberOfDays);
            application.setLeavePeriod(days);
        }else{
            application.setLeavePeriod(days);
            application.setCountedLeaveDays(days);
        }

        mav.setViewName("redirect:/staff/history/1/5?sortField=applicationId&sortDir=desc");
        appService.createApplication(application);
        //Send email
        String manager = usession.getEmployee().getManagerId();
        
        eservice.sendAppEmail(usession.getUser().getEmailAddress(), 
    			"Leave Application Confirmation", 
    			"<h1>Application Confirmation</h1> <br/><p>Your leave application has been sent to "+ manager+ ". You will be notified on approval/reject. Thank you!</p>");
        
       
        User e = uService.findUserByEmployeeId(manager);
        
        eservice.sendAppEmail(e.getEmailAddress(), 
    			"Leave Application From Employee", 
    			"<h1>Application for Leave</h1> <br/><p>A" + application.getLeaveType() +  "leave application has been sent to you from" + usession.getUser().getName() + ". It is pending approval. Thank you!</p>");

        return mav;
    }

    @RequestMapping(value = "/application/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editApplicationPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("staff_application_edit");
        Application application = appService.findApplication(id);
        mav.addObject("application",application);
        return mav;
    }


    @RequestMapping(value = "/application/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editApplicaton(
            @ModelAttribute("application") @Valid Application application,
            HttpSession session,
            @PathVariable("id")  String id,
            BindingResult result) throws ParseException {
        UserSession usession = (UserSession) session.getAttribute("usession");
        if (result.hasErrors()) {
            return new ModelAndView("staff_application_edit");
        }
        ModelAndView mav = new ModelAndView();
        System.out.println("Dates:" + application.getFromDate() + application.getToDate());
        String message =
                "New application " + application.getApplicationId() + " was successfully created.";
        System.out.println(message);

        //display total calendar days
        System.out.println(application.getFromDate());
        System.out.println(application.getToDate());
        System.out.println("Total Days:");
        int days =
                (int)
                        ((application.getToDate().getTime() - application.getFromDate().getTime())
                                / (1000 * 3600 * 24));
        System.out.println(days);
        //display total counted leave days
        System.out.println("Leave Days:");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String from = df.format(application.getFromDate());
        String to = df.format(application.getToDate());

        System.out.println(from);
        System.out.println(to);

        Date date1 = df.parse(from);
        Date date2 = df.parse(to);

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        System.out.println(numberOfDays);

        //save in database(counted leave days)
        //save in database(calendar days)
        //If Leave period(Calendar days)<=14, weekends are excluded
        if(days<=14){
            application.setCountedLeaveDays(numberOfDays);
            application.setLeavePeriod(days);
        }else{
            application.setLeavePeriod(days);
            application.setCountedLeaveDays(days);
        }

        application.setEmployeeId(usession.getEmployee().getName());
        application.setStatus(LeaveStatusEnum.UPDATED);

        mav.setViewName("redirect:/staff/history/1/5?sortField=applicationId&sortDir=desc");
        appService.changeApplication(application);
        return mav;
    }

    @RequestMapping(value ="/application/delete/{id}",method = RequestMethod.GET)
    public ModelAndView deleteApp(@PathVariable Integer id,HttpSession session){
        UserSession usession = (UserSession) session.getAttribute("usession");
        ModelAndView mav = new ModelAndView("forward:/staff/history/1/5?sortField=applicationId&sortDir=desc");
        Application application = appService.findApplication(id);
        application.setStatus(LeaveStatusEnum.DELETED);
        appService.changeApplication(application);
        return mav;
    }
    


    @RequestMapping(value="/overtime/history")
    public ModelAndView overtimeHistory(HttpSession session) {
        UserSession usession = (UserSession)session.getAttribute("usession");
        if (usession.getUser() != null) {
            System.out.println(usession.getEmployee());
            String empName = usession.getEmployee().getName();
            System.out.println(empName);
            List<Overtime> history = oService.findAllOvertimeByEmployeeId(empName);
            ModelAndView mView = new ModelAndView("staff_overtime_history", "otHistory", history);
            return mView;
            }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/overtime/create" , method = RequestMethod.GET)
    public ModelAndView createOvertime() {
        ModelAndView mav = new ModelAndView("staff_create_overtime");
        mav.addObject("overtime", new Overtime());
        return mav;
    }

    @RequestMapping(value = "/overtime/create" , method = RequestMethod.POST)
    public ModelAndView createOvertime(
            @ModelAttribute("overtime") @Valid Overtime overtime, @ModelAttribute("user") User user, BindingResult result, HttpSession session)
            throws ParseException {
        UserSession usession = (UserSession) session.getAttribute("usession");
        if (result.hasErrors()) {
            return new ModelAndView("staff_create_overtime");
        }
        ModelAndView mView = new ModelAndView();
        System.out.println("Overtime" + overtime.getOvertimeId() + "was recorded.");
        overtime.setEmployeeId(usession.getEmployee().getName());
        overtime.setStatus(LeaveStatusEnum.UPDATED);
        mView.setViewName("forward:/staff/overtime/history/");
        oService.createOvertime(overtime);
        return mView;
    }

    @RequestMapping(value="/compensation/history")
    public ModelAndView compensationHistory(HttpSession session) {
        UserSession usession = (UserSession)session.getAttribute("usession");
        if (usession.getUser() != null) {
            System.out.println(usession.getEmployee());
            String empName = usession.getEmployee().getName();
            System.out.println(empName);
            List<CompensationClaim> claims = cService.findAllClaimsByEmployeeName(empName);
            ModelAndView mView = new ModelAndView("staff_claim_history", "claims", claims);
            return mView;
            }
        return new ModelAndView("login");
    }
    @RequestMapping(value="/compensation/create", method = RequestMethod.POST)
    public ModelAndView createClaim(HttpSession session) {
        UserSession usession = (UserSession)session.getAttribute("usession");
        if (usession.getUser() != null) {
            System.out.println(usession.getEmployee());
            String empName = usession.getEmployee().getName();
            System.out.println(empName);
            List<Overtime> unclaimed = oService.findAllUnclaimedRecords(empName);
            if(unclaimed.size() > 0) {
                CompensationClaim claim = new CompensationClaim();
                claim.setEmployeeId(empName);
                Date dateApplied = java.sql.Date.valueOf(LocalDate.now());
                claim.setDateApplied(dateApplied);
                claim.setStatus(LeaveStatusEnum.APPLIED);
                List<Overtime> overtimes = new ArrayList<Overtime>();
                for(Overtime o: unclaimed) {
                    o.setStatus(LeaveStatusEnum.APPLIED);
                    oService.updateOvertime(o);
                    overtimes.add(o);
                }
                claim.setOvertimes(overtimes);
                cService.createCompensationClaim(claim);
            }
            return new ModelAndView("forward:/staff/compensation/history");
        }
        return new ModelAndView("login");
    }
}
