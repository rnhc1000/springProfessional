package com.devsuperior.dsmeta.config;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MetaData {


  private final MetaDataConfig metaDataConfig;

  public MetaData(MetaDataConfig metaDataConfig) {
    this.metaDataConfig = metaDataConfig;
  }

  public List<String> metaData() {
    String dev = metaDataConfig.getProperty("DEV_NAME");
    String email = metaDataConfig.getProperty("EMAIL");
    String motivation = metaDataConfig.getProperty("MOTIVATION");
    String createdAt = metaDataConfig.getProperty("CREATED_AT");

    List<String> properties = new ArrayList<>();
    properties.add(dev);
    properties.add(email);
    properties.add(motivation);
    properties.add(createdAt);

    return properties;
  }
}
