package dao.postgresql;

import dao.business.UserDAO;
import dao.core.DAOException;

import models.Model;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class PostGreSQLDAOUser extends PostGreSQLModelDAO implements UserDAO {
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#create()
     */
	public Model create() throws DAOException {
		return new User(super.create("utilisateurs", 4));
	}
	
	/* (non-Javadoc)
     * @see dao.business.UserDAO#update(models.Model)
     */
	public void update(Model model) throws DAOException {
		try {
			User user = (User) model;
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
     * @see dao.business.UserDAO#getModelById(int)
     */
	public Model getModelById(int id) throws DAOException {
		return super.getModelById("utilisateurs", id);
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
			if ( users != null ) return (User) users.get(0);
			return null;
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.postgresql.PostGreSQLModelDAO#executeSelect(String)
     */
	protected List executeSelect(String query) throws DAOException {
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
	}
	
}