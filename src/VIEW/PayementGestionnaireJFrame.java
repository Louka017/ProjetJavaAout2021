package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Organisateur;
import POJO.PlanningSalle;
import POJO.Reservation;

public class PayementGestionnaireJFrame extends JFrame {

	private static final long serialVersionUID = -3108378940868530807L;
	private JPanel contentPane;
	Reservation reservation = new Reservation();

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
	public PayementGestionnaireJFrame(PlanningSalle ps, Organisateur o) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel() 
		{
			private static final long serialVersionUID = 3827614812064042101L;
			public void paintComponent(Graphics g) 
			{
                Image img = Toolkit.getDefaultToolkit().getImage(("theatre.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
           }
        };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//LABEL EURO
		JLabel lblEuro = new JLabel("\u20AC");
		lblEuro.setForeground(Color.WHITE);
		lblEuro.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEuro.setBounds(236, 91, 20, 40);
		contentPane.add(lblEuro);
		
		//MONTANT
		JLabel lblMontant = new JLabel("Prix de location : ");
		lblMontant.setForeground(Color.LIGHT_GRAY);
		lblMontant.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontant.setBounds(0, 0, 436, 55);
		contentPane.add(lblMontant);
		JLabel lblPrix = new JLabel("New label");
		lblPrix.setForeground(Color.WHITE);
		lblPrix.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPrix.setBounds(26, 90, 205, 40);
		contentPane.add(lblPrix);

		//AJOUTE LE PLANNING A L'OBJET RESERVATION
		reservation.setPlanningSalle(ps);
		
		//CALCUL PRIX 
		reservation.calclulatePrice();
		
		//AFFICHAGE DU RIX
		lblPrix.setText(String.valueOf(reservation.getPrix()));
		
		//VALIDER
		JButton btnValider = new JButton("PAYER");
		btnValider.setBounds(151, 188, 120, 40);
		contentPane.add(btnValider);
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				//INSERE LES VALEURS DANS L'OBJET	
				reservation.setDate(reservation.getPlanningSalle().getDateDebutSal());
				reservation.setHeureDebut(reservation.getPlanningSalle().getDateDebutSal());
				reservation.setHeureFin(reservation.getPlanningSalle().getDateFinSal());
				
				//AJOUTE L'OBJET RESERVATION A L'OBJET ORGANISATEUR
				o.addReservation(reservation);
				
				//AJOUT DE LA RESERVATION
				boolean ajout = reservation.addReservation(o);
				if(ajout) 
				{
					//FRAME SUIVANTE
					JOptionPane.showMessageDialog(null, "Payement valider");
					setVisible(false);
					CreationSpectacleJFrame CreSpec = new CreationSpectacleJFrame(ps, o);
					CreSpec.setVisible(true);	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Erreur Réservation non effectuée.");
				}	
			}
		});
	}
}