package br.dev.ferreiras.dscommerce.controllers.handlers;

import br.dev.ferreiras.dscommerce.dto.CustomErrorDTO;
import br.dev.ferreiras.dscommerce.services.exceptions.DatabaseException;
import br.dev.ferreiras.dscommerce.services.exceptions.EntityNotFoundException;
import br.dev.ferreiras.dscommerce.services.exceptions.ForbiddenException;
import br.dev.ferreiras.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomErrorDTO> resourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomErrorDTO errorDTO = new CustomErrorDTO(
        Instant.now(),
        status.value(),
        exception.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(errorDTO);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<CustomErrorDTO> entityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomErrorDTO errorDTO = new CustomErrorDTO(
        Instant.now(),
        status.value(),
        exception.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(errorDTO);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<CustomErrorDTO> forbiddenException(ForbiddenException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.FORBIDDEN;
    CustomErrorDTO errorDTO = new CustomErrorDTO(
        Instant.now(),
        status.value(),
        exception.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(errorDTO);
  }



  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<CustomErrorDTO> databaseException(DatabaseException exception, HttpServletRequest request) {
    HttpStatus status = HttpStatus.CONFLICT;
    CustomErrorDTO errorDTO = new CustomErrorDTO(
        Instant.now(),
        status.value(),
        exception.getMessage(),
        request.getRequestURI()
    );

    return ResponseEntity.status(status).body(errorDTO);
  }


}
