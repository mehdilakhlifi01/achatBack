package org.sid.achatproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")//ga3 les methode dial /users    
		.allowedMethods("*")//"get" ,"post", "put" ,"delete" 
		.allowedOrigins("*");// les nom des domaine li an3tih l'access http://localhost:4200;

	}

}
