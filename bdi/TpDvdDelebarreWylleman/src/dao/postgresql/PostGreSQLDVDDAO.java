package dao.postgresql;

import dao.business.DVDDAO;
import dao.core.DAOException;

import models.DVD;
import models.Kind;
import models.User;
import models.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class PostGreSQLDVDDAO extends PostGreSQLModelDAO implements DVDDAO {
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#create()
     */
	public DVD create() throws DAOException {
		return new DVD(super.create("dvds", 4));
	}
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#update(models.DVD)
     */
	public void update(DVD dvd) throws DAOException {
		try {
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
     * @see dao.business.DVDDAO#getDVDById(int)
     */
	public DVD getDVDById(int id) throws DAOException {
		String query = "SELECT * FROM dvds WHERE id = " + id + ";" ;
		return (DVD) super.getModelById(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.DVDDAO#search(int, String, int, String, String)
     */
	public List search(int owner, String title, int kind, String date, String order) throws DAOException {
		String query = "SELECT * FROM dvds WHERE TRUE" ;
		if ( owner != 0 ) query += " AND utilisateurs_id = " + owner ;
		if ( title != null ) query += " AND titre ~~ '%" + title + "%'" ;
		if ( kind != 0 ) query += " AND categories_id = " + kind ;
		if ( date != null ) query += " AND parution ~~ '%" + date + "%'" ;
		if ( order != null ) query += " ORDER BY " + order ;
		query += ";" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.postgresql.DVDDAO#getArtists(models.DVD)
     */
	public List getArtists(DVD dvd) throws DAOException {
		String query = "SELECT artistes_id FROM dvds_has_artistes" ;
		if ( dvd != null ) query += " WHERE dvds_id = " + dvd.getId() ;
		query += ";" ;
		
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		dao.business.ArtistDAO artistDAO = new PostGreSQLArtistDAO();
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				int artistId = rs.getInt("artistes_id");
				models.Artist currentArtist = artistDAO.getArtistById(artistId);
				result.add(currentArtist);
			}
			rs.close();
			PostGreSQLCommons.close(st);
			
			return result;
		} catch (SQLException e) {
			PostGreSQLCommons.close(st);
			throw new DAOException(e.toString());
		}
		
	}
	
	/* (non-Javadoc)
     * @see dao.postgresql.PostGreSQLModelDAO#executeSelect(String)
     */
	/* protected List executeSelect(String query) throws DAOException {
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				DVD currentDVD = new DVD(rs.getInt("id"));
				currentDVD.setKind(Integer.parseInt(rs.getString("categories_id")));
				currentDVD.setUser(Integer.parseInt(rs.getString("utilisateurs_id")));
				currentDVD.setTitle(rs.getString("titre"));
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
	} */
	
	/* (non-Javadoc)
     * @see dao.postgresql.PostGreSQLModelDAO#getModelByResultSet(java.sql.ResultSet)
     */
	protected Model getModelByResultSet(ResultSet rs) throws SQLException {
		DVD dvd = new DVD(rs.getInt("id"));
		dvd.setKind(Integer.parseInt(rs.getString("categories_id")));
		dvd.setUser(Integer.parseInt(rs.getString("utilisateurs_id")));
		dvd.setTitle(rs.getString("titre"));
		dvd.setDate(Date.valueOf(rs.getString("parution")));
		return dvd;
	}
	
}