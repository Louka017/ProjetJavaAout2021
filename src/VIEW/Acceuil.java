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
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;


public class Acceuil extends JFrame {

	private static final long serialVersionUID = 3693941337194961838L;
	private JPanel contentPane;
	private JList<Spectacle> listeTousLesSpectacles;
	JMenuBar menuBar;
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
	public Acceuil(Personne p) 
	{
		setResizable(false);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 355);
		
		//JPANEL
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
		
		//MENU BAR AU DESSUS
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Que voulez-vous faire ?");
		menuBar.add(mnNewMenu);
		
		//LISTE DES SPECTACLES
		JLabel lblLstSpectacles = new JLabel("Liste des spectacles :");
		lblLstSpectacles.setForeground(Color.WHITE);
		lblLstSpectacles.setBounds(54, 10, 123, 13);
		contentPane.add(lblLstSpectacles);
		DefaultListModel<Spectacle> model = new DefaultListModel<>();
		model.addAll(Catalogue.getAllSpectacles());
		listeTousLesSpectacles = new JList<>(model);
		listeTousLesSpectacles.setBounds(21, 33, 166, 262);
		contentPane.add(listeTousLesSpectacles);   
		
		//BOUTON AJOUTER REPRESENTATION
		JButton btnRepresentation = new JButton("Ajouter une representation");
		btnRepresentation.setForeground(Color.RED);
		btnRepresentation.setBackground(Color.DARK_GRAY);
		btnRepresentation.setBounds(214, 63, 206, 28);
		btnRepresentation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{	
				Spectacle s = listeTousLesSpectacles.getSelectedValue();
				if(s != null) 
				{

					Organisateur personne = new Organisateur(p.getNom(), p.getPrenom(), p.getRue(), p.getNumero(),p.getVille(), p.getCp(),p.getEmail(),p.getPassword(),p.getId());
					RepresentationJFrame Rep = new RepresentationJFrame(s, personne, 0);
					setVisible(false);
					Rep.setVisible(true);   
				}
				else
				{
					try 
					{
						JOptionPane.showMessageDialog(null,"Erreur ! Veuillez choisir un spectacle !");
					}
					catch(Exception ee)
					{
					JOptionPane.showMessageDialog(null,"Erreur ! Veuillez choisir un spectacle !");	
					}
				}
			}
		});

		//BOUTTON ORGANISER SPECTACLE (RESERVER PLANNING SALLE)
		JButton btnOrganiserSpectacle = new JButton("Organiser un spectacle");
		btnOrganiserSpectacle.setForeground(Color.RED);
		btnOrganiserSpectacle.setBackground(Color.DARK_GRAY);
		btnOrganiserSpectacle.setBounds(214, 25, 206, 28);
		btnOrganiserSpectacle.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Organisateur personne = new Organisateur(p.getNom(), p.getPrenom(), p.getRue(), p.getNumero(),p.getVille(), p.getCp(),p.getEmail(),p.getPassword(),p.getId());
				ReservationSpectacleJFrame ResSpec = new ReservationSpectacleJFrame(personne);
				setVisible(false);
				ResSpec.setVisible(true); 
			}
		});
				
		//BOUTTON RESERVER UN SPECTACLE 
		JButton btnReservationSpectacle = new JButton("Rerserver spectacle");
		btnReservationSpectacle.setForeground(Color.RED);
		btnReservationSpectacle.setBackground(Color.DARK_GRAY);
		btnReservationSpectacle.setBounds(214, 101, 206, 28);
		btnReservationSpectacle.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
					Spectacle s = listeTousLesSpectacles.getSelectedValue();
					if(s != null) 
					{
						Client personne = new Client(p.getNom(), p.getPrenom(), p.getRue(), p.getNumero(),p.getVille(), p.getCp(),p.getEmail(),p.getPassword(),p.getId());	
						ClientReservationSpectacleJFrame CRes = new ClientReservationSpectacleJFrame(personne,s); 
						setVisible(false);
						CRes.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Erreur ! Veuillez choisir un spectacle !");	
					}
			}
		});
		
		//BOUTTON GESTIONNAIRE
		JButton btnGestionnaire = new JButton("Voir les plannings");
		btnGestionnaire.setForeground(Color.RED);
		btnGestionnaire.setBackground(Color.DARK_GRAY);
		btnGestionnaire.setBounds(214, 175, 206, 28);
		btnGestionnaire.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Gestionnaire personne = new Gestionnaire(p.getNom(), p.getPrenom(), p.getRue(), p.getNumero(),p.getVille(), p.getCp(),p.getEmail(),p.getPassword(),p.getId(), "Gestionnaire");
				GestionnaireJFrame frame = new GestionnaireJFrame(personne);
				setVisible(false);
				frame.setVisible(true);
			}
		});
		
		//ACCES ORGANISATEUR
		if(p.getDiscriminator().equals("Organisateur")) 
		{
			contentPane.add(btnRepresentation);
			contentPane.add(btnOrganiserSpectacle);
		}
		
		//ACCES GESTIONNAIRE
		if(p.getDiscriminator().equals("Gestionnaire"))
		{
			contentPane.add(btnRepresentation);
			contentPane.add(btnOrganiserSpectacle);
			contentPane.add(btnReservationSpectacle);
			contentPane.add(btnGestionnaire);
		}
		
		//ACCES CLIENT
		if(p.getDiscriminator().equals("Client")) 
		{
			contentPane.add(btnReservationSpectacle);
		}
	}

}


