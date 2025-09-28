package by.it_academy.jd2.dto.exceptions;

public class MonoException extends RuntimeException {
    public MonoException(String message) {
        super(message);
    }
    public MonoException(String message, Throwable cause) {
        super(message, cause);
    }
    public MonoException(Throwable cause) {
        super(cause);
    }
}
