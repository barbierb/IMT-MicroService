package fr.itspower.tp2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import fr.itspower.tp1.res.PersonneResource;

@SpringBootApplication
public class tp2app {

	public static void main(String[] args) {
        SpringApplication.run(tp2app.class,args);
        
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
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
