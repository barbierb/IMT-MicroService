package fr.itspower.tp1;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.itspower.tp1.res.PersonneResource;

@SpringBootApplication
public class Tp1Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
	}

	@Component
	@ApplicationPath("/rest")
	@Configuration
	public class JerseyConfiguration extends ResourceConfig {
		public JerseyConfiguration() {
			register(PersonneResource.class);
		}
	}

}
