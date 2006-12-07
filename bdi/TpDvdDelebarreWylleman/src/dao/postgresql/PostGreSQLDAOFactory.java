package dao.postgresql;

import dao.core.DAOFactory;

public class PostGreSQLDAOFactory extends DAOFactory {
	
	public PostGreSQLDAOFactory() {
		super();
		super.putDAO("kind", new PostGreSQLDAOKind());
		super.putDAO("user", new PostGreSQLDAOUser());
		super.putDAO("dvd", new PostGreSQLDVDDAO());
	}
	
}