package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import DAO.CommandeDAO;
import DAO.DAOFactory;

public class Commande implements Serializable  {

	//FONCTIONNEMENT
	private static final long serialVersionUID = -7833218115888236671L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static CommandeDAO dao = factory.getCommandeDAO();
	
	//ATTRIBUTS
	private int id;
	private String modePayement;
	private String modeLivraison;
	private int coutTotal;
	private List<Place> listePlaces = new ArrayList<>();
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModePayement() {
		return modePayement;
	}
	public void setModePayement(String modePayement) {
		this.modePayement = modePayement;
	}
	
	public String getModeLivraison() {
		return modeLivraison;
	}
	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}
	
	public int getCoutTotal() {
		return coutTotal;
	}
	public void setCoutTotal(int coutTotal) {
		this.coutTotal = coutTotal;
	}
	
	public List<Place> getListePlaces() {
		return listePlaces;
	}
	public void setListePlaces(List<Place> listePlaces) {
		this.listePlaces = listePlaces;
	}
	
	//CONSTURCETURS
	public Commande(String modePayement, String modeLivraison, int coutTotal) {
		this.modePayement = modePayement;
		this.modeLivraison = modeLivraison;
		this.coutTotal = coutTotal;
	}
	
	public Commande(int id, String modePayement, String modeLivraison, int coutTotal) {
		this.id = id;
		this.modePayement = modePayement;
		this.modeLivraison = modeLivraison;
		this.coutTotal = coutTotal;
	}
	
	//METHODES
	//Ajouter commande
	public boolean addCommande(Personne p) {
		return dao.createWithPersonne(this,p);
	}
	
	//Ajouter place dans la liste
	public void addPlace(Place place) {
		this.listePlaces.add(place);
	}
	
	//Trouver une commande
	public Commande find() {
		return dao.find();
	}
	
	//ToString
	public String ToString() {
		return "";
	}
	
}
