package com.sothrose.assetflow_alert_service.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
  @Bean
  public ObjectMapper dlqObjectMapper() {
    return new ObjectMapper();
  }
}
