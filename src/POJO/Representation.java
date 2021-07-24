package POJO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DAO.DAOFactory;
import DAO.RepresentationDAO;

public class Representation implements Serializable{

	//FONCTIONNEMENT
	private static final long serialVersionUID = -7253524476235799528L;
	protected static DAOFactory factory = (DAOFactory) DAOFactory.getFactory(0);
	protected static RepresentationDAO dao = factory.getRepresentationDAO();
	
	//ATTRIBUTS		Calculer le nombre de place totale par representation ( j'avais en effet fait l'erreur de calculer par spectacle).
	private int id;
	private Date date;
	private Date heureDebut;
	private Date heureFin;
	private Spectacle spectacle;
	
	//ACCESSEURS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Spectacle getSpectacle() {
		return spectacle;
	}
	public void setSpectacle(Spectacle spectacle) {
		this.spectacle = spectacle;
	}
	
	//CONSTRUCTEURS
	public Representation() {
	}
	
	public Representation(Spectacle spectacle) {
		this.spectacle = spectacle;
	}
	
	public Representation(int id,Date date, Date heureDebut, Date heureFin) {
		this.id = id;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	//METHODES
	//Ajouter une représentation
	public boolean addRepresentation() {
		return dao.create(this);
	}
	
	//Trouver une représentation
	public Representation find() {
		return dao.find(this);
	}
	
	//Obtenir toutes les représentations
	public List<Representation> getAllRepresentations(){
		return dao.getAllRepresentation(this);
	}
	
	//Obtenir toutes les heures des représentations
	public List<Representation> getAllRepresentationsHoraire(){
		return dao.getAllRepresentationHoraire(this);
	}
	
	//Regarde si la date de la representation est comprise dans les jours du planning et ( date > à J 12h00 et date < J+ 12h00)
	public boolean verifyDateRepresentation(PlanningSalle i) {
		//FORMAT
		DateFormat dateFormatLong  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//PARSE EN STRING 
		String strDateDebutAvecHeure = dateFormatLong.format(this.getHeureDebut());
		String strDateFinAvecHeure   = dateFormatLong.format(this.getHeureFin());
		try 
		{
			//PARSE EN DATE AVEC HEURE
			Date DateHeureDebut = dateFormatLong.parse(strDateDebutAvecHeure);
			Date DateHeureFin = dateFormatLong.parse(strDateFinAvecHeure);
			//VERIFICATION
			if( ( DateHeureDebut.before(i.getDateDebutSal() ) || DateHeureFin.after(i.getDateFinSal()) ) ){
				return false;
			}
			return true;
		} 
		catch (ParseException e) 
		{
		}
		return true;
	}
	
	//Regarde si aucune représentation n'a lieu à cette date
	public int verifyWithAllRepresentations(){
		int error = 0;
		for(Representation r : this.spectacle.getListeRepresentation()) {
			if( (this.getHeureDebut().getTime() <= (r.getHeureDebut().getTime())) && (this.getHeureFin().getTime() >= (r.getHeureDebut().getTime())) )
			{
				error = 1;
			}
			
			if( (this.getHeureDebut().getTime() >= (r.getHeureDebut().getTime())) && (this.getHeureDebut().getTime() <= (r.getHeureFin().getTime())) ) 
			{
				error = 1;
			}
			
			if( (this.getHeureDebut().getTime() == (r.getHeureDebut().getTime())) && (this.getHeureFin().getTime() == (r.getHeureFin().getTime()))  )
			{
				error = 1;
			}
		}
		return error;
	}
	
	//ToString
	public String toString() {	
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String strDate = dateFormat.format(heureDebut); 
		String strDate2 = dateFormat.format(heureFin);
		return date + "       " + strDate + "            " + strDate2;
	}
}
