package dao.business;

import dao.core.DAOException;
import dao.core.ModelDAO;

import models.User;
import models.Model;

import java.util.List;

public interface UserDAO extends ModelDAO {
	
	/* (non-Javadoc)
     * @see dao.core.ModelDAO#create()
     */
	public Model create() throws DAOException;
		
	/* (non-Javadoc)
     * @see dao.core.ModelDAO#update(models.Model)
     */
	public void update(Model model) throws DAOException;
		
	/* (non-Javadoc)
     * @see dao.core.ModelDAO#getModelById(int)
     */
	public Model getModelById(int id) throws DAOException;
	
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