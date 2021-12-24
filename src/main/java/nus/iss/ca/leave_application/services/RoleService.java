package nus.iss.ca.leave_application.services;

import nus.iss.ca.leave_application.model.Role;

import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface RoleService {
    ArrayList<Role> findAllRoles();

    Role findRole(String roleId);

    Role createRole(Role role);

    Role changeRole(Role role);

    void removeRole(Role role);

    ArrayList<String> findAllRolesNames();

    ArrayList<Role> findRoleByName(String name);
}
