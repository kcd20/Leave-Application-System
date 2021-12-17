package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nus.iss.ca.leave_application.helper.LeaveStatusEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(
            name = "status",
            columnDefinition =
                    "ENUM('APPLIED', 'UPDATED', 'DELETED', 'CANCELLED', 'APPROVED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    @OneToMany(
            mappedBy = "application",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    private Collection<ApplicationDetails> applicationDetails = new ArrayList<ApplicationDetails>();

    @ManyToOne private Employee employee;

    public Application(Long applicationId) {
        this.applicationId = applicationId;
    }

    public void addApplicationDetails(ApplicationDetails ad) {
        this.applicationDetails.add(ad);
    }
}
