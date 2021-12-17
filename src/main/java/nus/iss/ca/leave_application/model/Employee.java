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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name", length = 25)
    private String name;

    @OneToOne(mappedBy = "employee")
    private User user;

    @ManyToOne private Department department;

    @OneToMany(mappedBy = "employee")
    private List<Application> applications;

    @Column(name = "Medical_leave_remaining")
    private Integer MedicalLeaveRemaining;
    @Column(name = "Annual_leave_remaining")
    private Integer AnnualLeaveRemaining;
}