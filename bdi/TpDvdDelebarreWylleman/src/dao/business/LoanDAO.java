package dao.business;

import dao.core.DAOException;

import models.Loan;

import java.util.Date;
import java.util.List;

public interface LoanDAO {
	
	/**
	 * Cree et renvoie un nouvel objet metier de type <code>Loan</code>.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @return l'objet <code>Loan</code> cree (jamais <code>null</code>).
	 */
	public Loan create(int dvdID, int userId, Date date) throws DAOException;
		
	/**
	 * Reserve le dvd pour l'utilisateur specifies par leur identifiant.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 */
	//public void reserve(int dvdID, int userId) throws DAOException;
		
	/**
	 * Met a jour l'objet <code>Loan</code> passe en parametre.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param loan Le l'objet <code>Loan</code> a mettre a jour.
	 */
	public void update(Loan loan) throws DAOException;
		
	/**
	 * Renvoie l'objet metier <code>Loan</code> associe au couple d'identifiants passe en parametre.
	 * Renvoie <code>null</code> si l'identifiant n'existe pas.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param dvdId La reference du dvd emprunte.
	 * @param userId La reference de l'utilisateur qui a emprunte le dvd.
	 * @return l'objet <code>Loan</code> associe au couple d'identifiants.
	 */
	public Loan getLoanById(int dvdId, int userId) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de tous les emprunts de l'utilisateur specifie.
	 * La liste renvoyee est vide si aucun dvd n'a ete emprunte par cet utilsateur.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param userId L'identifiant de l'utilisateur emprunter.
	 * @param order Le champ par lequel trier la liste des emprunts.
	 * @return la liste des emprunts (jamais <code>null</code>).
	 */
	public List search(int userId, String order) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de tous les emprunts de l'utilisateur specifie.
	 * La liste renvoyee est vide si aucun dvd n'a ete emprunte par cet utilsateur.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param user L'utilisateur.
	 * @return la liste des emprunts (jamais <code>null</code>).
	 */
	public List getLoans(models.User user) throws DAOException;
	
	/**
	 * Cherche et renvoie le liste de toutes les reservations de l'utilisateur specifie.
	 * La liste renvoyee est vide si aucun dvd n'a ete reserve par cet utilsateur.
	 * Lance une exception de type <code>DAOException</code> si une erreur survient.
	 * @param user L'utilisateur.
	 * @return la liste des reservations (jamais <code>null</code>).
	 */
	public List getReserves(models.User user) throws DAOException;
	
	/**
	 * Renvoie <code>True</code> si le DVD passe en parametre est actuellement disponible a l'emprunt.
	 * Renvoie <code>False</code> dans tous les autres cas.
	 * @param dvd Le DVD dont on veut savoir s'il est actuellement disponible a l'emprunt.
	 * @return <code>True</code> si le DVD passe en parametre est actuellement disponible a l'emprunt, <code>False</code> sinon.
	 */
	public boolean isBorrowable(models.DVD dvd) throws DAOException;
	
	/**
	 * Renvoie <code>True</code> si le DVD passe en parametre est actuellement disponible a le reserve.
	 * Renvoie <code>False</code> dans tous les autres cas.
	 * @param dvd Le DVD dont on veut savoir s'il est actuellement disponible a le reserve.
	 * @return <code>True</code> si le DVD passe en parametre est actuellement disponible a le reserve, <code>False</code> sinon.
	 */
	public boolean isReservable(models.DVD dvd) throws DAOException;
		
	/**
	 * Renvoie <code>True</code> si l'utilisateur passe en parametre peut emprunter des DVDs.
	 * Renvoie <code>False</code> dans tous les autres cas.
	 * @param user L'utilisateur dont on veut savoir s'il peut emprunter des DVDs.
	 * @return <code>True</code> si l'utilisateur passe en parametre peut emprunter des DVDs, <code>False</code> sinon.
	 */
	public boolean canBorrow(models.User user) throws DAOException;
	
	/**
	 * Renvoie <code>True</code> si l'utilisateur passe en parametre peut reserver des DVDs.
	 * Renvoie <code>False</code> dans tous les autres cas.
	 * @param user L'utilisateur dont on veut savoir s'il peut reserver des DVDs.
	 * @return <code>True</code> si l'utilisateur passe en parametre peut reserver des DVDs, <code>False</code> sinon.
	 */
	public boolean canReserve(models.User user) throws DAOException;
	
	/**
	 * Renvoie <code>True</code> si l'utilisateur specifie peut reserver le DVD passe en parametre.
	 * Renvoie <code>False</code> dans tous les autres cas.
	 * @param dvd Le DVD que l'utilisateur specifie souhaite reserver.
	 * @param user L'utilisateur dont on veut savoir s'il peut reserver le DVD.
	 * @return <code>True</code> si l'utilisateur specifie peut reserver le DVD passe en parametre, <code>False</code> sinon.
	 */
	public boolean canReserve(models.DVD dvd, models.User user) throws DAOException;
	
}