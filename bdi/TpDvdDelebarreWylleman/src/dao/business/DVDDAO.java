package dao.business;

import dao.core.DAOException;

import models.DVD;

import java.util.List;

public interface DVDDAO {
	
	/**
	 * Cree et renvoir un nouvel objet metier de type <code>DVD</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>DVD</code> cree (jamais <code>null</code>).
	 */
	public DVD create() throws DAOException;
		
	/**
	 * Met a jour l'objet <code>DVD</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param dvd Le l'objet <code>DVD</code> a mettre a jour.
	 */
	public void update(DVD dvd) throws DAOException;
		
	/**
	 * Renvoie l'objet metier <code>DVD</code> associe a l'identifiant passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param id l'identifiant de l'objet a renvoyer.
	 * @return l'objet <code>DVD</code> associe a l'identifiant.
	 */
	public DVD getDVDById(int id) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de tous les dvds satisfaisants les criteres passes en parametre.
	 * La liste renvoyee est vide si aucun enregistrement ne correspond aux criteres de selection specifies.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param owner L'identifiant du proprietaire des dvds a chercher.
	 * @param title Le titre des dvds a chercher.
	 * @param kind L'identifiant de la categorie des dvds a chercher.
	 * @param date La date des dvds a chercher.
	 * @param order L'ordre de trie dans la liste.
	 * @return la liste des dvds (jamais <code>null</code>).
	 */
	public List search(int owner, String title, int kind, String date, String order) throws DAOException;
		
	/**
	 * Renvoie la liste des artistes qui ont participe au DVD passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param dvd Le DVD a rechercher dans la base de DVDs.
	 * @return la liste des Artistes (jamais <code>null</code>).
	 */
	public List getArtists(DVD dvd) throws DAOException;
	
}