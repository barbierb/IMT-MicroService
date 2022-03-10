package fr.itspower.tp1.res;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/res")
public class PersonneResource {
	
	@GET
	@Path("/person")
	@Produces(MediaType.APPLICATION_JSON)
	public Personne hello(@QueryParam("prenom") String prenom,
			@QueryParam("age") int age) {
		return new Personne(prenom, age);
	}

}
