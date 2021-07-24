package POJO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.DAOFactory;
import DAO.ReservationDAO;

public class Reservation implements Serializable {

	//FONCTIONNEMENT
	private static final long serialVersionUID = 2752339235768498033L;
	protected static DAOFactory factory = (DAOFactory) DAOFactory.getFactory(0);
	protected static ReservationDAO dao = factory.getReservationDAO();
	
	//ATTRIBUTS
	private int id;
	private float prix;
	private Date date;
	private Date heureDebut;
	private Date heureFin;
	private PlanningSalle planningSalle;
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	
	public PlanningSalle getPlanningSalle() {
		return planningSalle;
	}
	public void setPlanningSalle(PlanningSalle planningSalle) {
		this.planningSalle = planningSalle;
	}
	
	//CONSTRUCTEURS
	public Reservation() {
		
	}
	
	public Reservation(int prix, Date date, Date heureDebut, Date heureFin, PlanningSalle planningSalle) {
		this.prix = prix;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.planningSalle = planningSalle;
	}
	
	public Reservation(int id, int prix, Date date, Date heureDebut, Date heureFin) {
		this.id =id;
		this.prix = prix;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	//METHODES
	//Ajouter une réservation avec idOrganisateur
	public boolean addReservation(Organisateur o) {
		return dao.addReservation(this,o);
	}
	
	//Calcul du prix de la réservation de la salle               
	@SuppressWarnings("deprecation")
	public void calclulatePrice() {
		@SuppressWarnings("unused")
		//ATTRIBUTS
		DateFormat dateFormatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		float prix = 0;		
		
		//CALCUL DE LA DIFFERENCE DE JOURS POUR SAVOIR LE PRIX
		long debutlong = planningSalle.getDateDebutSal().getTime();
		long finlong = planningSalle.getDateFinSal().getTime();
		long correctDate =((finlong - debutlong) / 79377416);
		int DifferenceDeJours = (int)correctDate;
		
		//CALCUL DU PRIX
		for(int i=0; i<DifferenceDeJours ; i++) 
		{	
			if(planningSalle.getDateDebutSal().getDay() == 5 || planningSalle.getDateDebutSal().getDay() == 6) 
			{
				prix += 4500;
			}
			else 
			{
				prix +=3000;
			}
			
			//DATE DEBUT
			long debutlong1 = planningSalle.getDateDebutSal().getTime();
			
			//AJOUTE 79377416 (long) QUI CORRESPOND A 24h. PERMET D'OBTENIR LE JOUR SUIVANT
			debutlong1 += 79377416;	
			
			try
			{
				planningSalle.setDateDebutSal(new java.sql.Date (debutlong1));	
			} 
			catch(Exception e) {
			}					
		}
		
		//TARIF DEGRESSIF (optionnel)
		if(DifferenceDeJours == 2) {
			prix = prix - ( (prix*5) /(float)100 );
		}
		
		if(DifferenceDeJours == 3){
			prix = prix - ( (prix*10) /(float)100 );
		}
		
		if(DifferenceDeJours == 7){
			prix = prix - ( (prix*20) /(float)100 );
		}
		
		if(DifferenceDeJours == 15){
			prix = prix - ( (prix*30) /(float)100 );
		}
		
		this.prix = prix;
	}
}
