package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.errors.StructuredErrorResponse;
import by.it_academy.jd2.dto.errors.ErrorResponse;
import by.it_academy.jd2.dto.exceptions.MonoException;
import by.it_academy.jd2.dto.exceptions.PolyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StructuredErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new ErrorResponse("error", fe.getDefaultMessage()))
                .toList();

        StructuredErrorResponse response = StructuredErrorResponse.builder()
                .logref("structured_error")
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StructuredErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        StructuredErrorResponse response = StructuredErrorResponse.builder()
                .logref("structured_error")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StructuredErrorResponse> handleGeneric(Exception ex) {
        StructuredErrorResponse response = StructuredErrorResponse.builder()
                .logref("structured_error")
                .errors(List.of(
                        ErrorResponse.builder()
                                .logref("exception")
                                .message(ex.getMessage() != null ? ex.getMessage() : ex.getClass().getSimpleName())
                                .build()
                ))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private ErrorResponse mapToStructuredError(FieldError error) {
        return ErrorResponse.builder()
                .logref(error.getField())
                .message(error.getDefaultMessage())
                .build();
    }
    @ExceptionHandler(MonoException.class)
    public ResponseEntity<ErrorResponse> handleMono(MonoException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .logref("error")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PolyException.class)
    public ResponseEntity<StructuredErrorResponse> handlePoly(PolyException ex) {
        StructuredErrorResponse response = StructuredErrorResponse.builder()
                .logref("structured_error")
                .errors(ex.getErrors())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
