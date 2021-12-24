package nus.iss.ca.leave_application.repositories;

import nus.iss.ca.leave_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.emailAddress=:email and u.password=:pwd")
    User findUserByEmailPwd(@Param("email")String email,@Param("pwd")String Pwd);
    
    @Query("select u from User u where u.employeeId = :name")
    User findUserByEmployeeId(@Param("name")String name);
}
