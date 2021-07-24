package POJO;

import java.io.Serializable;
import java.util.List;

import DAO.CategorieDAO;
import DAO.DAOFactory;

public class Categorie implements Serializable{

	//FONCTIONNEMENT
	private static final long serialVersionUID = 3633610078969934440L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static CategorieDAO dao = factory.getCategorieDAO();

	//ATTRIBUTS
	private int id;
	private String type;
	private int prix;
	private int nbrPlaceDispo;
	private int nbrPlaceMax;
	
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
	
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
		
	public int getNbrPlaceDispo() {
		return nbrPlaceDispo;
	}
	public void setNbrPlaceDispo(int nbrPlaceDispo) {
		this.nbrPlaceDispo = nbrPlaceDispo;
	}
	
	public int getNbrPlaceMax() {
		return nbrPlaceMax;
	}
	public void setNbrPlaceMax(int nbrPlaceMax) {
		this.nbrPlaceMax = nbrPlaceMax;
	}
	
	//CONSTRUCTEURS
	public Categorie() {
		
	}
	
	public Categorie(int nbrPlaceMax) {
		this.nbrPlaceMax = nbrPlaceMax;
	}
	
	public Categorie(String type, int prix, int nbrPlaceDispo, int nbrPlaceMax) {
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
	}
	
	public Categorie(int id, String type, int prix, int nbrPlaceDispo, int nbrPlaceMax) {
		this.id = id;
		this.type = type;
		this.prix = prix;
		this.nbrPlaceDispo = nbrPlaceDispo;
		this.nbrPlaceMax = nbrPlaceMax;
	}
	
	//METHODES
	//Décrementer une place
	public boolean decrementerPlace() {
		this.nbrPlaceDispo = this.nbrPlaceDispo - 1;
		return true;
	}
	
	//Ajouter une catégorie
	public boolean addCategorie() {
		return dao.create(this);
	}
	
	//Trouver une catégorie
	public Categorie find() {
		return dao.find();
	}
	
	//Récupérer toutes les catégories par configurations
	public static List<Categorie>getAllCategories(Configuration conf){
		return dao.getAllCategories(conf);
	}
	
	//Récupérer toutes les catégories par représentation
	public static List<Categorie>getAllCategoriesByRep(Representation r){
		return dao.getAllCategoriesByRep(r);
	}
	
	//Trouver une Catégorie par spectacle
	public Categorie findWithSpectacle(Spectacle s) {
		return dao.findWithSpectacle(s);
	}
	
	//Update une catégorie
	public boolean update() {
		return dao.update(this);
	}
	
	//Update l'id Representation dans la table Catégorie
	public boolean updateRep (Representation r) {
		return dao.updateRep(this,r);
	}
	
	//Update l'id Configuration dans la table Catégorie
	public boolean updateConf(Configuration c) {
		return dao.updateConf(this,c);
	}
	
	//Update l'id Spectacle dans la table Catégorie
	public boolean updateSpec(Spectacle s) {
		return dao.updateSpec(this,s);
	}
	
	//Limite places DEBOUT
	public static boolean verifyDebout(int libre) {
		if(libre < 0 || libre > 8000)
			return false;
		return true;
	}
		
	//Limite places CONCERT
	public static boolean verifyBronzeConcert(int bronze) {
		if(bronze < 0 || bronze > 3000)
			return false;
		return true;
	}
	
	//Limite places CONCERT
	public static boolean verifyArgent(int argent) {
		if(argent < 0 || argent > 1500)
			return false;
		return true;
	}
		
	//Limite places CONCERT
	public static boolean verifyOrConcert(int or) {
		if(or < 0 || or > 500)
			return false;
		return true;
	}
	
	//Limite places CIRQUE
	public static boolean verifyBronzeCirque(int bronze) {
		if(bronze < 0 || bronze > 1500)
			return false;
		return true;
	}
	
	//Limite places CIRQUE
	public static boolean verifyOrCirque(int or) {
		if(or < 0 || or > 2000)
			return false;
		return true;
	}
	
	//Limite places CIRQUE
	public static boolean verifyDiamantCirque(int diamant) {
		if(diamant < 0 || diamant > 1000)
			return false;
		return true;
	}
	
}
