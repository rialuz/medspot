package io.medspot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is the main application. Running this class will start the SpringBoot server.
 */
@SpringBootApplication
@EnableSwagger2
public class AppRunner {

  /**
   * The main method
   */
  public static void main(String[] args) {
    SpringApplication.run(AppRunner.class);
  }

  /**
   * Configuration to allow cross origin requests from the front-end
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedOrigins("http://localhost:3000");
      }
    };
  }

}
