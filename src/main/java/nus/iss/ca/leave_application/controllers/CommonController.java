package nus.iss.ca.leave_application.controllers;

import nus.iss.ca.leave_application.exception.UserNotFound;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.model.Role;
import nus.iss.ca.leave_application.model.User;
import nus.iss.ca.leave_application.services.EmployeeService;
import nus.iss.ca.leave_application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/** @Author Fusheng Tan @Version 1.0 */
@Controller
public class CommonController {

    @Autowired private UserService uService;

    @Autowired private EmployeeService eService;

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
                throw new UserNotFound();
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
            return "forward:/staff/history/1/1?sortField=applicationId&sortDir=asc";
        }
    }
}
