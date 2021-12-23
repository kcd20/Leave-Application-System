package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/** @Author Fusheng Tan @Version 1.0 */
@Entity
@Data
@NoArgsConstructor
// @AllArgsConstructor
public class Employee implements Serializable {

  private static final long serialVersionUID = 8799335959273275966L;

  @Id
  @Column(name = "name", length = 25)
  private String name;

  @Column(name = "manager_id", length = 25)
  private String managerId;

  @Column(name = "medical_leave_remaining")
  private Integer medicalLeaveRemaining;

  @Column(name = "annual_leave_remaining")
  private Integer annualLeaveRemaining;

  public Employee(
      String name, String managerId, Integer medicalLeaveRemaining, Integer annualLeaveRemaining) {
    this.name = name;
    this.managerId = managerId;
    this.medicalLeaveRemaining = medicalLeaveRemaining;
    this.annualLeaveRemaining = annualLeaveRemaining;
  }

  public Employee(String name) {
    setName(name);
  }
}
