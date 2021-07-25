package CONNECTION;

import java.sql.*;
import javax.swing.JOptionPane;


public class SpectacleConnection {

private static Connection instance = null;
	
	private SpectacleConnection(){//donc constr prive.
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://./DB_ProjetGaspariLoukaAout2021.accdb";
			instance = DriverManager.getConnection(url);//singleton
		}
		catch(ClassNotFoundException ex){
	JOptionPane.showMessageDialog(null, "Classe de driver introuvable" + ex.getMessage());
			System.exit(0);
		}
		catch (SQLException ex) {
	JOptionPane.showMessageDialog(null, "Erreur JDBC : " + ex.getMessage());
		}
		if (instance == null) {
            JOptionPane.showMessageDialog(null, "La base de données est inaccessible, fermeture du programme.");
            System.exit(0);
        }
	}
	
	public static Connection getInstance() {//methode pour retourner le singleton
		if(instance == null){
			new SpectacleConnection();
		}
		return instance;
	}
	
}