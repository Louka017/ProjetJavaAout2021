package POJO;

import java.io.Serializable;
import java.util.List;

import DAO.CatalogueDAO;
import DAO.DAOFactory;


public class Catalogue implements Serializable {
	
	//FONCTIONNEMENT
	private static final long serialVersionUID = 6376470503190686235L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static CatalogueDAO dao = factory.getCatalogueDAO();

	//ATTRIBUT
	private static Catalogue instance = null;
	
	//SINGLETON
	public static Catalogue getInstance() {
		if(instance==null) {
			instance = new Catalogue() ;
		}
		return instance ;
	}
	
	//METHODES
	//Voir la liste de tous les spectacles
	public static List<Spectacle> getAllSpectacles() {
		return dao.listeSpectacles();
	}
	
}
