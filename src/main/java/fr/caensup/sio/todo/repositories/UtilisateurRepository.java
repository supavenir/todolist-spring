package fr.caensup.sio.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.caensup.sio.todo.models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}
