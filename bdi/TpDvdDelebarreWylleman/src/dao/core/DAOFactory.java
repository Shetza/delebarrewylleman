package dao.core;

//import java.net.URL;

import dao.business.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class DAOFactory {
	
	//private static DAOFactory _instance;
	
	/* static {
		Properties prop = new Properties();
		URL url = ClassLoader.getSystemResource("../../../../../conf/DAOFactory.conf");
		System.out.println("URL: " + url);
		String factoryName = null;
		try {
			prop.load(url.openStream());
			factoryName = prop.getProperty("DAOFactory");
		}
		catch (Exception e) {
			System.err.println("Impossible d'acceder au fichier 'conf/DAOFactory.conf', erreur: "+e);
		}
		try {
			Class c = Class.forName(factoryName);
			_instance = (DAOFactory) c.newInstance();
		}
		catch (Exception e) {
			_instance = new DAOFactory();
		}
	} */
	
	/* static {
		String factoryName = ResourceBundle.getBundle("conf.DAOFactory.properties").getString("DAOFactory");
		System.out.println("factoryName: " + factoryName);
		try {
			Class c = Class.forName(factoryName);
			_instance = (DAOFactory) c.newInstance();
		}
		catch (Exception e) {
			_instance = new DAOFactory();
		}
	} */
	
	//private Map daos;
	
	/* public DAOFactory() {
		this.daos = new HashMap();
	} */
	
	/* public static DAOFactory getDefaultFactory() {
		return _instance;
	} */
	
	/* protected void putDAO(String daoName, ModelDAO model) {
		this.daos.put(daoName, model);
	}
	
	public ModelDAO getDAO(String daoName) {
		return (ModelDAO) this.daos.get(daoName);
	} */
	
	public abstract DVDDAO getDVDDAO();
	public abstract KindDAO getKindDAO();
	public abstract UserDAO getUserDAO();
	public abstract LoanDAO getLoanDAO();
	public abstract OpinionDAO getOpinionDAO();
	
}