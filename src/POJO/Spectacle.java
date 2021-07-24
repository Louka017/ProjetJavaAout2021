package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOFactory;
import DAO.SpectacleDAO;

public class Spectacle  implements Serializable{

	//FONCTIONNEMENT
	private static final long serialVersionUID = -3582331972992320638L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static SpectacleDAO dao = factory.getSpectacleDAO();
	
	//ATTTRIBUTS
	private int id;
	private String titre;
	private int nbrPlaceParClient;
	private Configuration configuration;
	private List<Artiste> listeArtiste = new ArrayList<>();
	private List<Representation> listeRepresentation = new ArrayList<>();
	private static Spectacle instance = null;
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public int getNbrPlaceParClient() {
		return nbrPlaceParClient;
	}
	public void setNbrPlaceParClient(int nbrPlaceParClient) {
		this.nbrPlaceParClient = nbrPlaceParClient;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public List<Artiste> getListeArtiste() {
		return listeArtiste;
	}
	public void setListeArtiste(List<Artiste> listeArtiste) {
		this.listeArtiste = listeArtiste;
	}
	
	public List<Representation> getListeRepresentation() {
		return listeRepresentation;
	}
	public void setListeRepresentation(List<Representation> listeRepresentation) {
		this.listeRepresentation = listeRepresentation;
	}
	
	//SINGLETON
	public static Spectacle getInstance() {
		if(instance==null) {
			instance = new Spectacle() ;
		}
		return instance ;
	}
	
	
	//METHODES
	//Ajout d'un artiste à listeArtiste
	public void addArtiste(Artiste artiste) {
		this.listeArtiste.add(artiste);
	}
	
	//Ajouter une représentation
	public void addRepresentation(Representation representation) {
		this.listeRepresentation.add(representation);
	}
	
	//Ajouter un spectacle
	public boolean addSpectacle() {
		return dao.create(this);
	}
	
	//Ajout d'un artiste à un spectacle dans la db de ma table ARTISTE_SPECTALCE
	public boolean addArtistesSpectacle() {
		return dao.addArtistesSpectacle(this);
	}
	
	//Trouver un spectacle grâce à son titre
	public Spectacle findByTitle() {
		return dao.findByTitle(this);
	}
	
	//ToString
	public String toString(){
		return titre;
	}
	
}
