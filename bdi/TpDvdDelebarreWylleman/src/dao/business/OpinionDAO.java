package dao.business;

import dao.core.DAOException;

import models.Opinion;

import java.util.List;

public interface OpinionDAO {
	
	/**
	 * Cree et renvoir un nouvel objet metier de type <code>Opinion</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>Opinion</code> cree (jamais <code>null</code>).
	 */
	public Opinion create(int dvdID, int userId) throws DAOException;
		
	/**
	 * Supprime l'objet <code>Opinion</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param opinion Le l'objet <code>Opinion</code> a supprimer.
	 */
	public void delete(Opinion opinion) throws DAOException;
		
	/**
	 * Met a jour l'objet <code>Opinion</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param opinion Le l'objet <code>Opinion</code> a mettre a jour.
	 */
	public void update(Opinion opinion) throws DAOException;
		
	/**
	 * Renvoie l'objet metier <code>Opinion</code> associe au couple d'identifiants passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param dvdId La reference du dvd commente.
	 * @param userId La reference de l'utilisateur qui a commente le dvd.
	 * @return l'objet <code>Opinion</code> associe au couple d'identifiants.
	 */
	public Opinion getOpinionById(int dvdId, int userId) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de tous les avis de l'utilisateur specifie.
	 * La liste renvoyee est vide si aucun dvd n'a ete commente par cet utilsateur.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param dvdId L'identifiant du DVD commente.
	 * @param order Le champ par lequel trier la liste des avis.
	 * @return la liste des avis (jamais <code>null</code>).
	 */
	public List search(int dvdId, String order) throws DAOException;

}