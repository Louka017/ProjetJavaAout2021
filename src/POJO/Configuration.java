package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import DAO.ConfigurationDAO;
import DAO.DAOFactory;

public class Configuration  implements Serializable {

	//FONCTIONEMENT
	private static final long serialVersionUID = -4973296506746715437L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static ConfigurationDAO dao = factory.getConfigurationDAO();

	//ATTRIBUTS
	private int id;
	private String type;
	private List<Categorie> listeCategorie = new ArrayList<>();
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public List<Categorie> getListeCategorie() {
		return listeCategorie;
	}
	public void setListeCategorie(List<Categorie> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}
	
	//CONSTRUCTEURS
	public Configuration() {
		
	}
	
	public Configuration(String type) {
		this.type = type;
	}
	
	public Configuration(int id,String type) {
		this.id = id;
		this.type = type;
	}
	
	//METHODES
	//Ajouter liste categorie
	public void addCategorie(Categorie categorie) {
		this.listeCategorie.add(categorie);
	}
	
	//Ajouter une configuration
	public boolean addConfiguration() {
		return dao.create(this);
	}
	
	//Trouver une configuration
	public Configuration find(int id) {
		return dao.find(id);
	}
	
	//Trouver la derniere configuration ajoutée
	public Configuration find() {
		return dao.find();
	}
	
	//Trouver une configuration via un spectacle
	public static Configuration findBySpectacle(Spectacle s) {
		return dao.findBySpectacle(s);
	}
	
	//Ajouter l'id spectacle dans configuration
	public boolean update(Spectacle spectacle) {
		return dao.updateIdSpec(this, spectacle);
	}
	
	public boolean delete() {
		return dao.delete(this);
	}
}
