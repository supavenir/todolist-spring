package fr.caensup.sio.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.caensup.sio.todo.models.TodoList;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepo;

	public List<TodoList> getUserLists(String login) {
		return todoRepo.findByUserLogin(login);
	}
}
