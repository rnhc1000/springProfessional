package br.dev.ferreiras.ormchallenge.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Bloco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate inicio;

  private LocalDate fim;

  public Bloco() {
  }

  public Bloco(Long id, LocalDate inicio, LocalDate fim) {
    this.id = id;
    this.inicio = inicio;
    this.fim = fim;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getInicio() {
    return inicio;
  }

  public void setInicio(LocalDate inicio) {
    this.inicio = inicio;
  }

  public LocalDate getFim() {
    return fim;
  }

  public void setFim(LocalDate fim) {
    this.fim = fim;
  }
}
