package dao.postgresql;

import dao.business.OpinionDAO;
import dao.core.DAOException;

import models.Model;
import models.Opinion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PostGreSQLOpinionDAO extends PostGreSQLModelDAO implements OpinionDAO {
	
	/* (non-Javadoc)
     * @see dao.business.OpinionDAO#create(int, int)
     */
	public Opinion create(int dvdID, int userId) throws DAOException {
		String query = "INSERT INTO avis VALUES(" + dvdID + ", " + userId + ", NULL);" ;
		int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
		if ( insertedRowNumber != 1 ) throw new DAOException("Nombre d'enregistrement inserer non valide, creation annulee.");
		
		return new Opinion(dvdID, userId);
	}
	
	/* (non-Javadoc)
     * @see dao.business.OpinionDAO#delete(models.Opinion)
     */
	public void delete(Opinion opinion) throws DAOException {
		try {
			if ( opinion == null )  throw new SQLException("Suppression impossible, l'avis n'existe pas.");
			String query = "DELETE FROM avis WHERE dvds_id = " + opinion.getDVDId() + " AND utilisateurs_id = " + opinion.getAuthorId() + ";" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement supprime non valide, suppression annulee.");
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.OpinionDAO#update(models.Opinion)
     */
	public void update(Opinion opinion) throws DAOException {
		try {
			String query = "UPDATE avis SET" ;
			if ( opinion.getText() != null ) query += " avis = '" + opinion.getText() + "'" ;
			query += " WHERE dvds_id = " + opinion.getDVDId() + 
			" AND utilisateurs_id = " + opinion.getAuthorId() + ";" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
			
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.OpinionDAO#getOpinionById(int, int)
     */
	public Opinion getOpinionById(int dvdId, int userId) throws DAOException {
		String query = "SELECT * FROM avis WHERE TRUE" ; 
		if ( dvdId != 0 ) query += " AND dvds_id = " + dvdId ;
		if ( userId != 0 ) query += " AND utilisateurs_id = " + userId ;
		query += ";" ;
		return (Opinion) super.getModelById(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.OpinionDAO#search(int, String)
     */
	public List search(int dvdId, String order) throws DAOException {
		String query = "SELECT * FROM avis WHERE TRUE" ;
		if ( dvdId != 0 ) query += " AND dvds_id = " + dvdId ;
		if ( order != null ) query += " ORDER BY " + order ;
		query += ";" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.postgresql.PostGreSQLModelDAO#getModelByResultSet(java.sql.ResultSet)
     */
	protected Model getModelByResultSet(ResultSet rs) throws SQLException {
		int userId = rs.getInt("utilisateurs_id");
		int dvdId = rs.getInt("dvds_id");
		String text = rs.getString("avis");
		
		Opinion opinion = new Opinion(dvdId, userId);
		opinion.setText(text);
		
		return opinion;
	}
	
}