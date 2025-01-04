package br.dev.ferreiras.ormchallenge.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_atividades")
public class Atividade {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(columnDefinition = "TEXT")
  private String descricao;

  @Column(name = "preco")
  private Double price;

  @ManyToMany(mappedBy = "atividades")
  private List<Participante> participantes = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;

  @OneToMany(mappedBy = "atividade")
  private List<Bloco> blocos = new ArrayList<>();

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

  public List<Participante> getParticipantes() {
    return participantes;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public List<Bloco> getBlocos() {
    return blocos;
  }
}
