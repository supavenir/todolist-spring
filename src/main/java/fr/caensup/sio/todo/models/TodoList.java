package fr.caensup.sio.todo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Include
	private Integer id;
	private String nom;

	@ManyToOne
	private Utilisateur utilisateur;

	@Override
	public String toString() {
		return nom;
	}
}
