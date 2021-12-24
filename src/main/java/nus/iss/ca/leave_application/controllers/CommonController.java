package nus.iss.ca.leave_application.controllers;

import nus.iss.ca.leave_application.exception.UserNotFound;
import nus.iss.ca.leave_application.model.Application;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.model.Role;
import nus.iss.ca.leave_application.model.User;
import nus.iss.ca.leave_application.services.ApplicationService;
import nus.iss.ca.leave_application.services.EmployeeService;
import nus.iss.ca.leave_application.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/** @Author Fusheng Tan @Version 1.0 */
@Controller
public class CommonController {

    @Autowired private UserService uService;

    @Autowired private EmployeeService eService;
    
    @Autowired private ApplicationService aService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/home")
    public String logic(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/staffLogin")
    public String staffLogin(Model model) {
        model.addAttribute("user", new User());
        return "login_staff";
    }

    @RequestMapping(value = "/adminLogin")
    public String adminLogin(Model model) {
        model.addAttribute("user", new User());
        return "login_admin";
    }



    @RequestMapping(value = "/home/authenticate", method = RequestMethod.POST)
    public String authenticate(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {
        UserSession usession = new UserSession();
        if (bindingResult.hasErrors()) {
            return "login_staff";
        } else {
            User authUser = uService.authenticate(user.getEmailAddress(), user.getPassword());
            if (authUser==null){
                throw new UserNotFound("user does not exist");
            }
            usession.setUser(authUser);
            List<Role> roleSet = authUser.getRoleSet();
            // Display for checking
            for (Role role : roleSet) {
                System.out.println(role.toString());
            }
            usession.setEmployee(eService.findEmployeeByName(usession.getUser().getEmployeeId()));
            ArrayList<Employee> subordinates =
                    eService.findSubordinates(usession.getUser().getEmployeeId());

            if (subordinates != null) {
                usession.setSubordinates(subordinates);
            }
            System.out.println(usession);
            System.out.println(usession.getSubordinates());
            System.out.println(usession.getUser());
            session.setAttribute("usession", usession);
            return "forward:/staff/history/1/5?sortField=applicationId&sortDir=desc";
        }
    }
    
	//http://localhost:8081/movementregister/1/5?sortField=employeeId&sortDir=asc
	@RequestMapping(value = "/movementregister/current/{pageNo}/{pageSize}")
	public String movementregister(HttpSession session, @PathVariable int pageNo,
			@PathVariable int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();
		Month cMonth = currentDate.getMonth();
		Month pMonth = currentDate.getMonth().minus(1);
		Month nMonth = currentDate.getMonth().plus(1);
		model.addAttribute("cMonth", cMonth);
		if (usession.getUser() != null) {
			System.out.println(usession.getEmployee());
			if (aService.findAllApps(month).size() > 0) {
				Page<Application> page = aService.findAllApps(pageNo, pageSize,
						sortField, sortDir, month);
				List<Application> allApps = page.getContent();
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("sortField", sortField);
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
				model.addAttribute("allApps", allApps);
				model.addAttribute("pMonth", pMonth);
				model.addAttribute("nMonth", nMonth);

			}
			return "movement-register-current";
		}
		return "forward:/home/login";
	}
	
	@RequestMapping(value = "/movementregister/previous/{pageNo}/{pageSize}")
	public String movementregisterP(HttpSession session, 
			@PathVariable int pageNo,
			@PathVariable int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		LocalDate currentDate = LocalDate.now();
		int previousMonth = 0;
		if (currentDate.getMonthValue() == 0) {
			previousMonth = 12;
		} 
		else {
			previousMonth = currentDate.getMonthValue() - 1;
		}
		Month cMonth = currentDate.getMonth();
		Month pMonth = currentDate.getMonth().minus(1);
		Month nMonth = currentDate.getMonth().plus(1);
		model.addAttribute("cMonth", cMonth);
		if (usession.getUser() != null) {
			System.out.println(usession.getEmployee());
			if (aService.findAllApps(previousMonth).size() > 0) {
				Page<Application> page = aService.findAllApps(pageNo, pageSize,
						sortField, sortDir, previousMonth);
				List<Application> allApps = page.getContent();
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("sortField", sortField);
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
				model.addAttribute("allApps", allApps);
				model.addAttribute("pMonth", pMonth);
				model.addAttribute("nMonth", nMonth);

			}
			return "movement-register-previous";
		}
		return "forward:/home/login";
	}	
	
	@RequestMapping(value = "/movementregister/next/{pageNo}/{pageSize}")
	public String movementregisterN(HttpSession session, @PathVariable int pageNo,
			@PathVariable int pageSize,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		UserSession usession = (UserSession) session.getAttribute("usession");
		LocalDate currentDate = LocalDate.now();
		int nextMonth = 0;
		if (currentDate.getMonthValue() == 12) {
			nextMonth = 1;
		} 
		else {
			nextMonth = currentDate.getMonthValue() + 1;
		}
		Month cMonth = currentDate.getMonth();
		Month pMonth = currentDate.getMonth().minus(1);
		Month nMonth = currentDate.getMonth().plus(1);
		model.addAttribute("cMonth", cMonth);
		if (usession.getUser() != null) {
			System.out.println(usession.getEmployee());
			if (aService.findAllApps(nextMonth).size() > 0) {
				Page<Application> page = aService.findAllApps(pageNo, pageSize,
						sortField, sortDir, nextMonth);
				List<Application> allApps = page.getContent();
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("totalItems", page.getTotalElements());
				model.addAttribute("sortField", sortField);
				model.addAttribute("sortDir", sortDir);
				model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
				model.addAttribute("allApps", allApps);
				model.addAttribute("pMonth", pMonth);
				model.addAttribute("nMonth", nMonth);

			}
			return "movement-register-next";
		}
		return "forward:/home/login";
	}	
}
