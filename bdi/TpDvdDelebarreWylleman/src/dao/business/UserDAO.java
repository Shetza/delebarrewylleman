package dao.business;

import dao.core.DAOException;

import models.User;

import java.util.List;

public interface UserDAO {
	
	/**
	 * Cree et renvoir un nouvel objet metier de type <code>User</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>User</code> cree (jamais <code>null</code>).
	 */
	public User create() throws DAOException;
		
	/**
	 * Met a jour l'objet <code>User</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param user Le l'objet <code>User</code> a mettre a jour.
	 */
	public void update(User user) throws DAOException;
		
	/**
	 * Renvoie l'objet metier <code>User</code> associe a l'identifiant passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param id l'identifiant de l'objet a renvoyer.
	 * @return l'objet <code>User</code> associe a l'identifiant.
	 */
	public User getUserById(int id) throws DAOException;
	
	/**
	 * Renvoie l'objet <code>User</code> associe au login passe en parametre.
	 * Renvoie <code>null</code> si le login n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param login le login de l'utilisateur a renvoyer.
	 * @return l'objet <code>User</code> associe au login.
	 */
	public User getUserByLogin(String login) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de tous les utilisateurs satisfaisants les criteres passes en parametre.
	 * La liste renvoyee est vide si aucun enregistrement ne correspond aux criteres de selection specifies.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param lastName Le nom des utilisateurs a chercher.
	 * @param firstName Le prenom des utilisateurs a chercher.
	 * @param login Le nom d'utilisateur utilise pour se connecter des utilisateurs a chercher.
	 * @return la liste des utilisateurs (jamais <code>null</code>).
	 */
	public List search(String lastName, String firstName, String login) throws DAOException;
	
}