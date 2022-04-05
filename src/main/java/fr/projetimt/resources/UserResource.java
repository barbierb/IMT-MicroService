package fr.projetimt.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import fr.projetimt.entities.Category;
import fr.projetimt.entities.Element;
import fr.projetimt.entities.User;
import fr.projetimt.repositories.UserRepository;
import fr.projetimt.utils.UserSecured;

@Path("user")
public class UserResource implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(@RequestBody User u) {
		System.out.println(u);
		User dbuser = userRepository.getUserByUsername(u.getLogin());
		if (dbuser != null) {
			return Response.status(Response.Status.CONFLICT).entity("User already exists.").build();
		}
		
		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));

		userRepository.save(u);
		
		return Response.ok().entity("User created.").build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCateg(@PathParam("id") Long id) {

		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.CONFLICT).entity("User not found, strange because you are connected.").build();
		}
		userRepository.delete(uOpt.get());
		return Response.ok("ok").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSecured user = (UserSecured)authentication.getPrincipal();
		String username = user.getUsername();
		
		User u = userRepository.getUserByUsername(username);
		if (u == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		if (id != u.getId()) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		return Response.ok(u).build();
	}
	
	@GET
	@Path("{id}/cat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories(@PathParam("id") Long id) {
		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(uOpt.get().getCategories()).build();
	}
	
	@POST
	@Path("{id}/cat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCateg(@PathParam("id") Long id, @RequestBody Category cat) {

		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		User u = uOpt.get();
		u.getCategories().add(cat);
		userRepository.save(u);
		return Response.ok("ok").build();
	}

	
	@DELETE
	@Path("{id}/cat/{catid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCateg(@PathParam("id") Long id, @PathParam("catid") Long catid) {

		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.CONFLICT).entity("User not found, strange because you are connected.").build();
		}
		User u = uOpt.get();
		
		Category c = null;
		
		try {
			c = u.getCategoryById(catid);
		} catch (Exception e) {
			return Response.status(Response.Status.CONFLICT).entity("Category not found.").build();
		}
		
		u.getCategories().remove(c);
		userRepository.save(u);
		return Response.ok("ok").build();
	}

	@GET
	@Path("{id}/cat/{catid}/elem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addElement(@PathParam("id") Long id, @PathParam("catid") Long catid) {

		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.CONFLICT).entity("User not found, strange because you are connected.").build();
		}
		User u = uOpt.get();
		
		Category c = null;
		
		try {
			c = u.getCategoryById(catid);
		} catch (Exception ex) {
			return Response.status(Response.Status.CONFLICT).entity("Category not found.").build();
		}
		
		return Response.ok(c.getElements()).build();
	}

	@DELETE
	@Path("{id}/cat/{catid}/elem/{elemid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addElement(@PathParam("id") Long id, @PathParam("catid") Long catid, @PathParam("elemid") Long elemid) {

		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.CONFLICT).entity("User not found, strange because you are connected.").build();
		}
		User u = uOpt.get();
		
		Category c = null;
		
		try {
			c = u.getCategoryById(catid);
		} catch (Exception ex) {
			return Response.status(Response.Status.CONFLICT).entity("Category not found.").build();
		}

		Element el = null;
		
		try {
			el = c.getElementById(elemid);
		} catch (Exception ex) {
			return Response.status(Response.Status.CONFLICT).entity("Element not found.").build();
		}

		c.getElements().remove(el);
		userRepository.save(u);
		return Response.ok("ok").build();
	}

	@POST
	@Path("{id}/cat/{catid}/elem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addElement(@PathParam("id") Long id, @PathParam("catid") Long catid, @RequestBody Element e) {

		Optional<User> uOpt = userRepository.findById(id);
		if (!uOpt.isPresent()) {
			return Response.status(Response.Status.CONFLICT).entity("User not found, strange because you are connected.").build();
		}
		User u = uOpt.get();
		
		Category c = null;
		
		try {
			c = u.getCategoryById(catid);
		} catch (Exception ex) {
			return Response.status(Response.Status.CONFLICT).entity("Category not found.").build();
		}
		
		c.getElements().add(e);
		userRepository.save(u);
		return Response.ok("ok").build();
	}

	@Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new UserSecured(user);
    }
	
	

}
