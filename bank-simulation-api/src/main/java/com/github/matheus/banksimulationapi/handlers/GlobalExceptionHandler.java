package com.github.matheus.banksimulationapi.handlers;

import com.github.matheus.banksimulationapi.model.ErrorDetails;
import com.github.matheus.banksimulationapi.model.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetails> handleRuntimeException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "", request.getDescription(false));
        if (Objects.nonNull(ex.getCause())) {
            errorDetails.setMessage(ex.getCause().getMessage());
            return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        errorDetails.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails(LocalDateTime.now(), "Validation Failed", request.getDescription(false), errors);
        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);
    }

}