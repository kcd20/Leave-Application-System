package nus.iss.ca.leave_application.services;

import nus.iss.ca.leave_application.model.Role;
import nus.iss.ca.leave_application.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public ArrayList<Role> findAllRoles() {
        ArrayList<Role> ul = (ArrayList<Role>) roleRepository.findAll();
        return ul;
    }

    @Override
    public Role findRole(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role changeRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void removeRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public ArrayList<String> findAllRolesNames() {
        return roleRepository.findAllRolesNames();
    }

    @Override
    public ArrayList<Role> findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
