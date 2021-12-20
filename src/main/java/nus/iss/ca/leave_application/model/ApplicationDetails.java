package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import nus.iss.ca.leave_application.helper.LeaveStatusEnum;
import nus.iss.ca.leave_application.helper.LeaveTypeEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplicationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationDetailsId;

    @Column(name = "time_stamp")
    @Temporal(TemporalType.DATE)
    private Date timeStamp;

    @Column(
            name = "leave_type",
            columnDefinition = "ENUM('ANNUAL_LEAVE', 'MEDICAL_LEAVE', 'COMPENSATION_LEAVE')")
    @Enumerated(EnumType.STRING)
    private LeaveTypeEnum leaveType;

    @Column(name="applied_By")
    private String appliedBy;

    private String comment;

    // Reverse Relation
    @ManyToOne(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private Application application;
    @Column(name="status",
    columnDefinition =
    "ENUM('APPLIED', 'UPDATED', 'DELETED', 'CANCELLED', 'APPROVED', 'REJECTED')")
    private LeaveStatusEnum status;
}