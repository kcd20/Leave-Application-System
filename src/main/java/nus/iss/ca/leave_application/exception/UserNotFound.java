package nus.iss.ca.leave_application.exception;

public class UserNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UserNotFound(String msg) {
        super(msg);
    }
    public UserNotFound() {
        super();
    }
}
