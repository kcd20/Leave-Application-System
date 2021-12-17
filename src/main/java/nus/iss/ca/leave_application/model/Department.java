package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", length = 25)
    private String name;

    @Column(name = "department_description")
    private String description;

    @Column(name = "manager_name", length = 25)
    private String managerName;

    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees = new ArrayList<>();
}