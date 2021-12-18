package nus.iss.ca.leave_application.services;

import nus.iss.ca.leave_application.model.User;
import nus.iss.ca.leave_application.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepo;

    @Override
    @Transactional
    public ArrayList<User> findAllUsers() {
        return (ArrayList<User>)userRepo.findAll();
    }

    @Override
    @Transactional
    public User findUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User changeUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
            userRepo.delete(user);
    }

    @Override
    @Transactional
    public User authenticate(String email, String pwd) {
        return userRepo.findUserByEmailPwd(email, pwd);
    }
}
