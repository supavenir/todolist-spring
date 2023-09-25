package fr.caensup.sio.todo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.caensup.sio.todo.models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	public Optional<Utilisateur> findByLogin(String login);

	public List<Utilisateur> findByLoginLikeOrEmailLike(String login, String email);

	@Query("Select u FROM Utilisateur u WHERE u.login like :login OR u.email like :email")
	public List<Utilisateur> filter(String login, String email);
}
