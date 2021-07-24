package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import POJO.PlanningSalle;
import POJO.Spectacle;

public class PlanningSalleDAO implements DAO<PlanningSalle> {
	
	//ATTRIBUT
	protected Connection connect = null;
	
	//CONSTRUCTEUR
	public PlanningSalleDAO(Connection conn) {
		this.connect = conn;
	}

	//METHODES
	//Create
	public boolean create(PlanningSalle obj) {
		try {
			PreparedStatement ps= null;
			String create = "INSERT INTO PlanningSalle (DateDebut, DateFin) VALUES (?,?)";
			ps = connect.prepareStatement(create);
			connect.createStatement();		
			ps.setDate(1, (java.sql.Date) obj.getDateDebutSal());
			ps.setDate(2, (java.sql.Date)obj.getDateFinSal());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Delete
	public boolean delete(PlanningSalle obj) {
		try {	
			String delete = "DELETE FROM PlanningSalle where idPlanningSalle = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(PlanningSalle obj) {
		try {
			for(Spectacle s :obj.getListeSpectacle()) {
				String update = "UPDATE PlanningSalle SET idSpectacle = '" + s.getId() +"' WHERE idSalle = '" + obj.getId() + "';";
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	public PlanningSalle find(int id) {
		PlanningSalle p= null;
		try {
			ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Personne WHERE idSalle = '" + id + "');");
			if (result.first())
				p = new PlanningSalle(result.getInt("idSalle"), result.getDate("DateDebut"),result.getDate("DateFin"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	//Récupérer tous les plannings salle
	public List<PlanningSalle> getAllPlannings() {
		List<PlanningSalle> ps = new ArrayList<PlanningSalle>();
		try {
			String query = "SELECT * from PlanningSalle WHERE idSalle > 0";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					PlanningSalle planning = new PlanningSalle(result.getInt("idSalle"),result.getDate("DateDebut"),result.getDate("DateFin"));
					ps.add(planning);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	//Trouver un planning via la date de début + la date de fin
	public PlanningSalle findByDate(PlanningSalle s) {
		
		SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		Timestamp tDebut = new Timestamp(s.getDateDebutSal().getTime());
		Timestamp tFin = new Timestamp(s.getDateFinSal().getTime());
		tDebut.setNanos(00);
		tFin.setNanos(00);
		try {
			String query = "SELECT * from PlanningSalle where (DateDebut='" + localDateFormat.format(tDebut) +"') and (DateFin='" + localDateFormat.format(tFin) + "')";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
				.executeQuery(query);
			if(result.first())
				s = new PlanningSalle(result.getInt("idSalle"),result.getDate("DateDebut"), result.getDate("DateFin"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//Trouver un planning via la date de début + la date de fin  et du spectacle
	public PlanningSalle findBySpectacle (PlanningSalle s) {
		try {
			for(Spectacle spec : s.getListeSpectacle()){
					String query = "SELECT * from PlanningSalle where idSpectacle = '"+ spec.getId() +"';";
					ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery(query);
					if(result.next()) {
						s = new PlanningSalle(result.getInt("idSalle"),result.getTimestamp("DateDebut"), result.getTimestamp("DateFin"));
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	

}
