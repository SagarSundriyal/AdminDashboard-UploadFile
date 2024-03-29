package com.uploadfile;

import jakarta.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.uploadfile")
public class SpringBootUploadImageApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootUploadImageApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
	  return new RestTemplate();
  }
}
