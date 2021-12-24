package nus.iss.ca.leave_application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
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

  @Column(name="compensation_leave_remaining")
  private Double compensationLeaveRemaining;

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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getManagerId() {
	return managerId;
}

public void setManagerId(String managerId) {
	this.managerId = managerId;
}

public Integer getMedicalLeaveRemaining() {
	return medicalLeaveRemaining;
}

public void setMedicalLeaveRemaining(Integer medicalLeaveRemaining) {
	this.medicalLeaveRemaining = medicalLeaveRemaining;
}

public Integer getAnnualLeaveRemaining() {
	return annualLeaveRemaining;
}

public void setAnnualLeaveRemaining(Integer annualLeaveRemaining) {
	this.annualLeaveRemaining = annualLeaveRemaining;
}

public Double getCompensationLeaveRemaining() {
  return compensationLeaveRemaining;
}

public void setCompensationLeaveRemaining(Double compensation) {
  this.compensationLeaveRemaining = compensation;
}

public Employee() {
	super();
}
  
  
}
