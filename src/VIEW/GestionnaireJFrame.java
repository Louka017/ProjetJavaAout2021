package VIEW;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Gestionnaire;
import POJO.Personne;
import POJO.PlanningSalle;
import java.awt.Color;

public class GestionnaireJFrame extends JFrame {

	private static final long serialVersionUID = -6830900281301215260L;
	private JPanel contentPane;
	private JList<PlanningSalle> listeTousLesSpec;
	private JLabel lblDb;
	private JLabel lblDf;

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
	public GestionnaireJFrame(Gestionnaire g) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 408);
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
		
		//RECUPERERPLANNING SALLE
		g.setPlanningSalle(PlanningSalle.getAllPlanningSalle());
		
		DefaultListModel<PlanningSalle> model = new DefaultListModel<>();
		model.addAll(g.getlistePlanningSalle());
		listeTousLesSpec = new JList<>(model);
		listeTousLesSpec.setBounds(10, 44, 235, 305);
		contentPane.add(listeTousLesSpec);
		
		
		//BOUTON RETOUR
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(296, 302, 85, 21);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Acceuil frame = new Acceuil((Personne)g);
				setVisible(false);
				frame.setVisible(true);
			}
		});
		
		//LABLES
		lblDb = new JLabel("Date d\u00E9but : ");
		lblDb.setForeground(Color.WHITE);
		lblDb.setBackground(Color.WHITE);
		lblDb.setHorizontalAlignment(SwingConstants.CENTER);
		lblDb.setBounds(22, 21, 85, 13);
		contentPane.add(lblDb);
		
		lblDf = new JLabel("Date fin :");
		lblDf.setForeground(Color.WHITE);
		lblDf.setHorizontalAlignment(SwingConstants.CENTER);
		lblDf.setBounds(147, 21, 64, 13);
		contentPane.add(lblDf);
		
		
		
	}
}
