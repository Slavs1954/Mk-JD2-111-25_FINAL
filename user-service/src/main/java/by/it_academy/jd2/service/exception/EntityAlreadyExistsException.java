package by.it_academy.jd2.service.exception;

import by.it_academy.jd2.dto.exceptions.MonoException;

public class EntityAlreadyExistsException extends MonoException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
