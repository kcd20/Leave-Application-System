package nus.iss.ca.leave_application.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nus.iss.ca.leave_application.model.Employee;
import nus.iss.ca.leave_application.model.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private User user = null;
    private Employee employee = null;
    private ArrayList<Employee> subordinates = null;
}
