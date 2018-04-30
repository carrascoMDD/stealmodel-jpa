/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.web;

import org.modeldd.hortacasa.domain.UsuarioRepository;
import org.modeldd.hortacasa.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Antonio Carrasco Valero
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path="/hortacasa") // This means URL's start with /demo (after Application path)
public class UsuarioController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UsuarioRepository usuarioRepository;

	// Use with http://localhost:8080/hortacasa/crearUsuario?login=first&nombre=First&apellidos=SEcond&email=someemail@someemailprovider.com
	@GetMapping(path="/crearUsuario") // Map ONLY GET Requests
	public @ResponseBody String crearUsuario (@RequestParam String login, @RequestParam String nombre, @RequestParam String apellidos
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Usuario n = new Usuario();
		n.setLogin(login);
		n.setNombre(nombre);
		n.setApellidos(apellidos);
		n.setEmail(email);
		usuarioRepository.save(n);
		return "Saved with id=" + n.getId();
	}

	// Use with http://localhost:8080/hortacasa/todosUsuarios
	@GetMapping(path="/todosUsuarios")
	public @ResponseBody Iterable<Usuario> todosUsuarios() {
		// This returns a JSON or XML with the users
		return usuarioRepository.findAll();
	}
}
