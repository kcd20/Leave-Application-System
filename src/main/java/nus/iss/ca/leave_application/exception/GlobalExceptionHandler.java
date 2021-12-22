package nus.iss.ca.leave_application.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ModelAndView userExceptionHandler(Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("error","oops! your email or password is not correct, please try it again!");
        mv.setViewName("user_error");
        return mv;
    }
}
