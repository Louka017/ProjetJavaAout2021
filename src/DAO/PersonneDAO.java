package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Personne;

public class PersonneDAO implements DAO<Personne> {
	
	//ATTRIBUT
	protected Connection connect = null;
	
	//CONSTRUCTEUR
	public PersonneDAO(Connection conn) {
		this.connect = conn;
	}
	
	//METHODES
	//Create
	public boolean create(Personne obj) {
		try{
			String create = "INSERT INTO Personne (Nom,Prenom,Rue,Numero,Ville,CodePostal,Discriminator,Email,MotDePasse) "
			+ "values ('" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getRue() + "','" + obj.getNumero() + "','" + obj.getVille() + "','" + obj.getCp() + "','','"+ obj.getEmail() + "','" + obj.getPassword() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Personne obj) {
		try {	
			String delete = "DELETE FROM Personne where idersonne = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(Personne obj) {
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
	public Personne find(int id) {
		Personne p = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE idPersonne = '" + id + "');");
			if (result.first())
				 p = new Personne(result.getString("Nom"), result.getString("Prenom"), result.getString("Rue"), result.getInt("Numero"), result.getString("Ville"), result.getInt("CodePostal"), result.getString("Email"), result.getString("MotDePasse"),result.getInt("idPersonne"),result.getString("Discriminator"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	//Trouver une personne grâce à son adresse mail
	public Personne findbyMail(Personne obj) {
		Personne p = null;
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE Email = '" + obj.getEmail() + "';");
			if (result.first())
				p = new Personne(result.getString("Nom"), result.getString("Prenom"), result.getString("Rue"),result.getInt("Numero"),result.getString("Ville"),result.getInt("CodePostal"),result.getString("Email"), result.getString("MotDePasse"), result.getInt("IdPersonne"),result.getString("Discriminator"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}

}
