package br.dev.ferreiras.crudchallenge.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "tb_clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String cpf;
  private Double income;
  private LocalDate birthDate;
  private Integer children;

  public Client() {
  }

  public Client( Long id, String name,Integer children, LocalDate birthDate, Double income, String cpf) {
    this.name = name;
    this.id = id;
    this.children = children;
    this.birthDate = birthDate;
    this.income = income;
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getIncome() {
    return income;
  }

  public void setIncome(Double income) {
    this.income = income;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getChildren() {
    return children;
  }

  public void setChildren(Integer children) {
    this.children = children;
  }
}
