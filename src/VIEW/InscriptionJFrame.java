package VIEW;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import POJO.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;

public class InscriptionJFrame extends JFrame {

 static final long serialVersionUID = 2538226678514688194L;
 
	//Attributs
	private JPanel contentPane;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField Email;
	private JTextField Rue;
	private JTextField Numero;
	private JTextField CodePostal;
	private JTextField Ville;
	private JPasswordField Mdp;
	
	/**
	 * Launch the application.
	 **/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJFrame frame = new LoginJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InscriptionJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 3827614812064042101L;
			public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(("theatre.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
          };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//NOM
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setForeground(Color.WHITE);
		lblNom.setBounds(10, 24, 45, 13);
		contentPane.add(lblNom);
		Nom = new JTextField();
		Nom.setBounds(54, 21, 111, 19);
		contentPane.add(Nom);
		Nom.setColumns(10);
		
		//PRENOM
		JLabel lblPrenom = new JLabel("Pr\u00E9nom : ");
		lblPrenom.setForeground(Color.WHITE);
		lblPrenom.setBounds(248, 24, 73, 13);
		contentPane.add(lblPrenom);
		Prenom = new JTextField();
		Prenom.setBounds(315, 21, 111, 19);
		contentPane.add(Prenom);
		Prenom.setColumns(10);
		
		//EMAIL
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(10, 67, 45, 13);
		contentPane.add(lblEmail);
		Email = new JTextField();
		Email.setBounds(54, 64, 153, 19);
		contentPane.add(Email);
		Email.setColumns(10);
		
		//MOT DE PASSE
		JLabel lblMdp = new JLabel("Mot de passe :");
		lblMdp.setForeground(Color.WHITE);
		lblMdp.setBounds(217, 67, 96, 13);
		contentPane.add(lblMdp);
		Mdp = new JPasswordField();
		Mdp.setBounds(320, 64, 106, 19);
		contentPane.add(Mdp);
		
		//RUE
		JLabel lblRue = new JLabel("Rue : ");
		lblRue.setForeground(Color.WHITE);
		lblRue.setBounds(10, 101, 66, 13);
		contentPane.add(lblRue);
		Rue = new JTextField();
		Rue.setBounds(54, 98, 111, 19);
		contentPane.add(Rue);
		Rue.setColumns(10);
		
		//NUMERO
		JLabel lblNumero = new JLabel("n\u00B0 : ");
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setBounds(281, 140, 40, 13);
		contentPane.add(lblNumero);
		Numero = new JTextField();
		Numero.setBounds(313, 137, 45, 19);
		contentPane.add(Numero);
		Numero.setColumns(10);
		
		//CODE POSTAL
		JLabel lblCp = new JLabel("Code Postal : ");
		lblCp.setForeground(Color.WHITE);
		lblCp.setBounds(224, 101, 89, 13);
		contentPane.add(lblCp);
		CodePostal = new JTextField();
		CodePostal.setBounds(315, 98, 111, 19);
		contentPane.add(CodePostal);
		CodePostal.setColumns(10);
		
		//VILLE
		JLabel lblVille = new JLabel("Ville :");
		lblVille.setForeground(Color.WHITE);
		lblVille.setBounds(10, 140, 45, 13);
		contentPane.add(lblVille);
		Ville = new JTextField();
		Ville.setBounds(54, 137, 111, 19);
		contentPane.add(Ville);
		Ville.setColumns(10);
		
		//CHOIX
		JLabel lblChoix = new JLabel("Vous \u00EAtes :");
		lblChoix.setForeground(Color.WHITE);
		lblChoix.setBounds(10, 184, 66, 13);
		contentPane.add(lblChoix);
		
			//CLIENT
			JRadioButton rdbtnClient = new JRadioButton("Client");
			rdbtnClient.setBackground(Color.WHITE);
			rdbtnClient.setSelected(true);
			rdbtnClient.setBounds(89, 172, 103, 31);
			contentPane.add(rdbtnClient);
			
			//ORGANISATEUR
			JRadioButton rdbtnOrganisateur = new JRadioButton("Organisateur");
			rdbtnOrganisateur.setBackground(Color.WHITE);
			rdbtnOrganisateur.setBounds(89, 203, 103, 21);
			contentPane.add(rdbtnOrganisateur);
			
			//ARTISTE
			JRadioButton rdbtnArtiste = new JRadioButton("Artiste");
			rdbtnArtiste.setBackground(Color.WHITE);
			rdbtnArtiste.setBounds(89, 220, 103, 37);
			contentPane.add(rdbtnArtiste);
			
		//CHOIX GROUPE	
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnClient);
		bgroup.add(rdbtnOrganisateur);
		bgroup.add(rdbtnArtiste);

			
		//BOUTTON INSCRIPTION
		JButton btnInscription = new JButton("S'inscrire");
		btnInscription.setBounds(237, 203, 164, 31);
		contentPane.add(btnInscription);
		btnInscription.addActionListener(new ActionListener() 
		{

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{		
				try 
				{
					if(rdbtnClient.isSelected())
					{
						Client c = new Client(Nom.getText(),Prenom.getText(),Rue.getText(),Integer.parseInt(Numero.getText()),Ville.getText(),Integer.parseInt(CodePostal.getText()),Email.getText(),Mdp.getText());
						if(c.regexVerification() == "correct")
						{	
							c.register();
							JOptionPane.showMessageDialog(null, "Client creer");
							setVisible(false);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, c.regexVerification());
						}
					}
					else if(rdbtnOrganisateur.isSelected() )
					{
						Organisateur o = new Organisateur(Nom.getText(),Prenom.getText(),Rue.getText(),Integer.parseInt(Numero.getText()),Ville.getText(),Integer.parseInt(CodePostal.getText()),Email.getText(),Mdp.getText());
						if(o.regexVerification() == "correct") 
						{	
							o.register();
							JOptionPane.showMessageDialog(null, "Organisateur creer");
							setVisible(false);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, o.regexVerification());
						}
					}
					else if(rdbtnArtiste.isSelected())
					{
						Artiste a = new Artiste(Nom.getText(),Prenom.getText(),Rue.getText(),Integer.parseInt(Numero.getText()),Ville.getText(),Integer.parseInt(CodePostal.getText()),Email.getText(),Mdp.getText());
						if(a.regexVerification() == "correct") 
						{	
							a.register();
							JOptionPane.showMessageDialog(null, "Artiste creer");
							setVisible(false);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, a.regexVerification());
						}
					}
				}
				catch (Exception ee) {
					JOptionPane.showMessageDialog(null,"Erreur, utilisateur non créer ! Veuillez saisir des données correctes !");	
				}
			}
		}); 	
	}
}
