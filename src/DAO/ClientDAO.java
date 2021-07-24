package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Client;

public class ClientDAO implements DAO<Client> {
	
	//ATTRIBUT
	private Connection connect = null;

	//CONSTRUCTEUR
	public ClientDAO(Connection conn) {
		this.connect = conn;
	}

	//METHODES
	//Create
	public boolean create(Client obj) {
		try {
			String create = "INSERT INTO Personne (Nom,Prenom,Rue,Numero,Ville,CodePostal,Discriminator,Email,MotDePasse) "
			+ "values ('" + obj.getNom() + "','" + obj.getPrenom() + "','" + obj.getRue() + "','" + obj.getNumero() + "','" + obj.getVille() + "','" + obj.getCp() + "','Client','"+ obj.getEmail() + "','" + obj.getPassword() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Client obj) {
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
	public boolean update(Client obj) {
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
	public Client find(int id) {
		Client c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE idPersonne = '" + id + "');");
			if (result.first())
				 c = new Client(result.getString("Nom"), result.getString("Prenom"), result.getString("Rue"), result.getInt("Numero"), result.getString("Ville"), result.getInt("CodePostal"), result.getString("Email"), result.getString("MotDePasse"),result.getInt("idPersonne"),result.getString("Discriminator"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
