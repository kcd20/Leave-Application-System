package nus.iss.ca.leave_application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = -4354935610033647009L;

    @Id
    @Column(name = "role_id", length = 25)
    private String roleId;

    @Column(name = "role_name", length = 25)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "annual_leave_number")
    private Integer annualLeaveNum;

    @Column(name = "medical_leave_number")
    private Integer medicalLeaveNum;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAnnualLeaveNum() {
		return annualLeaveNum;
	}

	public void setAnnualLeaveNum(Integer annualLeaveNum) {
		this.annualLeaveNum = annualLeaveNum;
	}

	public Integer getMedicalLeaveNum() {
		return medicalLeaveNum;
	}

	public void setMedicalLeaveNum(Integer medicalLeaveNum) {
		this.medicalLeaveNum = medicalLeaveNum;
	}
    
    
}
