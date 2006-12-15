package dao.postgresql;

import dao.core.DAOFactory;
import dao.business.*;

/**
 * La fabrique de DAO specialisee pour le SGBD PostgreSQL.
  */
public class PostGreSQLDAOFactory extends DAOFactory {
	
	public DVDDAO getDVDDAO() {
		return new PostGreSQLDVDDAO();
	}
	
	public KindDAO getKindDAO() {
		return new PostGreSQLKindDAO();
	}
	
	public UserDAO getUserDAO() {
		return new PostGreSQLUserDAO();
	}
	
	public LoanDAO getLoanDAO() {
		return new PostGreSQLLoanDAO();
	}
	
	public OpinionDAO getOpinionDAO() {
		return new PostGreSQLOpinionDAO();
	}
	
}