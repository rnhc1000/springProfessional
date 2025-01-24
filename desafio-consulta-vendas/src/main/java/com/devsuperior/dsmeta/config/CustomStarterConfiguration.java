package com.devsuperior.dsmeta.config;

import com.devsuperior.dsmeta.services.CustomInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomStarterConfiguration {

  @Bean
  public CustomInfoService customInfo() {
    return new CustomInfoService();
  }
}
