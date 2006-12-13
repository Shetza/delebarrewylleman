package dao.postgresql;

import dao.core.DAOException;

import models.Model;

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
	 * Renvoie l'objet metier <code>Model</code> associe a la requete passee en parametre.
	 * Renvoie <code>null</code> si la requete n'a generee aucun objet.
	 * Lance une exception de type <code>DAOException</code> si plusieurs objets sont recuperes par la requete ou si une erreur survient.
	 * Note: Chaque demande est stockee avec son resultat correspondant (existant ou non)
	 * afin d'augmenter la rapidite en evitant plusieurs recherche similaires.
	 * @param query La requete a executer pour rechercher le <code>Model</code>.
	 * @return l'objet <code>Model</code> associe a l'identifiant ou <code>null</code>.
	 */
	public Model getModelById(String query) throws DAOException {
		if ( this.history.containsKey(query) ) return (Model) this.history.get(query);
		List results = this.executeSelect(query);
		
		if ( results.size() > 1 ) throw new DAOException("Nombre d'enregistrement non valide, recuperation annulee.");
		
		Model result = null;
		if ( ! results.isEmpty() ) result = (Model) results.get(0);
		this.history.put(query, result);
		return result;
	}
	
	/**
	 * Cree et renvoie une liste d'objet metier <code>Model</code> a partir de l'execution de la requete SQL passee en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param query La requete SQL a executer.
	 * @result La liste de <code>Model</code> creee a partir de l'execution de la requete (jamais <code>null</code>).
	 */
	protected List executeSelect(String query) throws DAOException {
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				result.add(this.getModelByResultSet(rs));
			}
			rs.close();
			PostGreSQLCommons.close(st);
			
			return result;
		} catch (SQLException e) {
			PostGreSQLCommons.close(st);
			throw new DAOException(e.toString());
		}
	}
	
	/**
	 * Renvoie un objet metier <code>Model</code> a partir du <code>ResultSet</code> passe en parametre.
	 * Lance une exception de type <code>SQLException</code> si une erreur survient.
	 * @param rs Le <code>ResultSet</code> duquel extraire l'objet <code>Model</code> a renvoyer.
	 * @result L'objet <code>Model</code> creee a partir du <code>ResultSet</code> (jamais <code>null</code>).
	 */
	protected abstract Model getModelByResultSet(ResultSet rs) throws SQLException ;
	
}