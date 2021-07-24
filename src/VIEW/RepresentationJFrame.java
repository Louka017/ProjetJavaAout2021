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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import POJO.Categorie;
import POJO.Configuration;
import POJO.Organisateur;
import POJO.PlanningSalle;
import POJO.Representation;
import POJO.Spectacle;

public class RepresentationJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310341402980116981L;
	private JPanel contentPane;
	private JTextField HeureDebut;
	private JTextField HeureFin;
	private JTextField MinDebut;
	private JTextField MinFin;
	DateFormat dateFormatPourTitre = new SimpleDateFormat("dd/MM/yyyy");
	PlanningSalle ps = new PlanningSalle();
	Representation rp ;
	String strDateDebut, strHeureDebut, strDateFin, strHeureFin;
	Date heuredb, heurefn,dateD,dateF;
	Date correctDateDebut = null;
	String goodDateDebut9 = null;
	Date VraiDateDebut = null;
	long heureDeb = 0;
	int error = 0;

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
	public RepresentationJFrame(Spectacle s, Organisateur p, int flag) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 310);
		//JPANEL
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
	
		//RCONSTRUCTION OBJET DU AU DIAGRAMME DE CLASSE
		Configuration conf = Configuration.findBySpectacle(s);
			
		//AJOUTE LE SPECTACLE A LA LISTE D'OBJETS SPECATCLES DE PLANNING SALLE		
		ps.addSpectacle(s);
		
		//TROUVE LE PLANNING SALLE GRACE A L'OBJET SPECTACLE
		ps = ps.findBySpectacle();	
		
		//AJOUT DU SPECTACLE A L'OBJET REPRESENTATION
		rp = new Representation(s);
		
		//AJOUT DES REPRESENTATIONS A LA LISTE D'OBJETS REPRESENTATIONS DE L'OBJET SPECTACLE
		s.setListeRepresentation(rp.getAllRepresentations());
		
		//TITRE
		JLabel lblDateDeSpectacle = new JLabel("Salle louée du " + dateFormatPourTitre.format(ps.getDateDebutSal()) + " au "+ dateFormatPourTitre.format(ps.getDateFinSal()) +" .");
		lblDateDeSpectacle.setForeground(Color.WHITE);
		lblDateDeSpectacle.setBounds(74, 10, 439, 13);
		contentPane.add(lblDateDeSpectacle);

		//HEURE DEBUT
		JLabel lblHeureDebut = new JLabel("Heure de d\u00E9but :");
		lblHeureDebut.setForeground(Color.WHITE);
		lblHeureDebut.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeureDebut.setBounds(10, 69, 111, 13);
		contentPane.add(lblHeureDebut);
		HeureDebut = new JTextField();
		HeureDebut.setBounds(131, 66, 26, 19);
		contentPane.add(HeureDebut);
		HeureDebut.setColumns(10);
		MinDebut = new JTextField();
		MinDebut.setColumns(10);
		MinDebut.setBounds(187, 66, 26, 19);
		contentPane.add(MinDebut);
			
		//HEURE FIN
		JLabel lblHeureFin = new JLabel("Heure de fin :");
		lblHeureFin.setForeground(Color.WHITE);
		lblHeureFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeureFin.setBounds(10, 111, 111, 13);
		contentPane.add(lblHeureFin);
		HeureFin = new JTextField();
		HeureFin.setColumns(10);
		HeureFin.setBounds(131, 108, 26, 19);
		contentPane.add(HeureFin);
		MinFin = new JTextField();
		MinFin.setColumns(10);
		MinFin.setBounds(187, 108, 26, 19);
		contentPane.add(MinFin);
		
		//VALIDER
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(168, 216, 85, 21);
		contentPane.add(btnValider);
			
		//LABEL
		JLabel lblPoint = new JLabel("h");
		lblPoint.setForeground(Color.WHITE);
		lblPoint.setBackground(Color.BLACK);
		lblPoint.setBounds(167, 69, 19, 13);
		contentPane.add(lblPoint);
		JLabel lblPoint_1 = new JLabel("h");
		lblPoint_1.setForeground(Color.WHITE);
		lblPoint_1.setBounds(167, 111, 19, 13);
		contentPane.add(lblPoint_1);
		JLabel lblNewMin = new JLabel("min");
		lblNewMin.setForeground(Color.WHITE);
		lblNewMin.setBounds(227, 69, 45, 13);
		contentPane.add(lblNewMin);
		JLabel lblNewMin_1 = new JLabel("min");
		lblNewMin_1.setForeground(Color.WHITE);
		lblNewMin_1.setBounds(223, 111, 45, 13);
		contentPane.add(lblNewMin_1);
			
		//CALENDAR
		JCalendar calendar = new JCalendar();
		calendar.setBounds(325, 36, 206, 152);
		contentPane.add(calendar);		
		
		//VALIDATION
		btnValider.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{	
				
					//FORMAT DE DATE
					DateFormat dateFormatCourt = new SimpleDateFormat("yyyy-MM-dd"); 
					DateFormat dateFormatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
					//DATE CALENDAR , LE JOUR
					Date daterecup = calendar.getDate();
					String strDateDebut2 = dateFormatCourt.format(daterecup);
					
					try 
					{
						//AJOUT DE LA DATE
						VraiDateDebut = dateFormatCourt.parse(strDateDebut2);
						heureDeb = VraiDateDebut.getTime();
						correctDateDebut = new java.sql.Date (heureDeb);	
						rp.setDate(correctDateDebut);
	
						//AJOUT DE L'HEURE DE DEBUT ET DE FIN
						String h1 = HeureDebut.getText();
						String h2 = HeureFin.getText();
						String m1 = MinDebut.getText();
						String m2 = MinFin.getText();
						
						//REGEX
						error = 0;
						if(!h1.matches("^([0-1][0-9]|2[0-3])$") || !h2.matches("^([0-1][0-9]|2[0-3])$")) {
							error = 1;
						}
						
						if(!m1.matches("^[0-5][0-9]$") || !m2.matches("^[0-5][0-9]$")) {
							error = 1;
						}
						
						//TRANSFORME EN HEURE CORRECTE LA RECUPERATION
						String heuredeb = h1 + ":" + m1;
						String heurefin = h2 + ":" + m2;
						
						//AJOUTE L'HEURE DE LA REPRESENTATION AU JOUR
						strDateDebut = dateFormatCourt.format(correctDateDebut);	
						strHeureDebut = strDateDebut +" "+ heuredeb+":00";
						strDateFin = dateFormatCourt.format(correctDateDebut);	
						strHeureFin = strDateDebut +" "+ heurefin+":00";
						
						//TRANSFORME LE STRING EN DATE ET Le SET
						rp.setHeureDebut(dateFormatLong.parse(strHeureDebut));   
						rp.setHeureFin(dateFormatLong.parse(strHeureFin));
						long heureDeb = rp.getHeureDebut().getTime();
						long heureFin = rp.getHeureFin().getTime();
						heuredb = new java.sql.Date (heureDeb);
						heurefn = new java.sql.Date (heureFin);
						
						//VERIFICATION SI LE JOUR CHOISI EST BIEN COMPRIS ENTRE LES JOURS DU PLANNING
						if(rp.verifyDateRepresentation(ps))
						{
							//SI HEURE DEBUT > HEURE DE FIN
							if(rp.getHeureDebut().getHours() > rp.getHeureFin().getHours()) 
							{
								error = 1;
								if(error == 0) 
								{
									//SI HEURE DEBUT = HEURE DE FIN alors MINUTE DEBUT > MINUTE FIN
									if(rp.getHeureDebut().getHours() == rp.getHeureFin().getHours()) 
									{
										if(rp.getHeureDebut().getMinutes() <= rp.getHeureFin().getMinutes()) 
											error = 1;
									}
								}
							}
							
							//VERIFICATION SI UNE REPRESENTATION A LIEU DURANT UNE AUTRE REPRESENTATION
							if(error == 0) 
							{
								int res = rp.verifyWithAllRepresentations();
								if(res == 0)
								{
									//AJOUT DE LA REPRESENTATION 
									rp.setHeureDebut((java.sql.Date) heuredb);
									rp.setHeureFin((java.sql.Date) heurefn);
									rp.addRepresentation();
									rp = rp.find();
									
									//AJOUT DE LA REPRESENTATION DANS L'OBJET SPECTACLE
									s.addRepresentation(rp);
									
									//LISTE
									conf.setListeCategorie(Categorie.getAllCategories(conf));
	
									//SI REPRESENTATION = 0 ==> AJOUT
									if(flag == 0 )
									{
										if(conf.getType().equals("Debout"))
										{
											Categorie catLibre = new Categorie();
											
											for(Categorie c : conf.getListeCategorie())
											{
												catLibre = c;
												catLibre.setNbrPlaceDispo(c.getNbrPlaceMax());
											}
											catLibre.addCategorie();
											catLibre = catLibre.find();
											catLibre.updateConf(conf);
											catLibre.updateSpec(s);
											catLibre.updateRep(rp);
										}
										
										else if (conf.getType().equals("Concert"))
										{
											Categorie cBronze = new Categorie();
											Categorie cArgent = new Categorie();
											Categorie cOr = new Categorie();
											
											for(Categorie c : conf.getListeCategorie())
											{
												
												if(c.getType().equals("Bronze") )
												{
													cBronze = c;
													cBronze.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
												
												if(c.getType().equals("Argent") )
												{
													cArgent = c;
													cArgent.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
												
												if(c.getType().equals("Or") )
												{
													cOr = c;
													cOr.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
											}
	
											cBronze.addCategorie();
											cBronze = cBronze.find();
											cBronze.updateConf(conf);
											cBronze.updateSpec(s);
											cBronze.updateRep(rp);
											cArgent.addCategorie();
											cArgent = cArgent.find();
											cArgent.updateConf(conf);
											cArgent.updateSpec(s);
											cArgent.updateRep(rp);
											cOr.addCategorie();
											cOr = cOr.find();
											cOr.updateConf(conf);
											cOr.updateSpec(s);
											cOr.updateRep(rp);
										}
										
										else if (conf.getType().equals("Cirque"))
										{
									
											Categorie cBronze = new Categorie();
											Categorie cArgent = new Categorie();
											Categorie cOr = new Categorie();
											Categorie cDiamant = new Categorie();
											
											for(Categorie c : conf.getListeCategorie())
											{
												if(c.getType().equals("Bronze"))
												{
													cBronze = c;
													cBronze.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
												
												if(c.getType().equals("Argent"))
												{
													cArgent = c;
													cArgent.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
												
												if(c.getType().equals("Or"))
												{
													cOr = c;
													cOr.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
												
												if(c.getType().equals("Diamant"))
												{
													cDiamant = c;
													cDiamant.setNbrPlaceDispo(c.getNbrPlaceMax());
												}
											}
											
											cBronze.addCategorie();
											cBronze.updateConf(conf);
											cBronze.updateSpec(s);
											cBronze.updateRep(rp);
											
											cArgent.addCategorie();
											cArgent.updateConf(conf);
											cArgent.updateSpec(s);
											cArgent.updateRep(rp);
											
											cOr.addCategorie();
											cOr.updateConf(conf);
											cOr.updateSpec(s);
											cOr.updateRep(rp);
											
											cDiamant.addCategorie();
											cDiamant.updateConf(conf);
											cDiamant.updateSpec(s);
											cDiamant.updateRep(rp);
										}
									}
									
									else if (flag == 1)
									{
										for(Categorie c : conf.getListeCategorie())
										{
											c.updateSpec(s);
											c.updateRep(rp);										
										}
									}
											
									//NOUVELLE JFRAME
									p.setDiscriminator("Organisateur");
									JOptionPane.showMessageDialog(null, "Représentation creer");  
									setVisible(false);
									Acceuil frame = new Acceuil(p);
									frame.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Une représentation existe déja durant cette période.");
								}
							} 
							else
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir une heure de début inférieur à l'heure de fin");
							}
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir un jour de votre planning de location");
						}
					}
					catch(Exception e4) {
						
					}
				} 
		});
	}
}
