package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import nus.iss.ca.leave_application.helper.LeaveStatusEnum;
import nus.iss.ca.leave_application.helper.LeaveTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/** @Author Fusheng Tan @Version 1.0 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Application {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long applicationId;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "from_date")
  private Date fromDate;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "to_date")
  private Date toDate;

  @Column(name = "leave_type", columnDefinition = "ENUM('ANNUAL_LEAVE', 'MEDICAL_LEAVE', 'COMPENSATION_LEAVE')")
  @Enumerated(EnumType.STRING)
  @NotEmpty(message = "Sorry! The category of leave must be selected")
  private LeaveTypeEnum leaveType;

  @Column(name = "reason")
  @NotEmpty(message = "Sorry! The reason must be filled in")
  private String reason;

  @Column(name = "contact_phone")
  private String contactPhone;

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

  // @OneToMany(
  //     mappedBy = "application",
  //     cascade = {CascadeType.ALL},
  //     fetch = FetchType.EAGER)
  // private Collection<ApplicationDetails> applicationDetails = new ArrayList<ApplicationDetails>();

  public Application(Long applicationId) {
    this.applicationId = applicationId;
  }

  public Application(LeaveTypeEnum leaveType) {
    this.leaveType = leaveType;
  }

  // public void addApplicationDetails(ApplicationDetails ad) {
  //   this.applicationDetails.add(ad);
  // }
}
