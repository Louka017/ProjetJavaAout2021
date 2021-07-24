package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Commande;
import POJO.Place;

public class PlaceDAO implements DAO<Place> {
	
	//ATTRIBUT
	private Connection connect = null;
	
	//CONSTRUCTEUR
	public PlaceDAO(Connection conn) {
		this.connect = conn;
	}
	
	//METHODES
	//Create
	public  boolean create(Place obj) {
		try {
		String create = "INSERT INTO Place (Prix, NumPlace, idRepresentation)"
				+ "values ('" + obj.getPrix() + "','" + obj.getNumeroPlace() + "','" + obj.getRepresentation().getId() + "');";
		connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Place obj) {
		try {	
			String delete = "DELETE FROM Place where idPlace = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(Place obj) {
		try {
			String update = "UPDATE Place SET Prix = '" + obj.getPrix() +"' WHERE idPlace = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	public Place find(int id) {
		Place p = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE idPlace = '" + id + "');");
			if (result.first())
				 p = new Place (result.getInt("idPlace"), result.getInt("Prix"), result.getInt("NumeroPlace"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	//Trouver le dernier objet ajouter
	public Place find() {
		Place p= null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT TOP 1 * FROM Place ORDER BY idPlace DESC");
			if (result.first())
				p = new Place(result.getInt("idPlace"), result.getInt("Prix"),result.getInt("NumPlace"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	//Ajouter dans la table Place_Commande
	public  boolean PlaceCommande(Place obj, Commande c) {
		try {
		String create = "INSERT INTO Place_Commande (idPlace, idCommande)"
				+ "values ('" + obj.getId() + "','" + c.getId() + "');";
		connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
