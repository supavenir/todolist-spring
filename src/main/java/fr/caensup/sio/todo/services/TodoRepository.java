package fr.caensup.sio.todo.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.caensup.sio.todo.models.TodoList;

public interface TodoRepository extends CrudRepository<TodoList, Long> {

	@Query("SELECT t FROM TodoList t join t.utilisateur u WHERE u.login=:login")
	public List<TodoList> findByUserLogin(String login);
}
