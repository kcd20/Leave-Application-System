package nus.iss.ca.leave_application.controllers;

import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin/employee")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminEmployeeController {
    @Autowired
    private EmployeeService eService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newEmployeePage() {
        ModelAndView mav = new ModelAndView("employee-new", "employee", new Employee());
        mav.addObject("eidlist", eService.findAllEmployeeIDs());
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewEmployee(@ModelAttribute @Valid Employee employee, BindingResult result) {
        if (result.hasErrors())
            return new ModelAndView("employee-new");
        ModelAndView mav = new ModelAndView();
        String message = "New employee " + employee.getName() + " was successfully created.";
        System.out.println(message);
        eService.createEmployee(employee);
        mav.setViewName("forward:/admin/employee/list");
        return mav;
    }

    @RequestMapping(value = "/list")
    public ModelAndView employeeListPage() {
        ModelAndView mav = new ModelAndView("employee-list");
        List<Employee> employeeList = eService.findAllEmployees();
        mav.addObject("employeeList", employeeList);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editEmployeePage(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("employee-edit");
        Employee employee = eService.findEmployee(id);
        mav.addObject("employee", employee);
        mav.addObject("eidlist", eService.findAllEmployeeIDs());
        return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editEmployee(@ModelAttribute @Valid Employee employee, BindingResult result,
                                     @PathVariable String id)  {

        if (result.hasErrors())
            return new ModelAndView("employee-edit");

        ModelAndView mav = new ModelAndView("forward:/admin/employee/list");
        String message = "Employee was successfully updated.";
        System.out.println(message);
        eService.changeEmployee(employee);
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable String id) {

        ModelAndView mav = new ModelAndView("forward:/admin/employee/list");
        Employee employee = eService.findEmployee(id);
        eService.removeEmployee(employee);
        String message = "The employee " + employee.getName() + " was successfully deleted.";
        System.out.println(message);
        return mav;
    }


}
