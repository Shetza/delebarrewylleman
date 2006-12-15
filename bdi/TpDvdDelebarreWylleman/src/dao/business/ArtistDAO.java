package dao.business;

import dao.core.DAOException;

import models.Artist;

import java.util.Date;
import java.util.List;

public interface ArtistDAO {
	
	/**
	 * Cree et renvoir un nouvel objet metier de type <code>Artist</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>Artist</code> cree (jamais <code>null</code>).
	 */
	public Artist create() throws DAOException;
		
	/**
	 * Met a jour l'objet <code>Artist</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param artist Le l'objet <code>Artist</code> a mettre a jour.
	 */
	public void update(Artist artist) throws DAOException;
		
	/**
	 * Renvoie l'objet metier <code>Artist</code> associe au triplet d'identifiants passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param id La reference de l'artiste.
	 * @return l'objet <code>Artist</code> associe au triplet d'identifiants.
	 */
	public Artist getArtistById(int id) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de tous les artistes ayant un nom comme celui specifie.
	 * La liste renvoyee est vide si aucun artiste n'a ete trouve par cet recherche.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param name Le nom de l'artiste recherche.
	 * @param order Le champ par lequel trier la liste des artistes.
	 * @return la liste des artistes (jamais <code>null</code>).
	 */
	public List<Artist> search(String name, String order) throws DAOException;
	
	/**
	 * Renvoie la liste des DVDs dans lesquels l'artiste passe en parametre apparait.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param artist L'artiste a rechercher dans la base de DVDs.
	 * @return la liste des DVDs (jamais <code>null</code>).
	 */
	public List<models.DVD> getDVDs(Artist artist) throws DAOException;
}