package nus.iss.ca.leave_application.services;

import nus.iss.ca.leave_application.model.User;

import java.util.ArrayList;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface UserService {

    ArrayList<User> findAllUsers();

    User findUser(Long userId);

    User createUser(User user);

    User changeUser(User user);

    void removeUser(User user);

    User authenticate(String email, String pwd);


}
