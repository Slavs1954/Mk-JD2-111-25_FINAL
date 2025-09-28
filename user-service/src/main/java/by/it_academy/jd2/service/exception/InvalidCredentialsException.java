package by.it_academy.jd2.service.exception;

import by.it_academy.jd2.dto.exceptions.MonoException;

public class InvalidCredentialsException extends MonoException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
}
