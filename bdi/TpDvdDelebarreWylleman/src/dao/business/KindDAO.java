package dao.business;

import dao.core.DAOException;
import dao.core.ModelDAO;

import models.Model;

import java.util.List;

public interface KindDAO extends ModelDAO {
	
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
	 * Cherche et renvoie le liste de toutes les categories satisfaisants les criteres passes en parametre.
	 * La liste renvoyee est vide si aucun enregistrement ne correspond aux criteres de selection specifies.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param name Le nom de la categorie a chercher.
	 * @return la liste des categories (jamais <code>null</code>).
	 */
	public List search(String name) throws DAOException;
	
}