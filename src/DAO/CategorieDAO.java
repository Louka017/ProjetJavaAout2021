package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import POJO.Categorie;
import POJO.Configuration;
import POJO.Representation;
import POJO.Spectacle;

public class CategorieDAO implements DAO<Categorie> {
	
	//ATTRIBUT
	private Connection connect = null;
	
	//CONSTRUCTEUR
	public CategorieDAO(Connection conn) {
		this.connect = conn;
	}

	//METHODES
	//Create
	public boolean create(Categorie obj) {
		try {
		String create = "INSERT INTO Categorie (Type, Prix, NbrPlaceDispo, NbrPlaceMax) "
				+ "VALUES ('" + obj.getType() + "','" + obj.getPrix() + "','" + obj.getNbrPlaceDispo() + "','" + obj.getNbrPlaceMax()+ "');";
		connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Categorie obj) {
		try {	
			String delete = "DELETE FROM Categorie where idCategorie = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(Categorie obj) {
		try {
				String update = "UPDATE CATEGORIE SET NbrPlaceDispo = '" + obj.getNbrPlaceDispo() +"' WHERE idCategorie = '" + obj.getId() + "';";
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	public Categorie find(int id) {
		Categorie c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Categorie WHERE idCategorie = '" + id + "');");
			if (result.first())
				c = new Categorie(result.getInt("idCategorie"), result.getString("Type"),result.getInt("Prix"),result.getInt("NbrPlaceDispo"),result.getInt("NbrPlaceMax"));
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//Trouver la dernière catégorie ajoutée
	public Categorie find() {
		Categorie c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT TOP 1 * FROM CATEGORIE ORDER BY idCategorie DESC");
			if (result.first())
				c = new Categorie(result.getInt("idCategorie"), result.getString("Type"),result.getInt("Prix"),result.getInt("NbrPlaceDispo"),result.getInt("NbrPlaceMax"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//Update idConfiguration
	public boolean updateConf(Categorie obj, Configuration conf) {
		try {
			String update ="UPDATE Categorie SET idConfiguration = '" + conf.getId() + "' WHERE idCategorie = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Update idSpectacle
	public boolean updateSpec(Categorie obj,Spectacle s) {
		try {
			String update ="UPDATE Categorie SET idSpectacle = '" + s.getId() + "' WHERE idCategorie = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Update idRepresentation
	public boolean updateRep(Categorie obj, Representation r) {
		try {
			String update ="UPDATE Categorie SET idRepresentation = '" + r.getId() + "' WHERE (idCategorie = '" + obj.getId() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Trouver une categorie grâce à un spectacle
	public Categorie findWithSpectacle(Spectacle s) {
		Categorie c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM CATEGORIE WHERE idSpectacle = '" + s.getId()+"';");
			if (result.first())
				c = new Categorie(result.getInt("idCategorie"), result.getString("Type"),result.getInt("Prix"),result.getInt("NbrPlaceDispo"),result.getInt("NbrPlaceMax"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//Obtenir toutes les catégories d'une configuration
	public List<Categorie> getAllCategories(Configuration conf) {
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			String query = "SELECT * from Categorie WHERE (idConfiguration = '" + conf.getId() + "') ; ";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					Categorie cat = new Categorie(result.getInt("idCategorie"),result.getString("Type"),result.getInt("Prix"),result.getInt("NbrPlaceDispo"),result.getInt("NbrPlaceMax"));
					categories.add(cat);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	//Obtenir toutes les catégories d'une représentation
	public List<Categorie> getAllCategoriesByRep(Representation r) {
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			String query = "SELECT * from Categorie WHERE (idRepresentation = '" + r.getId() + "') ; ";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					Categorie cat = new Categorie(result.getInt("idCategorie"),result.getString("Type"),result.getInt("Prix"),result.getInt("NbrPlaceDispo"),result.getInt("NbrPlaceMax"));
					categories.add(cat);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

}
