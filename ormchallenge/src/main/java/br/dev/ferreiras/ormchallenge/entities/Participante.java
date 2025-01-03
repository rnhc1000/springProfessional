package br.dev.ferreiras.ormchallenge.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_participantes")
public class Participante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String email;

  public Participante() {
  }

  public Participante(Long id, String nome, String email) {
    this.id = id;
    this.nome = nome;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
