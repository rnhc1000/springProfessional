package com.devsuperior.dsmeta.controllers.handlers;

import com.devsuperior.dsmeta.dto.ErrorResponseDTO;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> resourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponseDTO error = new ErrorResponseDTO(
        Instant.now(),
        status.value(),
        exception.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDTO> illegalArgument(IllegalArgumentException exception, HttpServletRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorResponseDTO error = new ErrorResponseDTO(
        Instant.now(),
        status.value(),
        exception.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(error);
  }

}
