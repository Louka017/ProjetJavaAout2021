package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import DAO.ClientDAO;
import DAO.DAOFactory;

public class Client extends Personne implements Serializable  {

	//FONCTIONNEMENT
	private static final long serialVersionUID = -2430812566256576791L;
	protected static DAOFactory factory = (DAOFactory)DAOFactory.getFactory(0);
	protected static ClientDAO dao = factory.getClientDAO();
	
	//ATTRIBUTS
	private List<Commande> commandes =new ArrayList<>();

	//ACCESSEURS
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	
	//CONSTRUCTEURS
	public Client(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password, int id) {
		super(nom, prenom, rue, numero, ville, cp, email, password, id);	
	}
	
	public Client(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password) {
		super(nom, prenom, rue, numero, ville, cp, email, password);
	}
	
	public Client(String nom, String prenom, String rue, int numero, String ville, int cp, String email, String password, int id, String discriminator) {
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
	
	//Inscription
	public boolean register() {
		return dao.create(this);
	}
	
	//Ajout dans liste commande
	public void addCommande(Commande commande){
		this.commandes.add(commande);
	}
	
	//Petite Regex de vérification
	String regnom = "^[A-Za-z]+$" ;
	String regmail = "^[A-Za-z0-9.-]+@[A-Za-z]+.(com|be|eu|fr)$";
	String regrue = "^[A-Za-z -]+$" ;
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
}
