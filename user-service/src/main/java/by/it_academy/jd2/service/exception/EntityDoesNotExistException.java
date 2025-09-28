package by.it_academy.jd2.service.exception;

import by.it_academy.jd2.dto.exceptions.MonoException;

public class EntityDoesNotExistException extends MonoException {
    public EntityDoesNotExistException(String message) {
        super(message);
    }
    public EntityDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
