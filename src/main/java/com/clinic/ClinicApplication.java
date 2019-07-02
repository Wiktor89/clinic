package com.clinic;

import com.clinic.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ClinicApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClinicApplication.class, args);
  }

//  @Bean
//  public AuditorAware<String> auditorProvider() {
//    return new AuditorAwareImpl();
//  }
}
