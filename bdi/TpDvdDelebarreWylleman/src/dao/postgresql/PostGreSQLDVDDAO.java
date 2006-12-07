package dao.postgresql;

import dao.business.DVDDAO;
import dao.core.DAOException;

import models.Model;
import models.DVD;
import models.Kind;
import models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class PostGreSQLDVDDAO implements DVDDAO {
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#create()
     */
	public Model create() throws DAOException {
		int key = PostGreSQLCommons.getGeneratedKey("dvds");
		
		// On insert un nouvel enregistrement vide avec la clef generee.
		String query = "INSERT INTO dvds VALUES(" + key + ", NULL, NULL, NULL, NULL);" ;
		int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
		if ( insertedRowNumber != 1 ) throw new DAOException("Nombre d'enregistrement inserer non valide, creation annulee.");
		
		return new DVD(key);
	}
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#update(models.Model)
     */
	public void update(Model model) throws DAOException {
		try {
			DVD dvd = (DVD) model;
			String query = "UPDATE dvds SET" + 
			" categories_id = " + dvd.getKind() + 
			", utilisateurs_id = " + dvd.getUser() + 
			", titre = '" + dvd.getTitle() + "'" + 
			", parution = '" + dvd.getDate() + "'" + 
			" WHERE id = " + dvd.getId() + ";" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
			
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#getModelById(int)
     */
	public Model getModelById(int id) throws DAOException {
		String query = "SELECT * FROM dvds WHERE id = " + id + ";" ;
		List dvds = this.executeSelect(query);
		
		try {
			if ( dvds != null ) return (DVD) dvds.get(0);
			return null;
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#search(String, int, String)
     */
	public List search(String title, int kind, String date) throws DAOException {
		String query = "SELECT * FROM dvds WHERE TRUE" ;
		if ( title != null ) query += " AND titre ~~ '%" + title + "%'" ;
		if ( kind != -1 ) query += " AND categories_id = " + kind ;
		if ( date != null ) query += " AND parution ~~ '%" + date + "%'" ;
		query += ";" ;
		return this.executeSelect(query);
	}
	
	private List executeSelect(String query) throws DAOException {
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				DVD currentDVD = new DVD(rs.getInt("id"));
				currentDVD.setKind(Integer.parseInt(rs.getString("categories_id")));
				currentDVD.setUser(Integer.parseInt(rs.getString("utilisateurs_id")));
				currentDVD.setTitle(rs.getString("title"));
				currentDVD.setDate(Date.valueOf(rs.getString("parution")));
				result.add(currentDVD);
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