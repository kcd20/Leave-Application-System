package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nus.iss.ca.leave_application.helper.LeaveStatusEnum;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/** @Author Fusheng Tan @Version 1.0 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Application {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer applicationId;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "from_date")
  @NotNull(message="Sorry! The from date must be filled in")
  private Date fromDate;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "to_date")
  @NotNull(message="Sorry! The to date must be filled in")
  private Date toDate;

  @Column(name = "leave_type")
  @NotEmpty(message = "Sorry! The category of leave must be selected")
  private String leaveType;

  @Column(name = "reason")
  @NotEmpty(message = "Sorry! The reason must be filled in")
  private String reason;

  @Column(name = "contact_phone")
  @Pattern(
          message = "Please enter a valid contact number (Only numbers are allowed)",
          regexp = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")
  private String contactPhone;

  @Column(name = "leave_period")
  private Integer leavePeriod;

  @Column(name = "Leave_days")
  private Integer countedLeaveDays;

  @Column(name = "work_dissemination")
  private String workDissemination;

  @Column(name = "employee_id")
  private String employeeId;

  @Column(
          name = "status",
          columnDefinition =
                  "ENUM('APPLIED', 'UPDATED', 'DELETED', 'CANCELLED', 'APPROVED', 'REJECTED')")
  @Enumerated(EnumType.STRING)
  private LeaveStatusEnum status;

  @Column(name="manager_comment") 
  private String managerComment;

public Integer getApplicationId() {
	return applicationId;
}

public void setApplicationId(Integer applicationId) {
	this.applicationId = applicationId;
}

public Date getFromDate() {
	return fromDate;
}

public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
}

public Date getToDate() {
	return toDate;
}

public void setToDate(Date toDate) {
	this.toDate = toDate;
}

public String getLeaveType() {
	return leaveType;
}

public void setLeaveType(String leaveType) {
	this.leaveType = leaveType;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}

public String getContactPhone() {
	return contactPhone;
}

public void setContactPhone(String contactPhone) {
	this.contactPhone = contactPhone;
}

public Integer getLeavePeriod() {
	return leavePeriod;
}

public void setLeavePeriod(Integer leavePeriod) {
	this.leavePeriod = leavePeriod;
}

public Integer getCountedLeaveDays() {
	return countedLeaveDays;
}

public void setCountedLeaveDays(Integer countedLeaveDays) {
	this.countedLeaveDays = countedLeaveDays;
}

public String getWorkDissemination() {
	return workDissemination;
}

public void setWorkDissemination(String workDissemination) {
	this.workDissemination = workDissemination;
}

public String getEmployeeId() {
	return employeeId;
}

public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}

public LeaveStatusEnum getStatus() {
	return status;
}

public void setStatus(LeaveStatusEnum status) {
	this.status = status;
}

public String getManagerComment() {
	return managerComment;
}

public void setManagerComment(String managerComment) {
	this.managerComment = managerComment;
}
  
  
}
