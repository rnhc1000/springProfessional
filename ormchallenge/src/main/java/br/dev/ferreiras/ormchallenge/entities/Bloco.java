package br.dev.ferreiras.ormchallenge.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_blocos")
public class Bloco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime inicio;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime fim;

  public Bloco() {
  }

  public Bloco(Long id, LocalDateTime inicio, LocalDateTime fim) {
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

  public LocalDateTime getInicio() {
    return inicio;
  }

  public void setInicio(LocalDateTime inicio) {
    this.inicio = inicio;
  }

  public LocalDateTime getFim() {
    return fim;
  }

  public void setFim(LocalDateTime fim) {
    this.fim = fim;
  }
}
