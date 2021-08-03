package VIEW;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import POJO.Personne;

public class LoginJFrame extends JFrame {

	/**
	 * 
	 **/
	private static final long serialVersionUID = 1772298076221977128L;
	//ATTRIBUTS
	InscriptionJFrame inscription = new InscriptionJFrame();
	private JPanel contentPane;
	private JTextField Email;
	private JPasswordField Mdp;

	/**
	 * Launch the application.
	 */
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
	public LoginJFrame() {
		setResizable(false);
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
	
	
		//EMAIL
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(121, 39, 135, 13);
		contentPane.add(lblEmail);
		Email = new JTextField();
		Email.setBackground(Color.RED);
		Email.setBounds(110, 62, 198, 19);
		contentPane.add(Email);
		Email.setColumns(10);
			
		//MOT DE PASSE
		JLabel lblMdp = new JLabel("Mot de passe :");
		lblMdp.setForeground(Color.WHITE);
		lblMdp.setBounds(121, 120, 135, 13);
		contentPane.add(lblMdp);
		Mdp = new JPasswordField();
		Mdp.setBackground(Color.RED);
		Mdp.setBounds(110, 147, 198, 19);
		contentPane.add(Mdp);
			
		//BOUTTON LOGIN
		JButton btnLogin = new JButton("Se connecter");
		btnLogin.setForeground(Color.RED);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBounds(137, 197, 135, 35);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{								
				String email = Email.getText();
				@SuppressWarnings("deprecation")
				String mdp = Mdp.getText();
				Personne p = new Personne (email,mdp);			
					
				if (p.login() == 1) {
					JOptionPane.showMessageDialog(null, "Vous êtes connecté !");
					setVisible(false);
					p = p.findbyMail();
					Acceuil acceuilJFrame = new Acceuil(p);
					acceuilJFrame.setVisible(true);
				}
				else if(p.login() == 2){
					JOptionPane.showMessageDialog(null, "Erreur dans le mot de passe !");
				}
				else if(p.login() == 0) {
					JOptionPane.showMessageDialog(null, "Aucun compte n'existe avec cette adresse ! Inscrivez-vous.");
				}	
			}
		});	
			
			//BOUTTON INSCRIPTION
			JButton btnInscription = new JButton("S'inscrire");
			btnInscription.setBackground(Color.DARK_GRAY);
			btnInscription.setForeground(Color.RED);
			btnInscription.setBounds(324, 10, 102, 21);
			contentPane.add(btnInscription);
			btnInscription.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					inscription.setVisible(true);
				}
			});	
	}
}
