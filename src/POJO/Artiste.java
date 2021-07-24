package POJO;

import java.io.Serializable;
import java.util.List;

import DAO.ArtisteDAO;
import DAO.DAOFactory;

public class Artiste extends Personne implements Serializable {

	//FONCTIONNEMENT
	private static final long serialVersionUID = -3526632018327934816L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static ArtisteDAO dao = factory.getArtisteDAO();
	
	//CONSTRUCTEURS
	public Artiste() {
	}
	
	public Artiste(String email) {
		super(email);
	}
	
	public Artiste(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password) {
		super(nom, prenom, rue, numero, ville, cp, email, password);
	}
	
	public Artiste(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password, int id) {
		super(nom, prenom, rue, numero, ville, cp, email, password, id);
	}
	
	public Artiste(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password, int id, String discriminator) {
		super(nom, prenom, rue, numero, ville, cp, email, password, id, discriminator);
	}
	
	//METHODES
	//Récupérer la liste de tous lesartistes
	public static List<Artiste> getAllArtistes() {
		return dao.getAllArtistes();
	}
	
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
	
	//Inscription
	public boolean register() {
		return dao.create(this);
	}
	
	//Trouver un artiste par mail
	public Artiste findByMail() {
		return dao.findbyMail(this);
	}
	
	//Petite Regex de vérification
	String regnom = "^[A-Za-z]+$" ;
	String regmail = "^[A-Za-z0-9.-]+@[A-Za-z]+.(com|be|eu|fr)$";
	String regrue = "^[A-Za-z -']+$" ;
	String regnum = "^[0-9]+$";
	String regcp = "^[0-9]{4}$";
	public String regexVerification() {
		String num =  String. valueOf(numero);
		String cdp = String. valueOf(cp);
		if(!nom.matches(regnom) || nom == "") {
			return "Entrer un nom correct !";
		}
			if(!prenom.matches(regnom) || prenom == "") {
				return "Entrer un prénom correct !";
			}
				if(!rue.matches(regrue) || rue == "") {
					return "Entrer un nom de rue correct !";
				}
					if(!num.matches(regnum) || num == "") {
						return "Entrer un numéro correct !";
					}
						if(!cdp.matches(regcp) || cdp == "") {
							return "Entrer un code postal de 4 chiffres !";
						}
							if(!email.matches(regmail) || email == "") {								
								return "Entrer un mail correct !";
							}
		return "correct";
	}
	
	//ToString
	public String toString()
	{
		return nom + " " + prenom;
	}
}
