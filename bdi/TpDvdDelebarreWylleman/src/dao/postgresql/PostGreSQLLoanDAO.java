package dao.postgresql;

import dao.business.LoanDAO;
import dao.core.DAOException;

import models.Loan;
import models.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PostGreSQLLoanDAO extends PostGreSQLModelDAO implements LoanDAO {
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#create(int, int, java.util.Date)
     */
	public Loan create(int dvdID, int userId, Date date) throws DAOException {
		String query = "INSERT INTO emprunts VALUES(" + dvdID + ", " + userId + ", '" + date + "', NULL, NULL, NULL, NULL);" ;
		int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
		if ( insertedRowNumber != 1 ) throw new DAOException("Nombre d'enregistrement inserer non valide, creation annulee.");
		
		return new Loan(dvdID, userId, date);
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#reserve(int, int)
     */
	/* public void reserve(int dvdID, int userId) throws DAOException {
		try {
			String query = "UPDATE emprunts SET loueur_suivant_id = " + userId +
			" WHERE dvds_id = " + dvdID + 
			" AND date_retour IS NULL;" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	} */
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#update(models.Loan)
     */
	public void update(Loan loan) throws DAOException {
		try {
			String query = "UPDATE emprunts SET" + 
			" prolonge = " + loan.getExtraTime() ;
			if ( loan.getEnd() != null ) query += ", date_retour = '" + loan.getEnd() + "'";
			if ( loan.getReserveUser() == 0 ) query += ", loueur_suivant_id = NULL" ;
			else query += ", loueur_suivant_id = " + loan.getReserveUser() ;
			query += " WHERE dvds_id = " + loan.getDVDId() + 
			" AND utilisateurs_id = " + loan.getUser() + 
			" AND date_emprunt = '" + loan.getBegin() + "';" ;
			int insertedRowNumber = PostGreSQLCommons.executeUpdate(query);
			if ( insertedRowNumber != 1 ) throw new SQLException("Nombre d'enregistrement mis a jour non valide, mise a jour annulee.");
			
		} catch (Exception e) {
			throw new DAOException(e.toString());
		}
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#getLoanById(int, int)
     */
	public Loan getLoanById(int dvdId, int userId) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2 WHERE true" ; 
		if ( dvdId != 0 ) query += " AND dvds_id = " + dvdId ;
		if ( userId != 0 ) query += " AND utilisateurs_id = " + userId ;
		query += ";" ;
		return (Loan) super.getModelById(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#search(int, String)
     */
	public List search(int userId, String order) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2" ;
		if ( userId != 0 ) query += " WHERE utilisateurs_id = " + userId ;
		if ( order != null ) query += " ORDER BY " + order ;
		query += ";" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#getLoans(models.User)
     */
	public List getLoans(models.User user) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2" ;
		if ( user != null ) query += " WHERE utilisateurs_id = " + user.getId() ;
		query += " ORDER BY date_emprunt ;" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#getReserves(models.User)
     */
	public List getReserves(models.User user) throws DAOException {
		String query = "SELECT * FROM vue_reserves2" ;
		if ( user != null ) query += " WHERE reserve_utilisateurs_id = " + user.getId() ;
		query += " ORDER BY date_emprunt ;" ;
		return this.executeSelect(query);
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#isBorrowable(models.DVD)
     */
	public boolean isBorrowable(models.DVD dvd) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2" ;
		if ( dvd != null ) query += " WHERE dvds_id = " + dvd.getId() ;
		query += ";" ;
		boolean isNotYetBorrowed = this.executeSelect(query).isEmpty();
		
		query = "SELECT * FROM vue_reserves2";
		if ( dvd != null ) query += " WHERE dvds_id = " + dvd.getId() ;
		query += ";" ;
		return this.executeSelect(query).isEmpty() & isNotYetBorrowed;
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#isReservable(models.DVD)
     */
	public boolean isReservable(models.DVD dvd) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2 WHERE reserve_utilisateurs_id IS NULL" ;
		if ( dvd != null ) query += " AND dvds_id = " + dvd.getId() ;
		query += ";" ;
		return ( this.executeSelect(query).size() == 1 );
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#canBorrow(models.User)
     */
	public boolean canBorrow(models.User user) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2" ;
		if ( user != null ) query += " WHERE utilisateurs_id = " + user.getId() ;
		query += ";" ;
		return ( this.executeSelect(query).size() < 3 );
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#canReserve(models.User)
     */
	public boolean canReserve(models.User user) throws DAOException {
		String query = "SELECT * FROM vue_emprunts2" ;
		if ( user != null ) query += " WHERE reserve_utilisateurs_id = " + user.getId() ;
		query += ";" ;
		return ( this.executeSelect(query).size() < 3 );
	}
	
	/* (non-Javadoc)
     * @see dao.business.LoanDAO#canReserve(models.DVD, models.User)
     */
	public boolean canReserve(models.DVD dvd, models.User user) throws DAOException {
		if ( user != null && dvd != null ) {
			Loan loan = this.getLoanById(dvd.getId(), user.getId());
			return ( loan == null );
		}
		return false;
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
				int userId = rs.getInt("utilisateurs_id");
				int dvdId = rs.getInt("dvds_id");
				String dvdTitle = rs.getString("dvds_titre");
				String begin = rs.getString("date_emprunt");
				String limit = rs.getString("date_limite");
				String end = rs.getString("date_retour");
				int extraTime = rs.getInt("prolonge");
				int reserverUser = rs.getInt("reserve_utilisateurs_id");
				
				Loan currentLoan = new Loan(dvdId, userId, java.sql.Date.valueOf(begin));
				currentLoan.setDVDTitle(dvdTitle);
				if ( limit != null ) currentLoan.setLimit(java.sql.Date.valueOf(limit));
				if ( end != null ) currentLoan.setEnd(java.sql.Date.valueOf(end));
				currentLoan.setExtraTime(extraTime);
				currentLoan.setReserveUser(reserverUser);
				
				result.add(currentLoan);
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
		int userId = rs.getInt("utilisateurs_id");
		int dvdId = rs.getInt("dvds_id");
		String dvdTitle = rs.getString("dvds_titre");
		String begin = rs.getString("date_emprunt");
		String limit = rs.getString("date_limite");
		String end = rs.getString("date_retour");
		int extraTime = rs.getInt("prolonge");
		int reserverUser = rs.getInt("reserve_utilisateurs_id");
		
		Loan loan = new Loan(dvdId, userId, java.sql.Date.valueOf(begin));
		loan.setDVDTitle(dvdTitle);
		if ( limit != null ) loan.setLimit(java.sql.Date.valueOf(limit));
		if ( end != null ) loan.setEnd(java.sql.Date.valueOf(end));
		loan.setExtraTime(extraTime);
		loan.setReserveUser(reserverUser);
		
		return loan;
	}
	
}