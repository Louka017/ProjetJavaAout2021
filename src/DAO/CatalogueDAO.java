package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import POJO.Catalogue;
import POJO.Spectacle;

public class CatalogueDAO implements DAO<Catalogue>{

	//ATTRIBUT
	private Connection connect = null;
	
	//CONSTRUCTEUR
	public CatalogueDAO(Connection conn) {
		this.connect = conn;
	}
	
	//METHODES
	//Create (non utilisé)
	public boolean create(Catalogue obj) {
		return false;
	}

	//Delete (non utilisé)
	public boolean delete(Catalogue obj) {
		return false;
	}

	//Uopdate (non utilisé)
	public boolean update(Catalogue obj) {
		return false;
	}

	//Find (non utilisé)
	public Catalogue find(int id) {
		return null;
	}

	//Liste des spectacles
	public List<Spectacle> listeSpectacles() {
		List<Spectacle> spectacles = new ArrayList<Spectacle>();
		try {

			String query = "SELECT * from Spectacle";
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
				while(result.next()) {
					Spectacle spectacle = new Spectacle();
					spectacle.setId(result.getInt("idSpectacle"));
					spectacle.setTitre(result.getString("Titre"));
					spectacle.setNbrPlaceParClient(result.getInt("NbrPlaceParClient"));
					spectacles.add(spectacle);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spectacles;
	}
}
