package dao.postgresql;

import dao.business.ArtistDAO;
import dao.core.DAOException;

import models.Artist;
import models.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class PostGreSQLArtistDAO extends PostGreSQLModelDAO implements ArtistDAO {
	
	/* (non-Javadoc)
     * @see dao.business.ArtistDAO#create()
     */
	public Artist create() throws DAOException {
		return new Artist(super.create("artistes", 2));
	}
	
	/* (non-Javadoc)
     * @see dao.business.ArtistDAO#update(models.Artist)
     */
	public void update(Artist artist) throws DAOException {
		try {
			String query = "UPDATE artistes SET" + 
			" nom = '" + artist.getName() + "'" + 
			", fonction = '" + artist.getFunction() + "'" + 
			" WHERE id = " + artist.getId() + ";" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
			
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.ArtistDAO#getArtistById(int)
     */
	public Artist getArtistById(int id) throws DAOException {
		String query = "SELECT * FROM artistes WHERE id = " + id + ";" ;
		return (Artist) super.getModelById(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.ArtistDAO#search(String, String)
     */
	public List search(String name, String order) throws DAOException {
		String query = "SELECT * FROM artistes WHERE TRUE" ;
		if ( name != null ) query += " AND nom ~~ '%" + name + "%'" ;
		if ( order != null ) query += " ORDER BY " + order ;
		query += ";" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.postgresql.ArtistDAO#getDVDs(models.Artist)
     */
	public List<models.DVD> getDVDs(Artist artist) throws DAOException {
		String query = "SELECT dvds_id FROM dvds_has_artistes" ;
		if ( artist != null ) query += " WHERE artistes_id = " + artist.getId() ;
		query += ";" ;
		
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		dao.business.DVDDAO dvdDAO = new PostGreSQLDVDDAO();
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				int dvdId = rs.getInt("dvds_id");
				models.DVD currentDVD = dvdDAO.getDVDById(dvdId);
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
	
	/* (non-Javadoc)
     * @see dao.postgresql.PostGreSQLModelDAO#executeSelect(String)
     */
	/* protected List executeSelect(String query) throws DAOException {
		List result = new ArrayList();
		
		Statement st = PostGreSQLCommons.executeQuery(query);
		
		try {
			ResultSet rs = st.getResultSet();
			while ( rs.next() ) {
				Artist currentArtist = new Artist(rs.getInt("id"));
				currentArtist.setName(rs.getString("nom"));
				currentArtist.setFunction(rs.getString("fonction"));
				result.add(currentArtist);
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
		Artist artist = new Artist(rs.getInt("id"));
		artist.setName(rs.getString("nom"));
		artist.setFunction(rs.getString("fonction"));
		return artist;
	}	
}