package nus.iss.ca.leave_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** @Author Fusheng Tan @Version 1.0 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = -6152630994529091749L;

  @Id
  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "user_name", nullable = false, length = 25)
  private String name;

  @Column(name = "password", nullable = false, length = 32)
  @NotEmpty(message = "Sorry! Password can not be empty.")
  private String password;

  @Column(name = "email_address", nullable = false)
  @NotEmpty(message = "Sorry! Email can not be empty.")
  private String emailAddress;

  @Column(name = "employee_id")
  private String employeeId;

  @ManyToMany(
      targetEntity = Role.class,
      cascade = {CascadeType.ALL, CascadeType.PERSIST},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "userrole",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
  private List<Role> roleSet;

  @Transient private ArrayList<String> roleIds = new ArrayList<String>();
}
