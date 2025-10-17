package org.fve.springbootprojects.springbootstarterapp.exceptions;

public class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException() {
        super();
    }

    public UsernameUniqueViolationException(String message) {
        super(message);
    }

    public UsernameUniqueViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameUniqueViolationException(Throwable cause) {
        super(cause);
    }

    protected UsernameUniqueViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
