package br.dev.ferreiras.crudchallenge.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

  private final List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(Instant timestamp, Integer status, String error, String path) {
    super(timestamp, status, error, path);
  }

  public List<FieldMessage> getErrors() {

    return errors;
  }

  public void addErrors(String fieldName, String fieldMessage) {
    errors.add(new FieldMessage(fieldName, fieldMessage));
  }
}
