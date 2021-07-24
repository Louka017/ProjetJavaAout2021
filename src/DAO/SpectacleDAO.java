package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.Artiste;
import POJO.Spectacle;

public class SpectacleDAO implements DAO<Spectacle> {
	
	//ATTRIBUT
	protected Connection connect = null;
	
	//CONSTRUCTEUR
	public SpectacleDAO(Connection conn) {
		this.connect=conn;
	}

	//METHODES
	//Create
	public boolean create(Spectacle obj) {
		try {
				String create = "INSERT INTO Spectacle (Titre, NbrPlaceParClient) "
				+ "values ('" + obj.getTitre() + "','" + obj.getNbrPlaceParClient() + "');";
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Delete
	public boolean delete(Spectacle obj) {
		try {	
			String delete = "DELETE FROM Spectacle where idSpectacle = " + obj.getId() + ";";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(delete);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Update
	public boolean update(Spectacle obj) {
		try {
			String update = "UPDATE Spectacle SET Titre = '" + obj.getTitre() +"' WHERE idSpectacle = '" + obj.getId() + "';";
			connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(update);				
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Find
	@SuppressWarnings("null")
	public Spectacle find(int id) {
		Spectacle s = null;
		try {
				ResultSet result = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT * FROM SPECTACLE WHERE idSpectacle = '" + id + "');");
				if (result.first()) {
					s.setId(result.getInt("idSpectacle"));
					s.setTitre(result.getString("Titre"));
					s.setNbrPlaceParClient(result.getInt("NbrPlaceParClient"));
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	//Trouver spectacle grâce à son titre
	public Spectacle findByTitle(Spectacle obj) {
		try {
				ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
				.executeQuery("SELECT * FROM Spectacle WHERE Titre = '" + obj.getTitre() + "';");
				if (result.first()) {
					obj.setId(result.getInt("idSpectacle"));
					obj.setTitre(result.getString("Titre"));
					obj.setNbrPlaceParClient(result.getInt("NbrPlaceParClient"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return obj;
	}

	//Ajouter un artiste à un spectacle
	public boolean addArtistesSpectacle(Spectacle obj) {
		try {
			for(Artiste artiste : obj.getListeArtiste()){
				String create = "INSERT INTO Personne_Spectacle (idPersonne, idSpectacle) "
								+ "values ('" + artiste.getId() + "','" + obj.getId() + "');";
				connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeUpdate(create);				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		return true;
	}
	
	
}
