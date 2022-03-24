package fr.itspower.referenceproject.personnes.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import fr.itspower.referenceproject.livres.bdd.Livre;
import fr.itspower.referenceproject.livres.bdd.LivreRepository;
import fr.itspower.referenceproject.personnes.process.Personne;
import fr.itspower.referenceproject.personnes.process.PersonneRepository;

@Path("personnes")
public class PersonneResource {
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private LivreRepository livreRepository;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personne createPersonne(Personne p) {
		return personneRepository.save(p);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Personne> getAllPersonne() {
		List<Personne> personnes = new ArrayList<>();
		personneRepository.findAll().forEach(personnes::add);
		return personnes;
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personne updateTotalyPersonne(@PathParam("id") Long id, Personne p) {
		p.setId(id);
		return personneRepository.save(p);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePersonne(@PathParam("id") Long id) {
		if (personneRepository.findById(id).isPresent()) {
			personneRepository.deleteById(id);
		}
		return Response.noContent().build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getpersonneById(@PathParam("id") Long id) {
		Optional<Personne> p = personneRepository.findById(id);
		if (p.isPresent()) {
			return Response.ok(p.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	// PATCH /personnes/{id}
	public Response updateAge(@PathParam("id") Long id, Personne p) {
		int age = p.getAge();
		Optional<Personne> optional = personneRepository.findById(id);

		if (optional.isPresent()) {
			Personne pBDD = optional.get();
			pBDD.setAge(age);
			personneRepository.save(pBDD);
			return Response.ok(pBDD).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("{id}/livres")
	@Produces(MediaType.APPLICATION_JSON)
	// GET /personnes/{id}/livres
	public List<Livre> listerLivres(@PathParam("id") Long id) {
		return personneRepository.findById(id).get().getLivres();
	}

	@POST
	@Path("{idPersonne}/livres")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLivreDejaExistant(@PathParam("idPersonne") Long idPersonne, LivreInput livres) {
		Optional<Personne> pOpt = personneRepository.findById(idPersonne);
		Optional<Livre> lOpt = livreRepository.findById(livres.getIdLivre());

		if (!pOpt.isPresent() || !lOpt.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Personne p = pOpt.get();
		Livre l = lOpt.get();
		p.getLivres().add(l);
		personneRepository.save(p);
		return Response.ok(p).build();
	}
}
