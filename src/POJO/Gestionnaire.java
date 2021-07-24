package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import DAO.DAOFactory;
import DAO.GestionnaireDAO;

public class Gestionnaire extends Personne implements Serializable {

	//FONCTIONNEMENT
	private static final long serialVersionUID = 2827991491753710283L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static GestionnaireDAO dao = factory.getGestionnaireDAO();
	
	//ATTRIBUTS
	private List<PlanningSalle> listePlanningSalle = new ArrayList<>();

	//ACCESSEURS
	public List<PlanningSalle> getlistePlanningSalle() {
		return listePlanningSalle;
	}
	public void setPlanningSalle(List<PlanningSalle> listePlanningSalle) {
		this.listePlanningSalle = listePlanningSalle;
	}

	//CONSTRUCTEURS 
	public Gestionnaire(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password, int id) {
		super(nom, prenom, rue, numero, ville, cp, email, password, id);
		
	}
	
	public Gestionnaire(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password) {
		super(nom, prenom, rue, numero, ville, cp, email, password);
		
	}
	
	public Gestionnaire(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password, int id, String discriminator) {
		super(nom, prenom, rue, numero, ville, cp, email, password, id, discriminator);
	}
	
	//METHODES
	//Connexion
	public int login(){
		try {
				Personne p = this.findbyMail();
				if(this.email.equals(p.getEmail())) {
					if(this.password.equals(p.getPassword())) {
						return 1;		
					}
					return 2; 
				}
				return 0;
		}catch (Exception e) {
			return 0;
		}
	}
	

}
