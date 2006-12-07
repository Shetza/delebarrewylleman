package dao.core;

import models.Model;

import java.util.List;

public interface ModelDAO {
	
	/**
	 * Cree et renvoir un nouvel objet metier de type <code>Model</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>Model</code> cree (jamais <code>null</code>).
	 */
	public Model create() throws DAOException;
		
	/**
	 * Met a jour l'objet <code>Model</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param model Le l'objet <code>Model</code> a mettre a jour.
	 */
	public void update(Model model) throws DAOException;
		
	//public List selectNumeric(Connection conn, String field, String value) throws SQLException;
	//public List selectAlphabetic(Connection conn, String field, String value) throws SQLException;
	
	/**
	 * Renvoie l'objet metier <code>Model</code> associe a l'identifiant passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param id l'identifiant de l'objet a renvoyer.
	 * @return l'objet <code>Model</code> associe a l'identifiant.
	 */
	public Model getModelById(int id) throws DAOException;
}