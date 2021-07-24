package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Configuration;
import POJO.Spectacle;

public class ConfigurationDAO  implements DAO<Configuration> {
	
	//ATTRIBUT
	private Connection connect = null;
	
	//CONSTRUCTEUR
	public ConfigurationDAO(Connection conn) {
		this.connect = conn;
	}
	
	//METHODES
	//Create
	public boolean create(Configuration obj) {
		try {
			String create = "INSERT INTO CONFIGURATION (Type) "
					+ "values ('" + obj.getType() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			return true;
	}
	
	//Delete
	public boolean delete(Configuration obj) {
		try {	
			String delete = "DELETE FROM Configuration where idConfiguration = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Update
	public boolean update(Configuration obj) {
		try {
			String update = "UPDATE Configuration SET Type = '" + obj.getType() +"' WHERE idConfiguration = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Find
	public Configuration find(int id) {
		Configuration c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Configuration WHERE idConfiguration = '" + id + "');");
			if (result.first())
				 c = new Configuration(result.getInt("idConfiguration"), result.getString("Type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	//Ajouter l'id Spectacle à la configuration
	public boolean updateIdSpec(Configuration obj, Spectacle s) {
		try {
			String update ="UPDATE Configuration SET idSpectacle = '" + s.getId() + "' WHERE (idConfiguration = '"+ obj.getId()+"');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Trouver la derniere configuration ajoutée
	public Configuration find(){
		Configuration c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT TOP 1 * FROM Configuration ORDER BY idConfiguration DESC");
			if (result.first())
				 c = new Configuration(result.getInt("idConfiguration"), result.getString("Type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//Trouver la derniere configuration grâce à un spectacle
	public Configuration findBySpectacle(Spectacle s){
		Configuration c = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Configuration WHERE idSpectacle ='"+s.getId()+"';");
			if (result.first())
				 c = new Configuration(result.getInt("idConfiguration"), result.getString("Type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	

}
