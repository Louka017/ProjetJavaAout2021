package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Representation;

public class RepresentationDAO implements DAO<Representation> {
	
	//ATTRIBUT
	protected Connection connect = null;
	
	//CONSTRUCTEUR
	public RepresentationDAO(Connection conn) {
		this.connect=conn;
	}

	//METHODES
	//Create
	public boolean create(Representation obj) {
		try {
			PreparedStatement ps= null;
			String create = "INSERT INTO Representation (Date, HeureDebut, HeureFin, idSpectacle) VALUES (?,?,?,?)";
			ps = connect.prepareStatement(create);
			connect.createStatement();		
			ps.setDate(1, (java.sql.Date) obj.getDate());			
			ps.setDate(2, (java.sql.Date) obj.getHeureDebut());
			ps.setDate(3, (java.sql.Date) obj.getHeureFin());
			ps.setInt(4, obj.getSpectacle().getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Representation obj) {
		try {	
			String delete = "DELETE FROM Representation where idRepresentation = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(Representation obj) {
		try {
			String update = "UPDATE Representation SET Date = '" + obj.getDate() +"' WHERE idRepresentation = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	public Representation find(int id) {
		Representation r = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE idPlace = '" + id + "');");
			if (result.first())
				 r = new Representation (result.getInt("idRepresentation"), result.getDate("Date"), result.getDate("HeureDebut"), result.getDate("HeureFin"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	
	//Trouver la derniere configuration
	public Representation find(Representation r){
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT TOP 1 * FROM Representation ORDER BY idRepresentation DESC");
			if (result.first())
				 r = new Representation(result.getInt("idRepresentation"), result.getDate("Date"), result.getTime("HeureDebut"),result.getTime("HeureFin"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	//Trouver la liste de toutes les représentations (récupère uniquement l'heure)
	public 	List<Representation> getAllRepresentationHoraire(Representation obj){
		List<Representation> reps = new ArrayList<Representation>();
		try {
			String query = "SELECT * from Representation WHERE idSpectacle = '" + obj.getSpectacle().getId() + "';";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					Representation r = new Representation(result.getInt("idRepresentation"), result.getDate("Date"), result.getTime("HeureDebut"),result.getTime("HeureFin"));
					reps.add(r);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reps;
	}
	
	//Trouver la liste de toutes les représentations (récupère la date et heure)
	public 	List<Representation> getAllRepresentation(Representation obj){
		List<Representation> reps = new ArrayList<Representation>();
		try {
			String query = "SELECT * from Representation WHERE idSpectacle = '" + obj.getSpectacle().getId() + "';";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					Representation r = new Representation(result.getInt("idRepresentation"), result.getDate("Date"), result.getTimestamp("HeureDebut"),result.getTimestamp("HeureFin"));
					reps.add(r);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reps;
	}

}
