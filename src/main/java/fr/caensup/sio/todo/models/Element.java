package fr.caensup.sio.todo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
}
