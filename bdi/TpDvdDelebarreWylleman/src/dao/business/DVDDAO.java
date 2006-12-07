package dao.business;

import dao.core.DAOException;
import dao.core.ModelDAO;

import models.Model;

import java.util.List;

public interface DVDDAO extends ModelDAO {
	
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
	 * Cherche et renvoie le liste de tous les dvds satisfaisants les criteres passes en parametre.
	 * La liste renvoyee est vide si aucun enregistrement ne correspond aux criteres de selection specifies.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param title Le titre des dvds a chercher.
	 * @param kind L'identifiant de la categorie des dvds a chercher.
	 * @param date La date des dvds a chercher.
	 * @return la liste des dvds (jamais <code>null</code>).
	 */
	public List search(String title, int kind, String date) throws DAOException;
	
}