package nus.iss.ca.leave_application.repositories;

import nus.iss.ca.leave_application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface RoleRepository extends JpaRepository<Role,String> {
}
