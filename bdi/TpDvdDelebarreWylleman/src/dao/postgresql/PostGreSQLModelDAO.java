package dao.postgresql;

import dao.business.KindDAO;
import dao.core.DAOException;

import models.Model;
import models.Kind;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe abstraite definit des methodes generiques
 * afin d'alleger chaque classe de DAO.
 */
public abstract class PostGreSQLModelDAO {
	
	/**
	 * L'historique des demande <code>getModelById</code>
	 */
	private java.util.Map history;
	
	public PostGreSQLModelDAO() {
		this.history = new java.util.HashMap();
	}
	
	/**
	 * Insert dans la table specifiee un nouvel enregistrement a partir d'un clef generee automatiquement
	 * et d'un nombre de champs <code>null</code> de la table.
	 * @return La clef generee.
     * @param tableName Le nom de la table dans laquelle creer un nouvel enregistrement.
	 * @param fieldsToNull Le nombre de champs de la table a mettre a <code>null</code> a la creation du nouvel enregistrement.
     */
	protected int create(String tableName, int fieldsToNull) throws DAOException {
		int key = PostGreSQLCommons.getGeneratedKey(tableName);
		
		// On insert un nouvel enregistrement vide avec la clef generee.
		String query = "INSERT INTO " + tableName + " VALUES(" + key ;
		for ( int i=0; i<fieldsToNull; i++ ) query += ", NULL" ;
		query += ");" ;
		int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
		if ( insertedRowNumber != 1 ) throw new DAOException("Nombre d'enregistrement inserer non valide, creation annulee.");
		
		return key;
	}
	
	/**
	 * Renvoie l'objet metier <code>Model</code> associe a l'identifiant passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * Note: Chaque demande est stockee avec son resultat correspondant (existant ou non)
	 * afin d'augmenter la rapidite en evitant plusieurs recherche similaires.
	 * @param tableName Le nom de la table dans laquelle rechercher le <code>Model</code>.
	 * @param id l'identifiant de l'objet a renvoyer.
	 * @return l'objet <code>Model</code> associe a l'identifiant.
	 */
	public Model getModelById(String tableName, int id) throws DAOException {
		Integer ObjectId = new Integer(id);
		if ( this.history.containsKey(ObjectId) ) return (Model) this.history.get(ObjectId);
		String query = "SELECT * FROM " + tableName + " WHERE id = " + id + ";" ;
		List results = this.executeSelect(query);
		
		try {
			Model result = null;
			if ( results != null ) result = (Model) results.get(0);
			this.history.put(ObjectId, result);
			return result;
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/**
	 * Cree et renvoie un liste de resultats a partir de l'execution de la requete SQL passee en parametre.
	 * @param query La requete SQL a executer.
	 * @result La liste de <code>Model</code> creee a partir de l'execution de la requete.
	 */
	protected abstract List<Model> executeSelect(String query) throws DAOException;
	
}