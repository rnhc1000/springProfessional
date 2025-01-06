package br.dev.ferreiras.crudchallenge.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String message){
    super(message);
  }
}
