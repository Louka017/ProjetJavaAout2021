package VIEW;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import POJO.*;

public class CreationSpectacleJFrame extends JFrame {

	/**
	 * 
	 **/
	private static final long serialVersionUID = -3627746851033550424L;
	//ATTRIBUTS
	private int placeLibre,placeBronze,placeArgent, placeOr, placeDiamant;
	private int  prixLibre, prixBronze, prixArgent, prixOr, prixDiamant;
	Configuration configuration ;
	Categorie cat;
	Spectacle spectacle = Spectacle.getInstance();
	private boolean visible = false;
	private JTextField NomSpectacle;
	private JTextField NbrParClient;
	private JTextField Diamant2;
	private JPanel contentPane;
	private JTextField Diamant;
	private JTextField Bronze2;
	private JTextField Argent2;
	private JTextField Libre2;
	private JTextField Bronze;
	private JTextField Argent;
	private JTextField Libre;
	private JTextField Or2;
	private JTextField Or;
	private int error = 0;
	private JList<Artiste> listeChoisit;
	private JList<Artiste> listeToutLesArtistes;
	private List<Artiste> artistes= new ArrayList<>();
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
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreationSpectacleJFrame(PlanningSalle ps, Organisateur p)
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 452);
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
		
		//NOM SPECTACLE
		JLabel lblNomSpectacle = new JLabel("Nom du spectacle : ");
		lblNomSpectacle.setBackground(Color.RED);
		lblNomSpectacle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomSpectacle.setForeground(Color.LIGHT_GRAY);
		lblNomSpectacle.setBounds(186, 10, 331, 13);
		contentPane.add(lblNomSpectacle);
		NomSpectacle = new JTextField();
		NomSpectacle.setColumns(10);
		NomSpectacle.setBounds(246, 33, 210, 21);
		contentPane.add(NomSpectacle);
		
		//ARTISTES DISPONIBLES
		JLabel lblArtistesPresent = new JLabel("Liste d'artistes :");
		lblArtistesPresent.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistesPresent.setForeground(Color.LIGHT_GRAY);
		lblArtistesPresent.setBounds(57, 76, 103, 13);
		contentPane.add(lblArtistesPresent);
		DefaultListModel<Artiste> model = new DefaultListModel<>();
		model.addAll(Artiste.getAllArtistes());
		listeToutLesArtistes = new JList<>(model);
		listeToutLesArtistes.setBounds(57, 99, 103, 95);
		contentPane.add(listeToutLesArtistes);
		
		//ARTISTES CHOISIS
		JLabel lblChoixArtistes = new JLabel("Artistes choisi :");
		lblChoixArtistes.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixArtistes.setForeground(Color.LIGHT_GRAY);
		lblChoixArtistes.setBounds(295, 76, 103, 13);
		contentPane.add(lblChoixArtistes);
		DefaultListModel<Artiste> ArtistesSelectionner = new DefaultListModel<>();
		listeChoisit = new JList<>(ArtistesSelectionner);
		listeChoisit.setBounds(295, 99, 103, 88);
		contentPane.add(listeChoisit);
		
		//CHOSIR -->
		JButton btnChoisir = new JButton("Choisir >>");
		btnChoisir.setForeground(Color.GREEN);
		btnChoisir.setBackground(Color.GRAY);
		btnChoisir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (!ArtistesSelectionner.contains(listeToutLesArtistes.getSelectedValue()))
				{
					ArtistesSelectionner.addElement(listeToutLesArtistes.getSelectedValue());
					artistes.add((Artiste)listeToutLesArtistes.getSelectedValue());
				}
			}
		});
		btnChoisir.setBounds(170, 119, 115, 21);
		contentPane.add(btnChoisir);	
		
		//RETIRER <--
		JButton btnRetirer = new JButton("<< Retirer");
		btnRetirer.setBackground(Color.GRAY);
		btnRetirer.setForeground(Color.RED);
		btnRetirer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				artistes.remove((Artiste)listeChoisit.getSelectedValue());
				ArtistesSelectionner.removeElement(listeChoisit.getSelectedValue());
			}
		});
		btnRetirer.setBounds(170, 150, 115, 21);
		contentPane.add(btnRetirer);
		
		//CONFIGURATION
		JLabel lblConfiguration = new JLabel("Configuration:");
		lblConfiguration.setForeground(Color.LIGHT_GRAY);
		lblConfiguration.setBounds(526, 85, 116, 13);
		contentPane.add(lblConfiguration);
		
		//CHOIX
		JRadioButton RadioDebout = new JRadioButton("Debout");
		RadioDebout.setBounds(526, 104, 103, 26);
		contentPane.add(RadioDebout);
			
		JRadioButton RadioConcert = new JRadioButton("Concert");
		RadioConcert.setBounds(526, 127, 103, 26);
		contentPane.add(RadioConcert);
		
		JRadioButton RadioCirque = new JRadioButton("Cirque");
		RadioCirque.setBounds(526, 150, 103, 21);
		contentPane.add(RadioCirque);
		
		//SELON CHOIX
		JLabel lblBronze = new JLabel("Bronze : ");
		lblBronze.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBronze.setForeground(Color.WHITE);
		lblBronze.setBounds(533, 272, 60, 13);
		contentPane.add(lblBronze);
		Bronze = new JTextField();
		Bronze.setBounds(603, 269, 35, 19);
		contentPane.add(Bronze);
		Bronze.setColumns(10);		
		lblBronze.setVisible(false);
		Bronze.setVisible(false);
				
		JLabel lblArgent = new JLabel("Argent  : ");
		lblArgent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArgent.setForeground(Color.WHITE);
		lblArgent.setBounds(533, 295, 60, 13);
		contentPane.add(lblArgent);
		Argent = new JTextField();
		Argent.setBounds(603, 292, 35, 19);
		contentPane.add(Argent);
		Argent.setColumns(10);
		lblArgent.setVisible(false);
		Argent.setVisible(false);
				
		JLabel lblOr = new JLabel("Or : ");
		lblOr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOr.setForeground(Color.WHITE);
		lblOr.setBounds(558, 321, 35, 13);
		contentPane.add(lblOr);
		Or = new JTextField();
		Or.setBounds(603, 318, 35, 19);
		contentPane.add(Or);
		Or.setColumns(10);
		lblOr.setVisible(false);
		Or.setVisible(false);
			
		JLabel lblDiamant = new JLabel("Diamant : ");
		lblDiamant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiamant.setForeground(Color.WHITE);
		lblDiamant.setBounds(525, 348, 68, 17);
		contentPane.add(lblDiamant);
		Diamant = new JTextField();
		Diamant.setBounds(603, 347, 35, 19);
		contentPane.add(Diamant);
		Diamant.setColumns(10);
		lblDiamant.setVisible(false);
		Diamant.setVisible(false);
			
		JLabel lblLibre = new JLabel("Libre :");
		lblLibre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLibre.setForeground(Color.WHITE);
		lblLibre.setBounds(548, 249, 45, 13);
		contentPane.add(lblLibre);
		Libre = new JTextField();
		Libre.setBounds(603, 246, 35, 19);
		contentPane.add(Libre);
		Libre.setColumns(10);
		lblLibre.setVisible(false);
		Libre.setVisible(false);
		
		//RADIO GROUP CHOIX
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(RadioDebout);
		bgroup.add(RadioConcert);
		bgroup.add(RadioCirque);
		
		//PRIX DES PLACES
		JLabel lblPrix = new JLabel("Prix des places :");
		lblPrix.setForeground(Color.LIGHT_GRAY);
		lblPrix.setBounds(543, 226, 121, 13);
		contentPane.add(lblPrix);
		
		//SYMBOLES EURO
		JLabel lblEuro = new JLabel("\u20AC");
		lblEuro.setVisible(false);
		lblEuro.setForeground(Color.WHITE);
		lblEuro.setBounds(648, 249, 16, 13);
		contentPane.add(lblEuro);
		//SYMBOLES EURO
		JLabel lblEuro1 = new JLabel("\u20AC");
		lblEuro1.setVisible(false);
		lblEuro1.setForeground(Color.WHITE);
		lblEuro1.setBounds(648, 272, 16, 13);
		contentPane.add(lblEuro1);
		//SYMBOLES EURO
		JLabel lblEuro2 = new JLabel("\u20AC");
		lblEuro2.setVisible(false);
		lblEuro2.setForeground(Color.WHITE);
		lblEuro2.setBounds(648, 295, 16, 13);
		contentPane.add(lblEuro2);
		//SYMBOLES EURO
		JLabel lblEuro3 = new JLabel("\u20AC");
		lblEuro3.setVisible(false);
		lblEuro3.setForeground(Color.WHITE);
		lblEuro3.setBounds(648, 320, 16, 15);
		contentPane.add(lblEuro3);
		//SYMBOLES EURO
		JLabel lblEuro4 = new JLabel("\u20AC");
		lblEuro4.setVisible(false);
		lblEuro4.setForeground(Color.WHITE);
		lblEuro4.setBounds(648, 349, 16, 13);
		contentPane.add(lblEuro4);
		
		//NOMBRE DE PLACES
		JLabel lblNbrPlace = new JLabel("Nombre de places :");
		lblNbrPlace.setBackground(Color.WHITE);
		lblNbrPlace.setForeground(Color.LIGHT_GRAY);
		lblNbrPlace.setBounds(20, 226, 121, 13);
		contentPane.add(lblNbrPlace);
		//LABEL BRONZE
		JLabel lblBronze_1 = new JLabel("Bronze : ");
		lblBronze_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBronze_1.setForeground(Color.WHITE);
		lblBronze_1.setBounds(20, 272, 60, 13);
		contentPane.add(lblBronze_1);
		Bronze2 = new JTextField();
		Bronze2.setColumns(10);
		Bronze2.setBounds(90, 269, 35, 19);
		contentPane.add(Bronze2);
		lblBronze_1.setVisible(false);
		Bronze2.setVisible(false);
		//LABEL ARGENT
		JLabel lblArgent_1 = new JLabel("Argent  : ");
		lblArgent_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArgent_1.setForeground(Color.WHITE);
		lblArgent_1.setBounds(20, 295, 60, 13);
		contentPane.add(lblArgent_1);
		Argent2 = new JTextField();
		Argent2.setColumns(10);
		Argent2.setBounds(90, 292, 35, 19);
		contentPane.add(Argent2);
		lblArgent_1.setVisible(false);
		Argent2.setVisible(false);
		//LABEL OR
		JLabel lblOr_1 = new JLabel("Or : ");
		lblOr_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOr_1.setForeground(Color.WHITE);
		lblOr_1.setBounds(45, 321, 35, 13);
		contentPane.add(lblOr_1);
		Or2 = new JTextField();
		Or2.setColumns(10);
		Or2.setBounds(90, 318, 35, 19);
		contentPane.add(Or2);
		lblOr_1.setVisible(false);
		Or2.setVisible(false);
		//LABEL DIAMANT
		JLabel lblDiamant_1 = new JLabel("Diamant : ");
		lblDiamant_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiamant_1.setForeground(Color.WHITE);
		lblDiamant_1.setBounds(12, 348, 68, 17);
		contentPane.add(lblDiamant_1);
		Diamant2 = new JTextField();
		Diamant2.setColumns(10);
		Diamant2.setBounds(90, 347, 35, 19);
		contentPane.add(Diamant2);
		lblDiamant_1.setVisible(false);
		Diamant2.setVisible(false);
		//LABEL LIBRE
		JLabel lblLibre_1 = new JLabel("Libre :");
		lblLibre_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLibre_1.setForeground(Color.WHITE);
		lblLibre_1.setBounds(35, 249, 45, 13);
		contentPane.add(lblLibre_1);
		Libre2 = new JTextField();
		Libre2.setColumns(10);
		Libre2.setBounds(90, 246, 35, 19);
		contentPane.add(Libre2);
		lblLibre_1.setVisible(false);
		Libre2.setVisible(false);
	
		//CONFIGURATION CHOISIE
		//debout
		RadioDebout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource() == RadioDebout) 
				{
					lblBronze.setVisible(false);
					Bronze.setVisible(false);
					lblBronze_1.setVisible(false);
					Bronze2.setVisible(false);
					lblArgent.setVisible(false);
					Argent.setVisible(false);
					lblArgent_1.setVisible(false);
					Argent2.setVisible(false);
					lblOr.setVisible(false);
					Or.setVisible(false);
					lblOr_1.setVisible(false);
					Or2.setVisible(false);
					lblDiamant.setVisible(false);
					Diamant.setVisible(false);
					lblDiamant_1.setVisible(false);
					Diamant2.setVisible(false);
					lblEuro1.setVisible(false);
					lblEuro2.setVisible(false);
					lblEuro3.setVisible(false);
					lblEuro4.setVisible(false);
					lblEuro.setVisible(true);
					lblLibre.setVisible(true);
					Libre.setVisible(true);
					lblLibre_1.setVisible(true);
					Libre2.setVisible(true);
					configuration = new Configuration("Debout");
				}
			}
		});
			
		//concert
		RadioConcert.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource() == RadioConcert) 
				{
					lblEuro.setVisible(false);
					lblEuro4.setVisible(false);
					lblLibre.setVisible(false);
					Libre.setVisible(false);
					lblLibre_1.setVisible(false);
					Libre2.setVisible(false);
					lblDiamant.setVisible(false);
					Diamant.setVisible(false);
					lblDiamant_1.setVisible(false);
					Diamant2.setVisible(false);
					lblBronze.setVisible(true);
					Bronze.setVisible(true);
					lblBronze_1.setVisible(true);
					Bronze2.setVisible(true);
					lblArgent.setVisible(true);
					Argent.setVisible(true);
					lblArgent_1.setVisible(true);
					Argent2.setVisible(true);
					lblOr.setVisible(true);
					Or.setVisible(true);
					lblOr_1.setVisible(true);
					Or2.setVisible(true);
					lblEuro1.setVisible(true);
					lblEuro2.setVisible(true);
					lblEuro3.setVisible(true);
					configuration = new Configuration("Concert");
				}
			}
		});
			
		//cirque
		RadioCirque.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == RadioCirque)
				{
					lblEuro.setVisible(false);
					lblLibre.setVisible(false);
					Libre.setVisible(false);
					lblLibre_1.setVisible(false);
					Libre2.setVisible(false);
					lblBronze.setVisible(true);
					Bronze.setVisible(true);
					lblBronze_1.setVisible(true);
					Bronze2.setVisible(true);
					lblArgent.setVisible(true);
					Argent.setVisible(true);
					lblArgent_1.setVisible(true);
					Argent2.setVisible(true);
					lblOr.setVisible(true);
					Or.setVisible(true);
					lblOr_1.setVisible(true);
					Or2.setVisible(true);
					lblDiamant.setVisible(true);
					Diamant.setVisible(true);
					lblDiamant_1.setVisible(true);
					Diamant2.setVisible(true);
					lblEuro1.setVisible(true);
					lblEuro2.setVisible(true);
					lblEuro3.setVisible(true);
					lblEuro4.setVisible(true);
					configuration = new Configuration("Cirque");
				}	
			}
		});
		
			
		//NbrParClient
		JCheckBox chckbxNewCheckBox = new JCheckBox("Nombre Limite par client");
		chckbxNewCheckBox.setBounds(526, 6, 171, 21);
		contentPane.add(chckbxNewCheckBox);
		NbrParClient = new JTextField();
		NbrParClient.setBounds(568, 34, 45, 19);
		NbrParClient.setColumns(10);
		contentPane.add(NbrParClient);
		NbrParClient.setVisible(visible);
		chckbxNewCheckBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getSource() == chckbxNewCheckBox) 
				{
					if(visible==false)
					{
						visible=true;
						NbrParClient.setVisible(visible);
					}
					else
					{
						visible=false;
						NbrParClient.setVisible(visible);
					}
				}
			}
		});
					
		//BOUTON VALIDER
		JButton btnValider = new JButton("VALIDER");
		btnValider.setForeground(Color.RED);
		btnValider.setBackground(Color.DARK_GRAY);
		btnValider.setBounds(281, 295, 160, 108);
		contentPane.add(btnValider);
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					//VERIFICATION TITRE
					String SpecatcleName = NomSpectacle.getText(); 
					if(SpecatcleName.equals("")) 
					{
						JOptionPane.showMessageDialog(null, "Erreur! Spectacle non ajouter ");	
						return ;
					}
					
					//SET DANS L'OBJET SPECTACLE
					spectacle.setTitre(NomSpectacle.getText());
					spectacle.setListeArtiste(artistes);
							
					//PLACES
					if(Libre2.getText().isEmpty()) 
						placeLibre = 0;
					else 
						placeLibre = Integer.parseInt(Libre2.getText());
																				
					if(Bronze2.getText().isEmpty())
						placeBronze = 0;									
					else
						placeBronze = Integer.parseInt(Bronze2.getText());
																					
					if(Argent2.getText().isEmpty())
						placeArgent =0;
					else
						placeArgent = Integer.parseInt(Argent2.getText());
							
					if(Or2.getText().isEmpty())
						placeOr = 0;
					else
						placeOr = Integer.parseInt(Or2.getText());
							
					if(Diamant2.getText().isEmpty())
						placeDiamant = 0;
					else
						placeDiamant = Integer.parseInt(Diamant2.getText());
					
					//TOTAL DES PLACES
					int placeTotal = placeLibre + placeBronze + placeArgent + placeOr + placeDiamant;
						
					//PRIX
					if(Libre.getText().isEmpty()) 
						prixLibre = 0;
					else
						prixLibre = Integer.parseInt(Libre.getText());
						
					if(Bronze2.getText().isEmpty())
						prixBronze = 0;									
					else
						prixBronze = Integer.parseInt(Bronze.getText());
						
					if(Argent.getText().isEmpty())
						prixArgent =0;
					else
						prixArgent = Integer.parseInt(Argent.getText());
							
					if(Or.getText().isEmpty())
						prixOr = 0;
					else
						prixOr = Integer.parseInt(Or.getText());
							
					if(Diamant.getText().isEmpty())
						prixDiamant = 0;
					else
						prixDiamant = Integer.parseInt(Diamant.getText());			
						
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
					//DEBOUT
					if(configuration.getType().equals("Debout")) 
					{
						//AJOUT CONFIGURATION											
						configuration.addConfiguration();
						configuration = configuration.find();
						
						if(Categorie.verifyDebout(placeLibre) == true) 
						{	
								
							//SI NOMBRE DE PLACE PAR CLIENT MAXIMUM
							if(chckbxNewCheckBox.isSelected()) 
							{
								//SET LE NOMBRE DE PLACE
								String stringNombreParClient = NbrParClient.getText();
								int nombreParClient = Integer.parseInt(stringNombreParClient);
								spectacle.setNbrPlaceParClient(nombreParClient);
							}
							else 
							{
								// NOMBRE DE PLACE PAR COMMANDE EST LE NOMBRE DE PLACE TOTAl
								spectacle.setNbrPlaceParClient(placeTotal);
							}
							
							//AJOUT DE LA CATEGORIE
							cat = new Categorie("Libre", prixLibre,placeLibre,placeLibre);
							cat.addCategorie();
							cat = cat.find();
							configuration.addCategorie(cat);
													
							//AJOUT DU SPECTACLE
							spectacle.addSpectacle();	
							spectacle = spectacle.findByTitle();
							ps.addSpectacle(spectacle);                     
							ps.update();
																						
							//AJOUT CONFIGURATION											
							spectacle.setConfiguration(configuration);
							
							//UPDATE DE LA DB
							spectacle.getConfiguration().update(spectacle);
																						
							//AJOUT DES ARTISTES AU SPECTACLE
							spectacle.addArtistesSpectacle();
																						
							//UPDATE DE LA CATEGORIE
							for(Categorie c : configuration.getListeCategorie())
							{
								c.updateConf(configuration);
							}
									
							//CHANGEMENT FRAME
							JOptionPane.showMessageDialog(null, "Spectacle ajouter !");
							setVisible(false);
							RepresentationJFrame frame1 = new RepresentationJFrame(spectacle,p,1);
							frame1.setVisible(true);
						}
						else 
						{
							//DELETE
							configuration.delete();
							JOptionPane.showMessageDialog(null, "Erreur! Spectacle non ajouter. Veuillez choisir un nombre de place inférieur à 8000.");
						}	
					}
						
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
					//CONCERT
					if(configuration.getType().equals("Concert")) 
					{	
						//AJOUT CONFIGURATION											
						configuration.addConfiguration();
						configuration = configuration.find();
							
						if(Categorie.verifyBronzeConcert(placeBronze) == true) 
						{
							//AJOUT DE LA CATEOGIRE
							Categorie catBronze = new Categorie("Bronze", prixBronze,placeBronze,placeBronze);
							catBronze.addCategorie();
							catBronze = catBronze.find();
							configuration.addCategorie(catBronze);
							error = 0;
						}
						else
						{
							error = 1;
							
						}
							
						if(Categorie.verifyArgent(placeArgent) == true) 
						{
							//AJOUT DE LA CATEOGIRE
							Categorie catArgent= new Categorie("Argent", prixArgent,placeArgent,placeArgent);
							catArgent.addCategorie();
							catArgent = catArgent.find();
							configuration.addCategorie(catArgent);
							error = 0;
						} 
						else 
						{
							error = 1;
						}
							
						if(Categorie.verifyOrConcert(placeOr) == true) 
						{
							//AJOUT DE LA CATEOGIRE
							Categorie catOr= new Categorie("Or", prixOr,placeOr,placeOr);
							catOr.addCategorie();
							catOr = catOr.find();
							configuration.addCategorie(catOr);
							error = 0;
						} 
						else 
						{
							error = 1;
						}
						
						if(error == 1) 
						{
							//DELETE CONFIGURATION
							configuration.delete();
							JOptionPane.showMessageDialog(null, "Erreur! Spectacle non ajouter ");	
							return;
						}
						else 
						{						
							//NOMBRE DE PLACE PAR CLIENT LIMITE
							if(chckbxNewCheckBox.isSelected()) 
							{
								String strNbr = NbrParClient.getText();
								int goodNbr = Integer.parseInt(strNbr);
								spectacle.setNbrPlaceParClient(goodNbr);
							}
							else 
							{
								spectacle.setNbrPlaceParClient(placeTotal);
							}
										
							//AJOUT DU SPECTACLE
							spectacle.addSpectacle();	
							spectacle = spectacle.findByTitle();
							ps.addSpectacle(spectacle);                     
							ps.update();
										
							//AJOUT CONFIGURATION											
							spectacle.setConfiguration(configuration);
							spectacle.getConfiguration().update(spectacle);
									
							//AJOUT DES ARTISTES AU SPECTACLE
							spectacle.addArtistesSpectacle();
									
							//UPDATE DE LA CATEGORIE
							for(Categorie c : configuration.getListeCategorie())
							{
								c.updateConf(configuration);
							}
									
							//CHGMT FRAME
							JOptionPane.showMessageDialog(null, "Spectacle Ajouter !");
							RepresentationJFrame frame1 = new RepresentationJFrame(spectacle,p,1);
							frame1.setVisible(true);
						}
					}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					//CIRQUE
					if(configuration.getType().equals("Cirque")) 
					{		
						//AJOUT CONFIGURATION											
						configuration.addConfiguration();
						configuration = configuration.find();
							
						if(Categorie.verifyBronzeCirque(placeBronze) == true) 
						{
							Categorie catBronze = new Categorie("Bronze", prixBronze,placeBronze,placeBronze);
							catBronze.addCategorie();
							catBronze = catBronze.find();
							configuration.addCategorie(catBronze);
							error = 0;
						}
						else
						{
							error = 1;
						}
	
						if(Categorie.verifyArgent(placeArgent) == true) 
						{
							Categorie catArgent= new Categorie("Argent", prixArgent,placeArgent,placeArgent);
							catArgent.addCategorie();
							catArgent = catArgent.find();
							configuration.addCategorie(catArgent);
							error = 0;
						} 
						else 
						{
							error = 1;
						}
						
						if(Categorie.verifyOrCirque(placeOr) == true) 
						{
							Categorie catOr= new Categorie("Or", prixOr,placeOr,placeOr);
							catOr.addCategorie();
							catOr = catOr.find();
							configuration.addCategorie(catOr);
							error = 0;
						} 
						else 
						{
							error = 1;
						}
							
						if(Categorie.verifyDiamantCirque(placeDiamant) == true)
						{
							Categorie catDiamant= new Categorie("Diamant", prixDiamant,placeDiamant,placeDiamant);
							catDiamant.addCategorie();
							catDiamant = catDiamant.find();
							configuration.addCategorie(catDiamant);
							error = 0;;
							error = 0;
						}
						else 
						{
							error = 1;
						}
							
						if(error == 1) 
						{
							//DELETE CONFIGURATION
							configuration.delete();
							JOptionPane.showMessageDialog(null, "Erreur! Spectacle non ajouter ");		
							return;
						}
						else 
						{						
							//NOMBRE DE PLACE PAR CLIENT LIMITE
							if(chckbxNewCheckBox.isSelected()) 
							{
								String strNbr = NbrParClient.getText();
								int goodNbr = Integer.parseInt(strNbr);
								spectacle.setNbrPlaceParClient(goodNbr);
							}
							else 
							{
								spectacle.setNbrPlaceParClient(placeTotal);
							}
		
							//AJOUT DU SPECTACLE
							spectacle.addSpectacle();	
							spectacle = spectacle.findByTitle();
							ps.addSpectacle(spectacle);                     
							ps.update();
								
							//AJOUT CONFIGURATION											
							spectacle.setConfiguration(configuration);
							spectacle.getConfiguration().update(spectacle);
							
							//AJOUT DES ARTISTES DANS LA DB
							spectacle.addArtistesSpectacle();
		
								
							//UPDATE DE LA CATEGORIE DANS LA DB
							for(Categorie c : configuration.getListeCategorie())
							{
								c.updateConf(configuration);
							}
	
							//CHGMT FRAME
							JOptionPane.showMessageDialog(null, "Spectacle Ajouter !");
							setVisible(false);
							RepresentationJFrame frame1 = new RepresentationJFrame(spectacle,p,1);
							frame1.setVisible(true);
						}
					}
				}
				catch(Exception eee) 
				{
					JOptionPane.showMessageDialog(null, "Erreur !");
				}
			} 
		});
	}
}
