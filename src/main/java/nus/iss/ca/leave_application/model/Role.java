package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = -4354935610033647009L;

    @Id
    @Column(name = "role_id", length = 25)
    private String roleId;

    @Column(name = "role_name", length = 25)
    private String name;

    @Column(name = "description")
    private String description;

    private Integer annual_leave_number;

    private Integer medical_leave_number;
}
