package dao.postgresql;

import dao.core.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

public class PostGreSQLCommons {
	
	/**
	 * Le pool de connexion.
	 */
	private static DataSource _dataSource;
	
	/**
	 * Recupere le pool de connexion cree par Tomcat.
	 */
	static {
		try {
			Context initContext = new InitialContext();
			if(initContext == null ) throw new Exception("Boom 1 - No Context");
			
			Context envContext  = (Context) initContext.lookup("java:/comp/env");
			if(envContext == null ) throw new Exception("Boom 2 - No Context");
			
			_dataSource = (DataSource) envContext.lookup("jdbc/dvdtheque");
			
			if (_dataSource == null) throw new Exception("Boom 3 - No DataSource");
		} catch (Exception e) {
			System.out.println("ERROR: "+e);
		}
	}
	
	/**
	 * Recupere et renvoie une nouvelle connection du pool.
	 */
	public static Connection getConnection() throws DAOException {
		try {
			return _dataSource.getConnection();
		} catch (SQLException e) {
			throw new DAOException(e.toString());
		}
	}
	
	/**
	 * Ne pas oublier de fermer le Statement retourne ainsi que la connexion associee.
	 * La methode close(Statement) effectue ces traitements.
	 * Le Statement n'est pas ferme volontairement afin 
	 * que la methode appelant celle-ci puisse recuperer
	 * le ResultSet genere par la methode executeQuery.
	 */
	public static Statement executeQuery(String query) throws DAOException {
		Connection conn = PostGreSQLCommons.getConnection();
		Statement st = null;
		try {
			st = conn.createStatement();
			st.executeQuery(query);
			//System.out.println("PostGreSQLCommons: "+query);
			return st;
		} catch (SQLException e) {
			// Dans tous les cas si un probleme survient, 
			// On annule les modifications sur la base
			// et on transmet l'erreur.
			PostGreSQLCommons.close(st);
			throw new DAOException(e.toString());
		}
	}
	
	/**
	 * Ne pas oublier de fermer le Statement retourne.
	 * Le Statement n'est pas ferme volontairement afin 
	 * que la methode appelant celle-ci puisse recuperer
	 * le ResultSet genere par la methode executeQuery.
	 */
	public static int executeUpdate(String query) throws DAOException {
		Connection conn = PostGreSQLCommons.getConnection();
		Statement st = null;
		try {
			st = conn.createStatement();
			int result = st.executeUpdate(query);
			conn.commit();
			PostGreSQLCommons.close(st);
			//System.out.println("PostGreSQLCommons: "+query);
			return result;
		} catch (SQLException e) {
			// Dans tous les cas si un probleme survient, 
			// On annule les modifications sur la base
			// et on transmet l'erreur.
			PostGreSQLCommons.close(st);
			throw new DAOException(e.toString());
		}
	}
	
	/**
	 * Calcule et renvoie la clef suivante a utiliser pour l'insertion d'un nouvel enregistrement dans la table specifie.
	 */
	public static int getGeneratedKey(String table) throws DAOException {
		String query = "SELECT COUNT(*) as key FROM " + table + ";" ;
		Statement st = PostGreSQLCommons.executeQuery(query);
		try {	
			// On recupere le nombre d'enregistrement de la table 
			// afin d'en deduire la clef auto increment.
			ResultSet rs = st.getResultSet();
			if ( !rs.next() ) throw new SQLException("Impossible de calculer une nouvelle clef, creation annulee.");
			int key = rs.getInt("key") +1;
			rs.close();
			PostGreSQLCommons.close(st);
			
			return key;
			
		} catch (SQLException e) {
			PostGreSQLCommons.close(st);
			throw new DAOException(e.toString());
		}
	}
	
	/**
	 * Ferme la connection et le statement passee en parametre.
	 */
	public static void close(Statement st) {
		try {
			Connection conn = st.getConnection();
			st.close();
			conn.close();
		} catch (SQLException e) {}
	}
	
}