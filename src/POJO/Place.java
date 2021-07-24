package POJO;

import java.io.Serializable;
import DAO.DAOFactory;
import DAO.PlaceDAO;

public class Place implements Serializable {

	//FONCTIONNEENT
	private static final long serialVersionUID = -3048706543695775020L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static PlaceDAO dao = factory.getPlaceDAO();
	
	//ATTRIBUTS
	private int id;
	private int prix;
	private int numeroPlace;
	private Representation representation;
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getNumeroPlace() {
		return numeroPlace;
	}
	public void setNumeroPlace(int numeroPlace) {
		this.numeroPlace = numeroPlace;
	}
	
	public Representation getRepresentation() {
		return representation;
	}
	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}
	
	//CONSTRUCEURS
	public Place() {

	}
	
	public Place(int prix) {
		this.prix = prix;
	}
	
	public Place(int prix,int numeroPlace, Representation representation) {
		this.prix = prix;
		this.numeroPlace = numeroPlace;
		this.representation = representation;
	}
	
	public Place(int id, int prix,int numeroPlace) {
		this.id = id;
		this.prix = prix;
		this.numeroPlace = numeroPlace;
	}
	
	//METHODES
	//Ajouter une place
	public boolean addPlace() {
		return dao.create(this);
	}
	
	//Trouver une place
	public Place find() {
		return dao.find();
	}
	
	//Ajouter dans ma table PLACE_COMMANDE de ma base de données 
	public boolean PlaceCommande(Commande c) {
		return dao.PlaceCommande(this,c);
	}
}
