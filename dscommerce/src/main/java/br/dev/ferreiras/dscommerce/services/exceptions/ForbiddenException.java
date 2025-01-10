package br.dev.ferreiras.dscommerce.services.exceptions;

public class ForbiddenException extends RuntimeException {

  public ForbiddenException(String message){
    super(message);
  }
}
