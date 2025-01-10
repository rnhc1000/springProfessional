package br.dev.ferreiras.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO{

  private final List<FieldMessageDTO> errors = new ArrayList<>();

  public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path) {
    super(timestamp, status, error, path);
  }

  public List<FieldMessageDTO> getErrors() {
    return errors;
  }

  public void addErrors(String fieldName, String fieldMessage) {

    errors.removeIf(f -> f.getFieldName().equals(fieldName));

    errors.add(new FieldMessageDTO(fieldName, fieldMessage));
  }
}
