package fr.caensup.sio.todo.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public String userNotFoundAction() {
		return "Utilisateur inexistant";
	}
}
