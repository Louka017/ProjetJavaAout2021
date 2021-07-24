package VIEW;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Client;
import POJO.Representation;
import POJO.Spectacle;

public class ClientReservationSpectacleJFrame extends JFrame {

	private static final long serialVersionUID = -5778600096352586056L;
	private JPanel contentPane;
	Representation r;
	private JList<Representation> listRepresentations;
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
	public ClientReservationSpectacleJFrame(Client p, Spectacle s) 
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
		
		//TITRE
		JLabel lblTitre = new JLabel("titre");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setBounds(0, 21, 446, 49);
		lblTitre.setText(s.getTitre());
		contentPane.add(lblTitre);
		
		//OBJET REPRESENTATION
		r = new Representation(s);

		//LISTE
		DefaultListModel<Representation> model = new DefaultListModel<>();
		model.addAll(r.getAllRepresentationsHoraire());
		listRepresentations = new JList<>(model);
		listRepresentations.setBounds(23, 115, 191, 138);
		contentPane.add(listRepresentations);

		//LABELS
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(Color.LIGHT_GRAY);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(34, 92, 45, 13);
		contentPane.add(lblDate);
		
		JLabel lblHeureD = new JLabel("Debut");
		lblHeureD.setForeground(Color.LIGHT_GRAY);
		lblHeureD.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeureD.setBounds(95, 92, 45, 13);
		contentPane.add(lblHeureD);
		
		JLabel lblHeureF = new JLabel("Fin");
		lblHeureF.setForeground(Color.LIGHT_GRAY);
		lblHeureF.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeureF.setBounds(160, 92, 45, 13);
		contentPane.add(lblHeureF);
		
		//BOUTTON VALIDER		
		JButton btnValider = new JButton("R\u00E9server");
		btnValider.setBounds(299, 128, 127, 21);
		contentPane.add(btnValider);
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					r = listRepresentations.getSelectedValue();	
					r.setSpectacle(s);
					ClientPlaceJFrame c = new ClientPlaceJFrame(r, p, s);
					c.setVisible(true);
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(null,"Erreur ! Veuillez choisir une rerésentation !");	
				}
			}
		});
	}
}
