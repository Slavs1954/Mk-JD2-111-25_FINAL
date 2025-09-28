package by.it_academy.jd2.dto.exceptions;

import by.it_academy.jd2.dto.errors.ErrorResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class PolyException extends RuntimeException {
    private final List<ErrorResponse> errors;

    public PolyException(List<ErrorResponse> errors) {
        super("Multiple errors occurred");
        this.errors = errors;
    }

    public PolyException(String message, List<ErrorResponse> errors) {
        super(message);
        this.errors = errors;
    }

    public PolyException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public PolyException(ErrorResponse error) {
        super("Multiple errors occurred");
        this.errors = List.of(error);
    }
}
