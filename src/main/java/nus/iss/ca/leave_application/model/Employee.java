package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 8799335959273275966L;

    @Id
    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "manager_id", length = 25)
    private String managerId;

    @OneToOne(mappedBy = "employee")
    private User user;

    @OneToOne(mappedBy = "manager")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Application> applications;

    @Column(name = "medical_leave_remaining")
    private Integer medicalLeaveRemaining;
    @Column(name = "annual_leave_remaining")
    private Integer annualLeaveRemaining;

    public  Employee (String name) { setName(name); }
}