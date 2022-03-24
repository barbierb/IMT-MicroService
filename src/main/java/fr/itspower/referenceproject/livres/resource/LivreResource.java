package fr.itspower.referenceproject.livres.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import fr.itspower.referenceproject.livres.bdd.Livre;
import fr.itspower.referenceproject.livres.bdd.LivreRepository;

@Path("livres")
public class LivreResource {
    @Autowired
    private LivreRepository livreRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livre> listerLivres() {
        List<Livre> livres = new ArrayList<>();
        livreRepository.findAll().forEach(livres::add);
        return livres;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Livre creeerLivre(Livre l) {
        return livreRepository.save(l);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLivre(@PathParam("id") Long id) {
        if (livreRepository.findById(id).isPresent()) {
            try {
                livreRepository.deleteById(id);
            } catch (Exception e) {
                return Response.serverError().build();
            }
        }
        return Response.noContent().build();
    }
}
