package by.it_academy.jd2.service.exception;

import by.it_academy.jd2.dto.exceptions.MonoException;

public class UnverifiedLoginException extends MonoException {
    public UnverifiedLoginException(String message) {
        super(message);
    }
    public UnverifiedLoginException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnverifiedLoginException(Throwable cause) {
        super(cause);
    }
}
