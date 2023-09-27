package fr.caensup.sio.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.caensup.sio.todo.models.TodoList;
import fr.caensup.sio.todo.services.TodoService;

@Controller
@SessionAttributes("user")
public class TodoController {
	@Autowired
	private TodoService todoService;

	@GetMapping("/todos")
	public ModelAndView indexAction(@SessionAttribute("user") String login) {
		List<TodoList> todos = todoService.getUserLists(login);
		return new ModelAndView("/todos/index", "todos", todos);
	}
}
