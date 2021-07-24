package POJO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.DAOFactory;
import DAO.PlanningSalleDAO;

public class PlanningSalle implements Serializable  {

	//FONCTIONNEMENT
	private static final long serialVersionUID = 8581140104258101507L;
	protected static DAOFactory factory = (DAOFactory) DAOFactory.getFactory(0);
	protected static PlanningSalleDAO dao = factory.getPlanningSalleDAO();

	//ATTRIBUTS
	private int id;
	private Date dateDebutSal;
	private Date dateFinSal;
	private List<Spectacle> listeSpectacle = new ArrayList<>();
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateDebutSal() {
		return dateDebutSal;
	}
	public void setDateDebutSal(Date dateDebutSal) {
		this.dateDebutSal = dateDebutSal;
	}
	
	public Date getDateFinSal() {
		return dateFinSal;
	}
	public void setDateFinSal(Date dateFinSal) {
		this.dateFinSal = dateFinSal;
	}
	
	public List<Spectacle> getListeSpectacle() {
		return listeSpectacle;
	}
	public void setListeSpectacle(List<Spectacle> listeSpectacle) {
		this.listeSpectacle = listeSpectacle;
	}
	
	//CONSTRUCTEURS
	public PlanningSalle() {
		
	}
	
	public PlanningSalle(Date dateDebutSal, Date dateFinSal) {
		this.dateDebutSal = dateDebutSal;
		this.dateFinSal = dateFinSal;
	}
	
	public PlanningSalle(int id, Date dateDebutSal, Date dateFinSal) {
		this.id = id;
		this.dateDebutSal = dateDebutSal;
		this.dateFinSal = dateFinSal;
	}
	
	
	//METHODES
	//Ajouter un planning salle
	public boolean addPlanningSalle() {
		return dao.create(this);
	}
	
	//Obtenir tous les plannings salle
	public static List<PlanningSalle> getAllPlanningSalle(){
		return dao.getAllPlannings();
	}
	
	//Update de planning salle
	public boolean update() {
		return dao.update(this);
	}
	
	//Ajouter un spectacle à la liste					
	public void addSpectacle(Spectacle s) {
		 this.listeSpectacle.add(s);
	}
	
	//Trouver planning via date début + date fin
	public PlanningSalle finfByDate() {
		return dao.findByDate(this);
	}
	
	//Trouver planning via date et spectacle
	public PlanningSalle findBySpectacle(){
		return dao.findBySpectacle(this);
	}
	
	//Voir si la date est deja reservée
	public boolean verifyDatePlanningSalle() {
		List<PlanningSalle> listePlanning = dao.getAllPlannings();
		for(PlanningSalle i : listePlanning){
			if(
				( (this.getDateDebutSal().before(i.getDateDebutSal())) && ( (this.getDateFinSal().after(i.getDateDebutSal())  )))) {
				  	return false;
			  }
			if(
				( (this.getDateDebutSal().before(i.getDateDebutSal()))  && (this.getDateFinSal().after(i.getDateFinSal()))  ) ) {
					return false;
			}
			if(
				( (this.getDateDebutSal().after(i.getDateDebutSal()))  && (this.getDateDebutSal().before(i.getDateFinSal())) ) ) {
					return false;
			}
			if(
				( (this.getDateDebutSal().equals(this.getDateFinSal()) )) ) {
					return false;
			} 
		  }
		return true;
	}
	
	//ToString
	public String toString(){
		//FORMAT
		DateFormat dateFormatLong  = new SimpleDateFormat("dd-MM-yyyy");
		//PARSE EN STRING 
		String strDateDebut = dateFormatLong.format(this.dateDebutSal);
		String strDateFin   = dateFormatLong.format(this.dateFinSal);
		return "      " + strDateDebut + "       au       " + strDateFin;
	}

}
