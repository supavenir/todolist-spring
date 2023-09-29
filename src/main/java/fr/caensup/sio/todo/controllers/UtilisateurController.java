package fr.caensup.sio.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fr.caensup.sio.todo.exceptions.InvalidUserException;
import fr.caensup.sio.todo.exceptions.UserNotFoundException;
import fr.caensup.sio.todo.models.TodoList;
import fr.caensup.sio.todo.models.Utilisateur;
import fr.caensup.sio.todo.repositories.UtilisateurRepository;
import fr.caensup.sio.todo.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository uRepository;

	@Autowired
	private UtilisateurService userService;

	@GetMapping("/users")
	public String indexAction(ModelMap model) {
		model.put("users", uRepository.findAll());
		return "/users/index";
	}

	@PostMapping("/users/filter")
	public ModelAndView filterAction(@RequestParam String filter) {
		ModelAndView mView = new ModelAndView("/users/index");
		mView.addObject("filter", filter);
		filter = "%" + filter + "%";
		mView.addObject("users", uRepository.filter(filter, filter));
		return mView;
	}

	@GetMapping("/users/{id}")
	public ModelAndView showUserAction(@PathVariable int id) throws UserNotFoundException {
		return new ModelAndView("/users/show", "user", userService.getById(id));
	}

	@GetMapping("/users/create")
	public ModelAndView createUserAction() {
		return new ModelAndView("/users/form", "user", new Utilisateur());
	}

	@PostMapping("/users/create")
	public RedirectView submitCreateUserAction(@ModelAttribute Utilisateur user, RedirectAttributes attrs,
			@RequestParam String myLists) {
		if (myLists != "") {
			for (String listName : myLists.split(",")) {
				TodoList list = new TodoList();
				list.setNom(listName);
				list.setUtilisateur(user);
				user.getListes().add(list);
			}
		}
		uRepository.save(user);
		attrs.addFlashAttribute("message", "Utilisateur " + user.getLogin() + " ajouté");
		return new RedirectView("/users");
	}

	@GetMapping("/users/update/{id}")
	public ModelAndView updateUserAction(@PathVariable int id) throws UserNotFoundException {
		return new ModelAndView("/users/form", "user", userService.getById(id));
	}

	@PostMapping("/users/update/{id}")
	public RedirectView submitUserAction(@ModelAttribute Utilisateur user) {
		uRepository.save(user);
		return new RedirectView("/users");
	}

	@PostMapping("/users/delete")
	public RedirectView deleteConfAction(RedirectAttributes attrs, @RequestParam int id) throws UserNotFoundException {
		Utilisateur user = userService.getById(id);
		attrs.addFlashAttribute("modal", true);
		attrs.addFlashAttribute("toDelete", user);
		return new RedirectView("/users");
	}

	@PostMapping("/users/delete/f")
	public RedirectView deleteAction(RedirectAttributes attrs, @RequestParam int id) throws UserNotFoundException {
		Utilisateur user = userService.getById(id);
		uRepository.delete(user);
		attrs.addFlashAttribute("message", user.getLogin() + " supprimé !");
		return new RedirectView("/users");
	}

}
