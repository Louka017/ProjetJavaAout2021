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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Categorie;
import POJO.Client;
import POJO.Commande;
import POJO.Configuration;
import POJO.Personne;
import POJO.Place;
import POJO.Representation;
import POJO.Spectacle;

public class ClientPlaceJFrame extends JFrame {
	private static final long serialVersionUID = -3379129441310621571L;
	private JPanel contentPane;
	private JButton btnValider;
	private JLabel lblObtenirPlace;
	private JRadioButton rdbtnNewRadioButton_5;
	private JRadioButton rdbtnNewRadioButton_6;
	private JRadioButton rdbtnNewRadioButton_7;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JRadioButton rdbtnNewRadioButton_8;
	private JRadioButton rdbtnNewRadioButton_9;
	private JRadioButton rdbtnNewRadioButton_10;
	private Configuration conf;
	private Categorie cat;
	private int error;
	private JTextField Libre;
	private JTextField Bronze;
	private JTextField Argent;
	private JTextField Or;
	private JTextField Diamant;
	private JLabel lblPrix;
	private JLabel lblPrix_1;
	private JLabel lblPrix_2;
	private JLabel lblPrix_3;
	private JLabel lblPrix_4;
	private JLabel lblVraiprix;
	private JLabel lblVraiprix_1;
	private JLabel lblVraiprix_2;
	private JLabel lblVraiprix_3;
	private JLabel lblVraiprix_4;
	private String strprixLibre ="0", strprixBronze ="0", strprixArgent ="0", strprixOr ="0", strprixDiamant ="0";
	private int nbrLibreInt = 0, nbrBronzeInt =0, nbrArgentInt=0, nbrOrInt=0, nbrDiamantInt=0, fraisDossier = 5;
	private JLabel lblEuro;
	private JLabel lblEuro_1;
	private JLabel lblEuro_2;
	private JLabel lblEuro_3;
	private JLabel lblEuro_4;
	String type ="";
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
	public ClientPlaceJFrame(Representation r, Client p, Spectacle s) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 370);
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
		
		//NOMBRES DE PLACES
		JLabel lblNewLabel = new JLabel("Nombres de places : ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(568, 25, 126, 13);
		contentPane.add(lblNewLabel);
		
		//LABEL PRIX
		lblPrix = new JLabel("Prix :");
		lblPrix.setForeground(Color.WHITE);
		lblPrix.setBackground(Color.WHITE);
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setBounds(852, 24, 35, 13);
		lblPrix_1 = new JLabel("Prix :");
		lblPrix_1.setForeground(Color.WHITE);
		lblPrix_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix_1.setBounds(852, 53, 45, 13);
		lblPrix_2 = new JLabel("Prix :");
		lblPrix_2.setForeground(Color.WHITE);
		lblPrix_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix_2.setBounds(852, 82, 45, 13);
		lblPrix_3 = new JLabel("Prix :");
		lblPrix_3.setForeground(Color.WHITE);
		lblPrix_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix_3.setBounds(852, 111, 45, 13);
		lblPrix_4 = new JLabel("Prix :");
		lblPrix_4.setForeground(Color.WHITE);
		lblPrix_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix_4.setBounds(852, 142, 45, 13);
		
		//SIGLE EURO
		lblEuro = new JLabel("\u20AC");
		lblEuro.setForeground(Color.WHITE);
		lblEuro.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro.setBounds(960, 24, 28, 13);
		lblEuro_1 = new JLabel("\u20AC");
		lblEuro_1.setForeground(Color.WHITE);
		lblEuro_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro_1.setBounds(943, 53, 45, 13);		
		lblEuro_2 = new JLabel("\u20AC");
		lblEuro_2.setForeground(Color.WHITE);
		lblEuro_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro_2.setBounds(943, 82, 45, 13);
		lblEuro_3 = new JLabel("\u20AC");
		lblEuro_3.setForeground(Color.WHITE);
		lblEuro_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro_3.setBounds(943, 111, 45, 13);
		lblEuro_4 = new JLabel("\u20AC");
		lblEuro_4.setForeground(Color.WHITE);
		lblEuro_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblEuro_4.setBounds(943, 142, 45, 13);
		
		//VRAI PRIX
		lblVraiprix = new JLabel("lol");
		lblVraiprix.setForeground(Color.WHITE);
		lblVraiprix.setHorizontalAlignment(SwingConstants.CENTER);
		lblVraiprix.setBounds(907, 24, 35, 13);
		lblVraiprix_1 = new JLabel("lol");
		lblVraiprix_1.setForeground(Color.WHITE);
		lblVraiprix_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVraiprix_1.setBounds(907, 53, 35, 13);	
		lblVraiprix_2 = new JLabel("lol");
		lblVraiprix_2.setForeground(Color.WHITE);
		lblVraiprix_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVraiprix_2.setBounds(907, 82, 35, 13);		
		lblVraiprix_3 = new JLabel("lol");
		lblVraiprix_3.setForeground(Color.WHITE);
		lblVraiprix_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblVraiprix_3.setBounds(907, 111, 35, 13);		
		lblVraiprix_4 = new JLabel("lol");
		lblVraiprix_4.setForeground(Color.WHITE);
		lblVraiprix_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblVraiprix_4.setBounds(907, 142, 35, 13);
		
		//LABELS
		JLabel lblLibre = new JLabel("Libre :");
		lblLibre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLibre.setForeground(Color.WHITE);
		lblLibre.setBounds(747, 24, 45, 13);
		Libre = new JTextField();
		Libre.setColumns(10);
		Libre.setBounds(802, 21, 35, 19);
		JLabel lblBronze = new JLabel("Bronze : ");
		lblBronze.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBronze.setForeground(Color.WHITE);
		lblBronze.setBounds(732, 53, 60, 13);
		Bronze = new JTextField();
		Bronze.setColumns(10);
		Bronze.setBounds(802, 50, 35, 19);
		JLabel lblArgent = new JLabel("Argent  : ");
		lblArgent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArgent.setForeground(Color.WHITE);
		lblArgent.setBounds(735, 82, 60, 13);
		Argent = new JTextField();
		Argent.setColumns(10);
		Argent.setBounds(802, 79, 35, 19);
		JLabel lblOr = new JLabel("Or : ");
		lblOr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOr.setForeground(Color.WHITE);
		lblOr.setBounds(757, 111, 35, 13);
		Or = new JTextField();
		Or.setColumns(10);
		Or.setBounds(802, 108, 35, 19);
		JLabel lblDiamant = new JLabel("Diamant : ");
		lblDiamant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiamant.setForeground(Color.WHITE);
		lblDiamant.setBounds(724, 140, 68, 17);
		Diamant = new JTextField();
		Diamant.setColumns(10);
		Diamant.setBounds(802, 139, 35, 19);
		
		//CREATION DE L'OBJET
		conf = Configuration.findBySpectacle(s);
		conf.setListeCategorie(Categorie.getAllCategoriesByRep(r));

		//AFFICHER PRIX
		for (Categorie i : conf.getListeCategorie()) 
		{
			if(i.getType().equals("Libre")) 
				strprixLibre = String.valueOf((i.getPrix()));

			if(i.getType().equals("Bronze")) 
				strprixBronze= String.valueOf((i.getPrix()));
	
			if(i.getType().equals("Argent")) 
				strprixArgent= String.valueOf((i.getPrix()));

			if(i.getType().equals("Or")) 
				strprixOr= String.valueOf((i.getPrix()));

			if(i.getType().equals("Diamant")) 
				strprixDiamant= String.valueOf((i.getPrix()));
		}
			

		//AFFICHAGE DES CONFIGURATION
		if(conf.getType().equals("Debout")) 
		{
			contentPane.add(lblLibre);
			contentPane.add(Libre);
			contentPane.add(lblPrix);
			lblVraiprix.setText(strprixLibre);
			contentPane.add(lblVraiprix);
			contentPane.add(lblEuro);
			
		}
		else if (conf.getType().equals("Concert")) 
		{
			contentPane.add(lblBronze);		
			contentPane.add(Bronze);
			contentPane.add(lblArgent);
			contentPane.add(Argent);
			contentPane.add(lblOr);
			contentPane.add(Or);
			contentPane.add(lblPrix_1);
			contentPane.add(lblPrix_2);
			contentPane.add(lblPrix_3);
			lblVraiprix_1.setText(strprixBronze);
			lblVraiprix_2.setText(strprixArgent);
			lblVraiprix_3.setText(strprixOr);
			contentPane.add(lblVraiprix_1);
			contentPane.add(lblVraiprix_2);
			contentPane.add(lblVraiprix_3);
			contentPane.add(lblEuro_1);
			contentPane.add(lblEuro_2);
			contentPane.add(lblEuro_3);
		}
		else
		{
			contentPane.add(lblBronze);		
			contentPane.add(Bronze);
			contentPane.add(lblArgent);
			contentPane.add(Argent);
			contentPane.add(lblOr);
			contentPane.add(Or);
			contentPane.add(lblDiamant);
			contentPane.add(Diamant);
			contentPane.add(lblPrix_1);
			contentPane.add(lblPrix_2);
			contentPane.add(lblPrix_3);
			contentPane.add(lblPrix_4);
			lblVraiprix_1.setText(strprixBronze);
			lblVraiprix_2.setText(strprixArgent);
			lblVraiprix_3.setText(strprixOr);
			lblVraiprix_4.setText(strprixDiamant);
			contentPane.add(lblVraiprix_1);
			contentPane.add(lblVraiprix_2);
			contentPane.add(lblVraiprix_3);
			contentPane.add(lblVraiprix_4);
			contentPane.add(lblEuro_1);
			contentPane.add(lblEuro_2);
			contentPane.add(lblEuro_3);
			contentPane.add(lblEuro_4);
		}
		
		//OBTENIR LES PLACES
		lblObtenirPlace = new JLabel("Moyen d'obtenir les places :");
		lblObtenirPlace.setForeground(Color.WHITE);
		lblObtenirPlace.setBounds(10, 93, 166, 13);
		contentPane.add(lblObtenirPlace);
		
		rdbtnNewRadioButton_5 = new JRadioButton("Prendre sur place le jour J");
		rdbtnNewRadioButton_5.setSelected(true);
		rdbtnNewRadioButton_5.setForeground(Color.BLACK);
		rdbtnNewRadioButton_5.setBounds(181, 89, 335, 21);
		contentPane.add(rdbtnNewRadioButton_5);
		
		rdbtnNewRadioButton_6 = new JRadioButton("Se faire livr\u00E9 \u00E0 domicile");
		rdbtnNewRadioButton_6.setBounds(182, 112, 368, 21);
		contentPane.add(rdbtnNewRadioButton_6);
			
		rdbtnNewRadioButton_7 = new JRadioButton("Se faire livr\u00E9 \u00E0 domicile de mani\u00E8re s\u00E9curis\u00E9e ( +10 \u20AC )");
		rdbtnNewRadioButton_7.setBounds(182, 136, 430, 21);
		contentPane.add(rdbtnNewRadioButton_7);
		
		//FRAIS DE DOSSIERS
		lblNewLabel_1 = new JLabel("+ 5 \u20AC de frais de dossier");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(21, 188, 144, 13);
		contentPane.add(lblNewLabel_1);
		
		//MODE DE PAYEMENT
		lblNewLabel_2 = new JLabel("Mode de payement : ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(21, 243, 126, 13);
		contentPane.add(lblNewLabel_2);
		
		rdbtnNewRadioButton_8 = new JRadioButton("Visa");
		rdbtnNewRadioButton_8.setSelected(true);
		rdbtnNewRadioButton_8.setBounds(146, 239, 103, 21);
		contentPane.add(rdbtnNewRadioButton_8);
			
		rdbtnNewRadioButton_9 = new JRadioButton("Paypal");
		rdbtnNewRadioButton_9.setBounds(146, 262, 103, 24);
		contentPane.add(rdbtnNewRadioButton_9);
			
		rdbtnNewRadioButton_10 = new JRadioButton("Virement SEPA (\u00E0 effectuer dans les 7 jours)");
		rdbtnNewRadioButton_10.setBounds(146, 288, 241, 21);
			
		//VERIFICATION POUR PAYEMENT SEPA ///////////////////////////////////////////////////////////////////////// A MODIFIER
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String test = dateFormat.format(date);
		try 
		{
			date =dateFormat.parse(test);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
			
		//DATE DU JOUR EN (DATE) ET (LONG)
		long dateday = date.getTime();
		Date dd = new java.sql.Date (dateday);
			
		//DATE REPRESENTATION EN (DATE) ET (LONG)
		long daterepres = r.getDate().getTime();
		Date datelimite = new java.sql.Date (daterepres - (79377416*21));
			
		//VERIFICATION
		if(dd.equals(datelimite)||dd.before(datelimite))
			contentPane.add(rdbtnNewRadioButton_10);
			
		//RADIO GROUP	
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnNewRadioButton_5);
		bgroup.add(rdbtnNewRadioButton_6);
		bgroup.add(rdbtnNewRadioButton_7);
		
		ButtonGroup bgroup2 = new ButtonGroup();
		bgroup2.add(rdbtnNewRadioButton_8);
		bgroup2.add(rdbtnNewRadioButton_9);
		bgroup2.add(rdbtnNewRadioButton_10);
		
		//BOUTTON VALIDER
		btnValider = new JButton("Payer");
		btnValider.setBounds(802, 243, 166, 66);
		contentPane.add(btnValider);
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{	

					error = 0;
					//RECUPERATION
					if(Libre.getText().isEmpty()) 
						nbrLibreInt = 0;
					else 
						nbrLibreInt = Integer.parseInt(Libre.getText());
																				
					if(Bronze.getText().isEmpty())
						nbrBronzeInt = 0;									
					else
						nbrBronzeInt = Integer.parseInt(Bronze.getText());
																				
					if(Argent.getText().isEmpty())
						nbrArgentInt =0;
					else
						nbrArgentInt = Integer.parseInt(Argent.getText());
						
					if(Or.getText().isEmpty())
						nbrOrInt = 0;
					else
						nbrOrInt = Integer.parseInt(Or.getText());
						
					if(Diamant.getText().isEmpty())
						nbrDiamantInt = 0;
					else
						nbrDiamantInt = Integer.parseInt(Diamant.getText());
					
					//VERIFICATION SI NOMBRE LIMITE PAR PLACE
					if( (nbrLibreInt > s.getNbrPlaceParClient()) || (nbrBronzeInt > s.getNbrPlaceParClient()) || (nbrArgentInt > s.getNbrPlaceParClient()) || (nbrOrInt > s.getNbrPlaceParClient()) || (nbrDiamantInt > s.getNbrPlaceParClient()) )
					{
						JOptionPane.showMessageDialog(null, "Nombre de place par client limité à " + s.getNbrPlaceParClient() + " place(s) !");
						error = 1;
					}
					
					//PRIX TOTAL
					int cout = ( (nbrLibreInt*Integer.parseInt(strprixLibre)) +(nbrBronzeInt*Integer.parseInt(strprixBronze)) + (nbrArgentInt*Integer.parseInt(strprixArgent)) + (nbrOrInt*Integer.parseInt(strprixOr)) + (nbrDiamantInt*Integer.parseInt(strprixDiamant)) + fraisDossier );
					
					//MOYEN D'OBTENIR LES PLACES
					String ModeLivraison = "";
					
					if(rdbtnNewRadioButton_5.isSelected()) {
						ModeLivraison = "Prendre sur place le jour J";
					}
					
					if(rdbtnNewRadioButton_6.isSelected()) {
						ModeLivraison = "Se faire livré à domicile";
					}
					
					if(rdbtnNewRadioButton_7.isSelected()) {
						ModeLivraison = "Se faire livré à domicile de maninière sécurisée";
						cout += 10;
					}
					
					//MODE DE PAYEMENT
					String ModePayement = "";
					
					if(rdbtnNewRadioButton_8.isSelected()) {
						ModePayement = "Visa";
					}
					
					if(rdbtnNewRadioButton_9.isSelected()) {
						ModePayement = "Paypal";
					}
					
					if(rdbtnNewRadioButton_10.isSelected()) {
						ModePayement = "Virement SEPA";
					}
					
	
					//VERIFICATION S' IL RESTE DES PLACES
					for (Categorie i : conf.getListeCategorie()) 
					{
						if(i.getType().equals("Libre"))
						{
							if(nbrLibreInt > i.getNbrPlaceDispo())
							{
								if(i.getNbrPlaceDispo() == 0 )
								{
									JOptionPane.showMessageDialog(null, "Plus aucune place de catégorie " + i.getType() + " disponible !");
									error = 1;	
								}
								else 
								{
									JOptionPane.showMessageDialog(null, "Il ne reste que " + i.getNbrPlaceDispo() + " place(s) disponible(s) de catégorie " + i.getType() + " !");
									error = 1;								
								}
							}
						}
						if(i.getType().equals("Bronze"))
						{
							if(nbrBronzeInt > i.getNbrPlaceDispo())
							{
								if(i.getNbrPlaceDispo() == 0 )
								{
									JOptionPane.showMessageDialog(null, "Plus aucune place de catégorie " + i.getType() + " disponible !");
									error = 1;	
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Il ne reste que " + i.getNbrPlaceDispo() + " place(s) disponible(s) de catégorie " + i.getType() + " !");
									error = 1;
								}	
							}
						}
						if(i.getType().equals("Argent"))
						{
							if(nbrArgentInt > i.getNbrPlaceDispo())
							{
								if(i.getNbrPlaceDispo() == 0 )
								{
									JOptionPane.showMessageDialog(null, "Plus aucune place de catégorie " + i.getType() + " disponible !");
									error = 1;	
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Il ne reste que " + i.getNbrPlaceDispo() + " place(s) disponible(s) de catégorie " + i.getType() + " !");
									error = 1;
								}
							}
						}
						if(i.getType().equals("Or"))
						{
							if(nbrOrInt > i.getNbrPlaceDispo())
							{
								if(i.getNbrPlaceDispo() == 0 )
								{
									JOptionPane.showMessageDialog(null, "Plus aucune place de catégorie " + i.getType() + " disponible !");
									error = 1;	
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Il ne reste que " + i.getNbrPlaceDispo() + " place(s) disponible(s) de catégorie " + i.getType() + " !");
									error = 1;
								}
							}
						}
						if(i.getType().equals("Diamant"))
						{
							if(nbrDiamantInt > i.getNbrPlaceDispo())
							{
								if(i.getNbrPlaceDispo() == 0 )
								{
									JOptionPane.showMessageDialog(null, "Plus aucune place de catégorie " + i.getType() + " disponible !");
									error = 1;	
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Il ne reste que " + i.getNbrPlaceDispo() + " place(s) disponible(s) de catégorie " + i.getType() + " !");
									error = 1;
								}
							}
						}
					}
					
					//VERIFICATION SI AJOUT
					if(error == 0) 
					{
						//COMMANDE
						Commande commande = new Commande(ModePayement,ModeLivraison, cout);
						
						//AJOUT DANS L'OBJET CLIENT
						p.addCommande(commande);
						
						//AJOUT DB
						commande.addCommande(p);
						commande = commande.find();	
						
						//PLACE
						for(int i=0; i<nbrLibreInt; i++ ) 
						{
							for(Categorie ca : conf.getListeCategorie()) 
							{
								if(ca.getType().equals("Libre")) 
								{
									ca.decrementerPlace();
									ca.update();
									cat = ca;
								}
							}
							//OBJET PLACE
							Place place = new Place(Integer.parseInt(strprixLibre),cat.getNbrPlaceDispo(),r );
							
							//AJOUT DANS OBJET COMMANDE
							commande.addPlace(place);
							
							//AJOUT DE LA PLACE
							place.addPlace();
							place = place.find();
							
							//AJOUT DANS TABLE DB PLACE_COMMANDE
							place.PlaceCommande(commande);
						}
						
						for(int i=0; i<nbrBronzeInt; i++ )
						{
							for(Categorie ca : conf.getListeCategorie()) 
							{
								if(ca.getType().equals("Bronze")) 
								{
									ca.decrementerPlace();
									ca.update();
									cat = ca;
								}
							}
							//OBJET PLACE
							Place place = new Place(Integer.parseInt(strprixBronze),cat.getNbrPlaceDispo(),r );
							
							//AJOUT DANS OBJET COMMANDE
							commande.addPlace(place);
							
							//AJOUT DE LA PLACE
							place.addPlace();
							place = place.find();
							
							//AJOUT DANS TABLE DB PLACE_COMMANDE
							place.PlaceCommande(commande);
						}
						
						for(int i=0; i<nbrArgentInt; i++ ) 
						{
							for(Categorie ca : conf.getListeCategorie()) 
							{
								if(ca.getType().equals("Argent")) 
								{
									ca.decrementerPlace();
									ca.update();
									cat = ca;
								}
							}
							//OBJET PLACE
							Place place = new Place(Integer.parseInt(strprixArgent),cat.getNbrPlaceDispo(),r);
							
							//AJOUT DANS OBJET COMMANDE
							commande.addPlace(place);
							
							//AJOUT DE LA PLACE
							place.addPlace();
							place = place.find();
							
							//AJOUT DANS TABLE DB PLACE_COMMANDE
							place.PlaceCommande(commande);
						}
						
						for(int i=0; i<nbrOrInt; i++ )
						{
							for(Categorie ca : conf.getListeCategorie()) 
							{
								if(ca.getType().equals("Or")) 
								{
									ca.decrementerPlace();
									ca.update();
									cat = ca;
								}
							}
							//OBJET PLACE
							Place place = new Place(Integer.parseInt(strprixOr),cat.getNbrPlaceDispo(),r );
							
							//AJOUT DANS OBJET COMMANDE
							commande.addPlace(place);
							
							//AJOUT DE LA PLACE
							place.addPlace();
							place = place.find();
							
							//AJOUT DANS TABLE DB PLACE_COMMANDE
							place.PlaceCommande(commande);
						}
						
						for(int i=0; i<nbrDiamantInt; i++ ) 
						{
							for(Categorie ca : conf.getListeCategorie()) 
							{
								if(ca.getType().equals("Diamant")) 
								{
									ca.decrementerPlace();
									ca.update();
									cat = ca;
								}
							}
							//OBJET PLACE
							Place place = new Place(Integer.parseInt(strprixDiamant),cat.getNbrPlaceDispo(),r );
							
							//AJOUT DANS OBJET COMMANDE
							commande.addPlace(place);
							
							//AJOUT DE LA PLACE
							place.addPlace();
							place = place.find();
							
							//AJOUT DANS TABLE DB PLACE_COMMANDE
							place.PlaceCommande(commande);
						}
						//SET DISCRIMINATOR
						p.setDiscriminator("Client");
						
						//CHANGEMENT FRAME
						JOptionPane.showMessageDialog(null,"Commande validée ! Prix total : " + cout + " €");
						setVisible(false);
						Acceuil acc = new Acceuil((Personne)p);
						acc.setVisible(true);
					}
					else
					{

					}

			}
		});

	}
}
