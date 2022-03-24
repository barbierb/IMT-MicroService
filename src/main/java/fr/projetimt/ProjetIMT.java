package fr.projetimt;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import fr.itspower.referenceproject.DemoApplication;

@Component
@ApplicationPath("api")
public class ProjetIMT extends ResourceConfig {
	
	public ProjetIMT() {
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
