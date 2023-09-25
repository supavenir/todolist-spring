package fr.caensup.sio.todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.caensup.sio.todo.exceptions.InvalidUserException;
import fr.caensup.sio.todo.exceptions.UserNotFoundException;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(value = { InvalidUserException.class })
	@ResponseBody
	public String userNotValidAction() {
		return "Erreur : login ou mot de passe incorrect";
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView userNotFoundAction(UserNotFoundException exception) {
		return new ModelAndView("/error/404", "message", exception.getMessage());
	}
}
