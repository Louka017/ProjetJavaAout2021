package DAO;

import java.sql.Connection;

import POJO.Gestionnaire;


public class GestionnaireDAO implements DAO<Gestionnaire> {
	
	//ATTRIBUT
	protected Connection connect = null;
	
	//CONSTRUCTEUR
	public GestionnaireDAO(Connection conn) {
		this.connect = conn;
	}

	//METHODES
	//Create (non utilis�)
	public boolean create(Gestionnaire obj) {
		return false;
	}

	//Delete (non utilis�)
	public boolean delete(Gestionnaire obj) {
		return false;
	}

	//Update (non utilis�)
	public boolean update(Gestionnaire obj) {
		return false;
	}

	//Find (non utilis�)
	public Gestionnaire find(int id) {
		return null;
	}


}
