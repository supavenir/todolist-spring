package fr.caensup.sio.todo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.caensup.sio.todo.models.Utilisateur;
import fr.caensup.sio.todo.services.UtilisateurService;

@Controller
@SessionAttributes({ "user", "listes" })
public class MainController {
	
	@Autowired
	public UtilisateurService userService;

	@GetMapping("/")
	public String indexAction(Authentication auth,ModelMap model) {
		model.addAttribute("user",auth.getDetails());
		return "/index";
	}
	
	@GetMapping("/createUser/{login}/{password}")
	public String createUserAction(@PathVariable String login,@PathVariable String password) {
		Utilisateur user= userService.createUser(login, password);
		System.out.println(user +" créé");
		return "redirect:/";
	}
}
