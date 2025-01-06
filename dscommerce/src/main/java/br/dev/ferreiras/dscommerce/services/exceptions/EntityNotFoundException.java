package br.dev.ferreiras.dscommerce.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
  public EntityNotFoundException(String message) {
    super(message);
  }
}
