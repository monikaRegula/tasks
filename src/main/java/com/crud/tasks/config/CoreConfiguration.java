package com.crud.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2//włącza obsługę Swaggera
@Configuration
public class CoreConfiguration implements WebMvcConfigurer {

    @Bean
    //RestTemplate to klasa dostarczana przez Springa;
    //umożliwia realizacje żądań HTTP pomiezy serwerami
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)//wybiera typ dokumentacji
                .select()//rozpoczęcie budowania konfiguracji dla typu dokumentacji SWAGGER_2
                .apis(RequestHandlerSelectors.any())//metoda wybiera pakiety,które chcemy aby zostały porzeszukane w celu znalezienia controllerów
                .paths(PathSelectors.any())
                .build();
    }

    @Override//ta metoda nadaje Springowi prawo do przeszukiwania konkretneych katalogów
    //w celu udostępnienia ich użytkownikowi naszej apki
    //swagger udosteoia widoki czyli : HTML CSS JS
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // Required by Swagger UI configuration
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/").setCachePeriod(0);
        registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(0);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(0);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
