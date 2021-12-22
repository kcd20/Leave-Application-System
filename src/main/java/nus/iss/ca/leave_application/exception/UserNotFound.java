package nus.iss.ca.leave_application.exception;

import lombok.NoArgsConstructor;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@NoArgsConstructor
public class UserNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UserNotFound(String msg) {
        super(msg);
    }
}
