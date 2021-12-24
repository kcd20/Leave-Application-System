package nus.iss.ca.leave_application.repositories;

import nus.iss.ca.leave_application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface RoleRepository extends JpaRepository<Role,String> {
    @Query("SELECT r.name FROM Role r")
    ArrayList<String> findAllRolesNames();

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    ArrayList<Role> findRoleByName(@Param("name") String name);

}
