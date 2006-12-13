package dao.postgresql;

import dao.business.UserDAO;
import dao.core.DAOException;

import models.User;
import models.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class PostGreSQLUserDAO extends PostGreSQLModelDAO implements UserDAO {
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#create()
     */
	public User create() throws DAOException {
		return new User(super.create("utilisateurs", 4));
	}
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#update(models.User)
     */
	public void update(User user) throws DAOException {
		try {
			String query = "UPDATE utilisateurs" + 
			" SET nom = '" + user.getLastName() + "'" + 
			" SET prenom = '" + user.getFirstName() + "'" + 
			" SET login = '" + user.getLogin() + "'" + 
			" SET pass = '" + user.getPassword() + "'" + 
			" WHERE id = " + user.getId() + ";" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
			
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#getUserById(int)
     */
	public User getUserById(int id) throws DAOException {
		String query = "SELECT * FROM utilisateurs WHERE id = " + id + ";" ;
		return (User) super.getModelById(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#search(String, String, String)
     */
	public List search(String lastName, String firstName, String login) throws DAOException {
		String query = "SELECT * FROM utilisateurs WHERE TRUE" ;
		if ( lastName != null ) query += " AND nom ~~ '%" + lastName + "%'" ;
		if ( firstName != null ) query += " AND prenom ~~ '%" + firstName + "%'" ;
		if ( login != null ) query += " AND login ~~ '%" + login + "%'" ;
		query += ";" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#getUserByLogin(String)
     */
	public User getUserByLogin(String login) throws DAOException {
		String query = "SELECT * FROM utilisateurs WHERE login = '" + login + "';" ;
		List users = this.executeSelect(query);
		try {
			if ( users.isEmpty() ) return null;
			return (User) users.get(0);
		} catch (Exception e) {
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
				User currentUser = new User(rs.getInt("id"));
				currentUser.setLastName(rs.getString("nom"));
				currentUser.setFirstName(rs.getString("prenom"));
				currentUser.setLogin(rs.getString("login"));
				currentUser.setPassword(rs.getString("password"));
				result.add(currentUser);
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
		User user = new User(rs.getInt("id"));
		user.setLastName(rs.getString("nom"));
		user.setFirstName(rs.getString("prenom"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		return user;
	}
	
}