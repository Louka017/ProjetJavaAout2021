package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import com.toedter.calendar.JDateChooser;

import POJO.Organisateur;
import POJO.PlanningSalle;

public class ReservationSpectacleJFrame extends JFrame {

	//ATTRIBUTS
	private static final long serialVersionUID = 224229295309924343L;
	String strDateDebut, strDateFin, goodDateDebut, goodDateFin;
	private JList<PlanningSalle> listeTousLesPlannings;
	private JPanel contentPane;
	boolean salleDisponible ;
	Date correctDateDebut;
	Date dateDebut = null;
	Date dateeFin = null;
	Date correctDateFin;
	Date VraiDateDebut;
	Date VraiDateFin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
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
	public ReservationSpectacleJFrame(Organisateur p) 
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
		
		
		//LABEL DATES NON DISPONIBLEs
		JLabel lblDisponibilite = new JLabel("Dates non disponibles pour louer la salle :");
		lblDisponibilite.setForeground(Color.WHITE);
		lblDisponibilite.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponibilite.setBounds(0, 25, 251, 13);
		contentPane.add(lblDisponibilite);
		
		//LISTE
		DefaultListModel<PlanningSalle> model = new DefaultListModel<>();
		model.addAll(PlanningSalle.getAllPlanningSalle());
		listeTousLesPlannings = new JList<>(model);
		listeTousLesPlannings.setBounds(10, 44, 235, 209);
		contentPane.add(listeTousLesPlannings);
		
		//DATE DE DEBUT
		JDateChooser dateDeb = new JDateChooser();
		dateDeb.setBounds(298, 45, 116, 19);
		contentPane.add(dateDeb);
		JLabel lblDateDebut = new JLabel("Date de d\u00E9but :");
		lblDateDebut.setForeground(Color.WHITE);
		lblDateDebut.setBounds(298, 25, 126, 13);
		contentPane.add(lblDateDebut);
		
		//DATE DE FIN
		JLabel lblDateFin = new JLabel("Date de fin :");
		lblDateFin.setForeground(Color.WHITE);
		lblDateFin.setBounds(298, 96, 83, 13);
		contentPane.add(lblDateFin);
		JDateChooser dateFin = new JDateChooser();
		dateFin.setBounds(298, 119, 116, 19);
		contentPane.add(dateFin);
		
		//VALIDATION
		JButton BtnValider = new JButton("Valider");
		BtnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					//FORMAT DATE
					DateFormat dateFormatCourt = new SimpleDateFormat("yyyy-MM-dd"); 
					DateFormat dateFormatLongg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
					//RECUPERATION
					dateDebut = dateDeb.getDate();
					dateeFin  = dateFin.getDate();
				
					//TRANSFORME EN HEURE CORRECTE LA RECUPERATION
					String heureDebFin = "12:00:00";
					
					//AJOUTE L'HEURE DE LA REPRESENTATION AU JOUR
					strDateDebut = dateFormatCourt.format(dateDebut);	
					strDateDebut = strDateDebut + " " + heureDebFin;
					strDateFin   = dateFormatCourt.format(dateeFin);	
					strDateFin  = strDateFin + " " + heureDebFin;
					
					//PLANNING SALLE
					PlanningSalle planningSalle = new PlanningSalle(dateFormatLongg.parse(strDateDebut),dateFormatLongg.parse(strDateFin));
	
					//MODIFICATION POUR UN AJOUT CORRECT EN DB
					long db = planningSalle.getDateDebutSal().getTime();
					long df = planningSalle.getDateFinSal().getTime();
					Date datedeb = new java.sql.Date (db);
					Date datefin = new java.sql.Date (df);
								
					//REMET LES VALEURS DANS UN FORMAT CORRECT
					planningSalle = new PlanningSalle((java.sql.Date)datedeb,(java.sql.Date)datefin);
	
					if( planningSalle.getDateDebutSal().before(planningSalle.getDateFinSal()) )
					{
						//REGARDE SI LA SALLE EST DEJA LOUER
						salleDisponible = planningSalle.verifyDatePlanningSalle();

						if (salleDisponible == true) 
						{
							//AJOUTE LE PLANNING
							planningSalle.addPlanningSalle(); 
							planningSalle = planningSalle.finfByDate();
							
							//FRAME SUIVANTE
							PayementGestionnaireJFrame modaliteSalle = new PayementGestionnaireJFrame(planningSalle, p);
							setVisible(false);
							modaliteSalle.setVisible(true);	 
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Réservation annulée! La salle est déjà loué à cette date là.");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Réservation annulée! Saisissez une date de début inférieur à la date de fin.");
					}
				
				}
				catch(Exception eee) 
				{
					
				}
		}
	});
	BtnValider.setBounds(329, 209, 85, 21);
	contentPane.add(BtnValider);
  }
}
