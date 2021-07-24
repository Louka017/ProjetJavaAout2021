package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Commande;
import POJO.Personne;

public class CommandeDAO implements DAO<Commande> {
	
	//ATTRIBUT
	private Connection connect = null;
	
	//CONSTRUCTEUR
	public CommandeDAO(Connection conn) {
		this.connect = conn;
	}

	//METHODES
	//Create
	public boolean create(Commande obj) {
		try{
			String create = "INSERT INTO Commande (ModePayement,ModeLivraison,Cout) "
			+ "values ('" + obj.getModePayement() + "','" + obj.getModeLivraison()+ "','" + obj.getCoutTotal() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Commande obj) {
		try {	
			String delete = "DELETE FROM Commande where idersonne = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Update
	public boolean update(Commande obj) {
		try {
			String update = "UPDATE Commande SET Cout = '" + obj.getCoutTotal() +"' WHERE idCommande = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	public Commande find(int id) {
		Commande c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Commande WHERE idCommande = '" + id + "');");
			if (result.first())
				 c = new Commande(result.getInt("idCategorie"), result.getString("ModePayement"), result.getString("ModeLivraison"), result.getInt("Cout"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//Ajouter une commande avec une personne liée
	public  boolean createWithPersonne(Commande obj ,Personne p) {
		try {
			String create = "INSERT INTO Commande (ModePayement, ModeLivraison, Cout, idPersonne)"
					+ "values ('" + obj.getModePayement() + "','" + obj.getModeLivraison() + "', '" + obj.getCoutTotal() + "','" + p.getId() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Trouver la dernière commande ajoutée
	public Commande find() {
		Commande c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT TOP 1 * FROM COMMANDE ORDER BY idCommande DESC");
			if (result.first())
				c = new Commande(result.getInt("idCommande"), result.getString("ModePayement"),result.getString("ModeLivraison"), result.getInt("Cout"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
