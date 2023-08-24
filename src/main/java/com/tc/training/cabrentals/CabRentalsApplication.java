package com.tc.training.cabrentals;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition( info = @Info( title = "cab-rentals", version = "v3" ) )
@CrossOrigin
public class CabRentalsApplication {

  public static void main( String[] args ) {
    SpringApplication.run( CabRentalsApplication.class, args );
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings( CorsRegistry registry ) {
        registry.addMapping( "/**" ).allowedOrigins( "*" ).allowedHeaders( "*" ).allowedMethods( "*" );
      }
    };
  }

}
