package nus.iss.ca.leave_application.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import nus.iss.ca.leave_application.helper.LeaveStatusEnum;

@Entity
public class Overtime implements Serializable{
    @Id
    @Column(name="overtime_id", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer overtimeId;

    private String employeeId;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    private Integer hours;    

    @Column(
          name = "status",
          columnDefinition =
                  "ENUM('APPLIED', 'UPDATED', 'DELETED', 'CANCELLED', 'APPROVED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    public Overtime() {}

    public Overtime(String employeeId) {
        setEmployeeId(employeeId);
    }
    public Overtime(String employeeId, Date date, Integer hours) {
        this.employeeId = employeeId;
        this.date = date;
        this.hours = hours;
    }
    public void setEmployeeId(String id) {
        employeeId = id;
    } 
    public String getEmployeeId() {
        return employeeId;
    }
    public Integer getOvertimeId() {
        return overtimeId;
    }
    public Integer getHours() {
        return hours;
    }
    public void setHours(Integer h) {
        this.hours = h;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getStatus() {
        return status.toString();
    }
    public void setStatus(LeaveStatusEnum newStatus) {
        status = newStatus;
    }
}
