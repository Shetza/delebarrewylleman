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

public class PostGreSQLDAOKind implements KindDAO {
	
	/* (non-Javadoc)
     * @see dao.business.KindDAO#create()
     */
	public Model create() throws DAOException {
		int key = PostGreSQLCommons.getGeneratedKey("categories");
		
		// On insert un nouvel enregistrement vide avec la clef generee.
		String query = "INSERT INTO categories VALUES(" + key + ", NULL);" ;
		int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
		if ( insertedRowNumber != 1 ) throw new DAOException("Nombre d'enregistrement inserer non valide, creation annulee.");
		
		return new Kind(key);
	}
	
	/* (non-Javadoc)
     * @see dao.business.KindDAO#update(models.Model)
     */
	public void update(Model model) throws DAOException {
		try {
			Kind kind = (Kind) model;
			String query = "UPDATE categories" + 
			" SET nom = '" + kind.getName() + "'" + 
			" WHERE id = " + kind.getId() + ";" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
			
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.KindDAO#getModelById(int)
     */
	public Model getModelById(int id) throws DAOException {
		String query = "SELECT * FROM categories WHERE id = " + id + ";" ;
		List kinds = this.executeSelect(query);
		
		try {
			if ( kinds != null ) return (Kind) kinds.get(0);
			return null;
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.KindDAO#search(String)
     */
	public List search(String name) throws DAOException {
		String query = "SELECT * FROM categories";
		if (name != null) query += " WHERE nom ~~ '%" + name + "%'";
		query += ";";
		return this.executeSelect(query);
	}
	
	private List executeSelect(String query) throws DAOException {
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				Kind currentKind = new Kind(rs.getInt("id"));
				currentKind.setName(rs.getString("nom"));
				result.add(currentKind);
			}
			rs.close();
			PostGreSQLCommons.close(st);
			
			return result;
		} catch (SQLException e) {
			PostGreSQLCommons.close(st);
			throw new DAOException(e.toString());
		}
	}
	
}