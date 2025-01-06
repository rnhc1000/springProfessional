package br.dev.ferreiras.crudchallenge.controllers.handlers;


import br.dev.ferreiras.crudchallenge.dto.CustomError;
import br.dev.ferreiras.crudchallenge.dto.ValidationError;
import br.dev.ferreiras.crudchallenge.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionsHandler  {

  private static final String INVALID_DATA = "Invalid data";

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error = new CustomError(
        Instant.now(), status.value(), exception.getMessage(), request.getRequestURI()
    );

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomError> methodArgumentInvalid(MethodArgumentNotValidException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError errors = new ValidationError(
        Instant.now(), status.value(), INVALID_DATA, request.getRequestURI()
    );
   for (FieldError error : exception.getBindingResult().getFieldErrors()) {
     errors.addErrors(error.getField(), error.getDefaultMessage());
   }

    return ResponseEntity.status(status).body(errors);
  }
}
