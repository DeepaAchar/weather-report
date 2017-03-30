package io.egen.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter{
	public void addCrossMappings(CorsRegistry registry){
		registry.addMapping("/api/*").allowedOrigins("http://mocker.egen.io")
		.allowedMethods("POST","GET","PUT","DELETE","PATCH","OPTIONS");

	}

}
