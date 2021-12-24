package nus.iss.ca.leave_application.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import nus.iss.ca.leave_application.helper.LeaveStatusEnum;

@Entity
public class CompensationClaim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="claim_id")
    private Integer claimId;

    private String employeeId;

    @OneToMany(
        targetEntity = Overtime.class,
        cascade = {CascadeType.ALL, CascadeType.PERSIST},
        fetch = FetchType.EAGER)
    private List<Overtime> overtimes;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_applied")
    private Date dateApplied;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_approved")
    private Date dateApproved;

    @Column(
          name = "status",
          columnDefinition =
                  "ENUM('APPLIED', 'UPDATED', 'DELETED', 'CANCELLED', 'APPROVED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    private String managerComment;

    public CompensationClaim() {}

    public CompensationClaim(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getClaimId() {
        return claimId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String id) {
        employeeId = id;
    }
    public List<Overtime> getOvertimes() {
        return overtimes;
    }
    public void setOvertimes(List<Overtime> aList) {
        overtimes = aList;
    }
    public Date getDateApplied() {
        return dateApplied;
    }
    public void setDateApplied(Date d) {
        dateApplied = d;
    }
    public Date getDateApproved() {
        return dateApproved;
    }
    public void setDateApproved(Date d) {
        dateApproved = d;
    }
    public Integer getTotalHours() {
        Integer sum = 0;
        for(Overtime o : overtimes) {
            sum += o.getHours();            
        }
        return sum;
    }
    public Double getTotalCompensation() {
        // 4 hours overtime = 0.5 days of leave
        Integer sum = getTotalHours();
        Double compensation = Math.round(((sum/4)*0.5)*2)/2.0;
        return compensation;
    }
    public String getStatus() {
        return status.toString();
    }
    public void setStatus(LeaveStatusEnum status) {
        this.status = status;
    }
    public String getManagerComment() {
        return managerComment;
    }
    public void setManagerComment(String comment) {
        managerComment = comment;
    }
}
