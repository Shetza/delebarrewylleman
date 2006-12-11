package dao.postgresql;

import dao.core.DAOFactory;
import dao.business.*;

public class PostGreSQLDAOFactory extends DAOFactory {
	
	/* public PostGreSQLDAOFactory() {
		super();
		super.putDAO("kind", new PostGreSQLDAOKind());
		super.putDAO("user", new PostGreSQLDAOUser());
		super.putDAO("dvd", new PostGreSQLDVDDAO());
	} */
	
	public DVDDAO getDVDDAO() {
		return new PostGreSQLDVDDAO();
	}
	
	public KindDAO getKindDAO() {
		return new PostGreSQLDAOKind();
	}
	
	public UserDAO getUserDAO() {
		return new PostGreSQLDAOUser();
	}
	
}