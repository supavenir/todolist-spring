package fr.caensup.sio.todo.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Utilisateur")
@Getter
@Setter
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String email = "";
	private String login = "";
	private String password = "";

	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TodoList> listes = new HashSet<TodoList>();

	@ManyToMany(mappedBy = "collaborateurs")
	private Set<TodoList> listesPartagees = new HashSet<TodoList>();
}
