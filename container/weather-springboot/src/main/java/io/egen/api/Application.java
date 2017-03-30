package io.egen.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.AbstractEnvironment;

import io.egen.api.config.SwaggerConfig;
import io.egen.api.config.WebConfig;

@SpringBootApplication
@Import({ WebConfig.class, SwaggerConfig.class})

public class Application {

	public static void main(String[] args) {
			//Fetch prod properties to run on production environment
			System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,"prod");
			SpringApplication.run(Application.class, args);

	}

}
