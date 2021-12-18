package nus.iss.ca.leave_application.validators;

import nus.iss.ca.leave_application.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {return User.class.isAssignableFrom(clazz);}

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmpty(errors,"id","error.user.userid.empty");
        ValidationUtils.rejectIfEmpty(errors,"name","error.user.name.empty");
        ValidationUtils.rejectIfEmpty(errors,"password","error.user.password.empty");
        ValidationUtils.rejectIfEmpty(errors,"emailAddress","error.employee.email.empty");

    }
}
