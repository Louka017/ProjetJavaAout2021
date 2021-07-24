package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Artiste;

public class ArtisteDAO  implements DAO<Artiste> {

	//ATTRIBUT
	private Connection connect = null;
	
	//CONSTRUCTEUR
	public ArtisteDAO(Connection conn) {
		this.connect = conn;
	}
	
	//METHODES
	//Create
	public boolean create(Artiste obj) {
		try{
			String create = "INSERT INTO Personne (Nom,Prenom,Rue,Numero,Ville,CodePostal,Discriminator,Email,MotDePasse) "
			+ "values ('" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getRue() + "','" + obj.getNumero() + "','" + obj.getVille() + "','" + obj.getCp() + "','Artiste','"+ obj.getEmail() + "','" + obj.getPassword() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Artiste obj) {
		try {	
			String delete = "DELETE FROM Personne where idPersonne = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Update
	public boolean update(Artiste obj) {
		try {
			String update = "UPDATE Personne SET Nom = '" + obj.getNom() +"' WHERE idPersonne = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Find
	public Artiste find(int id) {
		Artiste a = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE idPersonne = '" + id + "');");
			if (result.first())
				 a = new Artiste(result.getString("Nom"), result.getString("Prenom"), result.getString("Rue"), result.getInt("Numero"), result.getString("Ville"), result.getInt("CodePostal"), result.getString("Email"), result.getString("MotDePasse"),result.getInt("idPersonne"),result.getString("Discriminator"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	//Trouver un artiste grâce à son mail
	public Artiste findbyMail(Artiste obj) {
		Artiste artiste = null;
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE Email = '" + obj.getEmail() + "';");
			if (result.first())
				artiste = new Artiste(result.getString("Nom"), result.getString("Prenom"), result.getString("Rue"),result.getInt("Numero"),result.getString("Ville"),result.getInt("CodePostal"),result.getString("Email"), result.getString("MotDePasse"), result.getInt("IdPersonne"),result.getString("Discriminator"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return artiste;
	}
	
	//Obtenir tous les artistes
	public List<Artiste> getAllArtistes() {
		List<Artiste> artistes = new ArrayList<Artiste>();
		try {
			String query = "SELECT * from Personne where Discriminator ='Artiste' ";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					Artiste artiste = new Artiste(result.getString("Nom"), result.getString("Prenom"), result.getString("Rue"),result.getInt("Numero"),result.getString("Ville"),result.getInt("CodePostal"),result.getString("Email"), result.getString("MotDePasse"), result.getInt("idPersonne"));
					artistes.add(artiste);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artistes;
	}
}
