package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Organisateur;
import POJO.Reservation;

public class ReservationDAO implements DAO<Reservation> {
	
	//ATTRIBUT
	protected Connection connect = null;
	
	//CONSTRUCTEUR
	public ReservationDAO(Connection conn) {
		this.connect = conn;
	}

	//METHODES
	//Create
	public boolean create(Reservation obj) {
		try {
			String create = "INSERT INTO Reservation (Prix, DateRes, HeureDebut, HeureFin) "
			+ "values ('" + obj.getPrix() + "','" + obj.getDate() + "'" + obj.getHeureDebut() + "','" + obj.getHeureFin() + "');";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//delete
	public boolean delete(Reservation obj) {
		try {	
			String delete = "DELETE FROM Reservation where idReservation = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(Reservation obj) {
		try {
			String update = "UPDATE Reservation SET Prix = '" + obj.getPrix() +"' WHERE idReservation = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	public Reservation find(int id) {
		Reservation r = null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Reservation WHERE idReservation = '" + id + "');");
			if (result.first())
				 r = new Reservation(result.getInt("idReservation"), result.getInt("Prix"), result.getDate("DateRes"), result.getDate("HeureDebut"), result.getDate("HeureFin"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	//Ajouter  reservation avec idOrganisateur
	public boolean addReservation(Reservation obj, Organisateur o) {
		try {
				String create = "INSERT INTO Reservation (Prix, DateRes, HeureDebut, HeureFin, idOrganisateur, idSalle) "
						+ "values ('" + obj.getPrix() + "','" + obj.getDate() + "','" + obj.getHeureDebut() +  "','" + obj.getHeureFin() + "','" + o.getId() +  "','" + obj.getPlanningSalle().getId() + "');";
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return true;
	}


}
