package fr.caensup.sio.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "user", "listes" })
public class MainController {

	@GetMapping("/")
	public String indexAction() {
		return "/index";
	}
}
