package dao.business;

import dao.core.DAOException;

import models.Kind;

import java.util.List;

public interface KindDAO {
	
	/**
	 * Cree et renvoir un nouvel objet metier de type <code>Kind</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>Kind</code> cree (jamais <code>null</code>).
	 */
	public Kind create() throws DAOException;
		
	/**
	 * Met a jour l'objet <code>Model</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param kind Le l'objet <code>Kind</code> a mettre a jour.
	 */
	public void update(Kind kind) throws DAOException;
		
	/**
	 * Renvoie l'objet metier <code>Kind</code> associe a l'identifiant passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param id l'identifiant de l'objet a renvoyer.
	 * @return l'objet <code>Kind</code> associe a l'identifiant.
	 */
	public Kind getKindById(int id) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de toutes les categories satisfaisants les criteres passes en parametre.
	 * La liste renvoyee est vide si aucun enregistrement ne correspond aux criteres de selection specifies.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param name Le nom de la categorie a chercher.
	 * @return la liste des categories (jamais <code>null</code>).
	 */
	public List search(String name) throws DAOException;
	
}