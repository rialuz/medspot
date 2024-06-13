package io.medspot.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This SwaggerConfig configs swagger user interface It allow users and developers to view
 * controllers and models information at a user friendly manner
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * This docket api builds a new docket using swagger2
   *
   * @return docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("/error").negate())
        .build();
  }
}