package com.devsuperior.dsmeta.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDateTime;

@Configuration
@EnableConfigurationProperties(MetaDataProperties.class)
public class MetaDataAutoConfiguration {

  private final MetaDataProperties metaDataProperties;

  public MetaDataAutoConfiguration(MetaDataProperties metaDataProperties) {
    this.metaDataProperties = metaDataProperties;
  }

  @Bean
  public MetaDataConfig metaDataConfig() {
    String dev = metaDataProperties.getDeveloper() == null ? System.getProperty("dev.name") : metaDataProperties.getDeveloper();
    String email = metaDataProperties.getEmail();
    String motivation = metaDataProperties.getMotivation();
    String time = metaDataProperties.getCreatedAt();

    MetaDataConfig metaDataConfig = new MetaDataConfig();
    metaDataConfig.put("DEV_NAME", dev);
    metaDataConfig.put("EMAIL", email);
    metaDataConfig.put("MOTIVATION", motivation);
    metaDataConfig.put("CREATED_AT", time);

    return metaDataConfig;
  }
}
