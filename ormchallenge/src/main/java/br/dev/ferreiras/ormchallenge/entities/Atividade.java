package br.dev.ferreiras.ormchallenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_atividades")
public class Atividade {

  private Long id;

  private String nome;

  @Column(columnDefinition = "TEXT")
  private String descricao;

  private Double price;

  public Atividade() {
  }

  public Atividade(Long id, String nome, String descricao, Double price) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.price = price;
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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
