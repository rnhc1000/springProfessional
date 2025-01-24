package com.devsuperior.dsmeta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDateTime;

@ConfigurationProperties(prefix = "rferreira.metadata")
public class MetaDataProperties {


  private String developer;
  private String email;
  private String createdAt;
  private String motivation;

  public MetaDataProperties() {
  }

  public MetaDataProperties(String developer, String email, String createdAt, String motivation) {
    this.developer = developer;
    this.email = email;
    this.createdAt = createdAt;
    this.motivation = motivation;
  }

  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getMotivation() {
    return motivation;
  }

  public void setMotivation(String motivation) {
    this.motivation = motivation;
  }
}
