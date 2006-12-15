package dao.core;

import dao.business.*;

/**
 * La fabrique de DAO.
 */
public abstract class DAOFactory {

	public abstract DVDDAO getDVDDAO();
	public abstract KindDAO getKindDAO();
	public abstract UserDAO getUserDAO();
	public abstract LoanDAO getLoanDAO();
	public abstract OpinionDAO getOpinionDAO();
	
}