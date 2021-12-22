package nus.iss.ca.leave_application.validators;

import nus.iss.ca.leave_application.model.Application;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

public class ApplicationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Application.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Application application = (Application) target;
        ValidationUtils.rejectIfEmpty(errors, "leaveType", "error.application.leaveType.empty");
        ValidationUtils.rejectIfEmpty(errors, "reason", "error.application.reason.empty");
    }
}
