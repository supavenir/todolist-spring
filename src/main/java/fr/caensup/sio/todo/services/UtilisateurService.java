package fr.caensup.sio.todo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.caensup.sio.todo.exceptions.InvalidUserException;
import fr.caensup.sio.todo.exceptions.UserNotFoundException;
import fr.caensup.sio.todo.models.Utilisateur;
import fr.caensup.sio.todo.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepo;

	public Utilisateur findByLogin(String login) throws InvalidUserException {
		Optional<Utilisateur> opt = utilisateurRepo.findByLogin(login);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new InvalidUserException();
	}

	public Utilisateur getById(int id) throws UserNotFoundException {
		Optional<Utilisateur> opt = utilisateurRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new UserNotFoundException("Utilisateur d'identifiant " + id + " non trouvé !");
	}

}
