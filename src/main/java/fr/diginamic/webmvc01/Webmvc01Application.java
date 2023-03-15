package fr.diginamic.webmvc01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Webmvc01Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Webmvc01Application.class, args);
	}
	@Configuration
	 public class CorsConfig {

	     @Bean
	     public WebMvcConfigurer corsConfigurer() {
	         return new WebMvcConfigurer() {
	             @Override
	             public void addCorsMappings(CorsRegistry registry) {
	                 registry.addMapping("/**")
	                 	.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
	                 	.allowedOrigins("*")
	                 	.allowedHeaders("*");
	             }
	         };
	     }
	 }

	/**
	 * Configuration pour le chargement des 
	 * messages Intenationaux
	 * messages.properties
	 * @return
	 */
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = 
        		new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
