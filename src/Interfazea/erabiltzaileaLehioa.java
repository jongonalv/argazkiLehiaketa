package Interfazea;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import programaKlaseak.Erabiltzailea;
import programaKlaseak.Lehiaketa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class erabiltzaileaLehioa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int xx;
	private int xy;

	/**
	 * Create the frame.
	 */
	public erabiltzaileaLehioa(boolean epailea, Erabiltzailea erabiltzailea) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1401, 767);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);		

        JPanel panelGorakoa = new JPanel();
        panelGorakoa.setBackground(new Color(119, 136, 153));
        panelGorakoa.setBounds(0, 0, 1401, 135);
        contentPane.add(panelGorakoa);
        panelGorakoa.setLayout(null);

        LineBorder borde = new LineBorder(new Color(90, 105, 120), 10);
        panelGorakoa.setBorder(borde);

        panelGorakoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xx = e.getX();
                xy = e.getY();
            }
        });

        panelGorakoa.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xx, y - xy);
            }
        });
		
		JLabel lblTituloa = new JLabel("Argazki lehiaketak aplikazioa");
		lblTituloa.setForeground(new Color(255, 255, 255));
		lblTituloa.setFont(new Font("Arial", Font.BOLD, 34));
		lblTituloa.setBounds(52, 23, 530, 85);
		panelGorakoa.add(lblTituloa);
		
        ImageIcon originalIcon = new ImageIcon(erabiltzaileaLehioa.class.getResource("/Irudiak/erabiltzailea.png"));
        Image resizedImage = originalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(resizedIcon);
        lblNewLabel.setBounds(1184, 11, 108, 101);
        panelGorakoa.add(lblNewLabel);
        
        JLabel lblErabiltzailea = new JLabel("");
        lblErabiltzailea.setHorizontalAlignment(SwingConstants.LEFT);
        lblErabiltzailea.setForeground(new Color(255, 255, 255));
        lblErabiltzailea.setFont(new Font("Arial", Font.BOLD, 20));
        lblErabiltzailea.setBounds(1007, 34, 210, 32);
        panelGorakoa.add(lblErabiltzailea);
        
        lblErabiltzailea.setText(erabiltzailea.getErabiltzaileIzena());
        
        JLabel lblErabiltzaileArrunta = new JLabel("");
        lblErabiltzaileArrunta.setHorizontalAlignment(SwingConstants.LEFT);
        lblErabiltzaileArrunta.setForeground(new Color(192, 192, 192));
        lblErabiltzaileArrunta.setFont(new Font("Arial", Font.BOLD, 13));
        lblErabiltzaileArrunta.setBounds(1007, 60, 210, 32);
        panelGorakoa.add(lblErabiltzaileArrunta);
        
        JLabel lehioaItxi = new JLabel("x");
        lehioaItxi.setBounds(1356, 0, 35, 38);
        panelGorakoa.add(lehioaItxi);
        lehioaItxi.setHorizontalAlignment(SwingConstants.CENTER);
        lehioaItxi.setForeground(new Color(220, 20, 60));
        lehioaItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lehioaItxi.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        lehioaItxi.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		System.exit(0);
        	}
        });
        
        if (epailea) {
        	lblErabiltzaileArrunta.setText("Epailea zara");
        }	else	{
        	lblErabiltzaileArrunta.setText("Erabiltzaile arrunta");
		}
        
        List<Lehiaketa> zureLehiaketak = new ArrayList();

		try {
			zureLehiaketak = DatuBasea.lehiaketaDB.getErabiltzaileLehiaketak(erabiltzailea.getErabiltzaileIzena());
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		
		for (int i = 0; i < zureLehiaketak.size(); i++) {
			System.out.println(zureLehiaketak.get(i).getIzena());
		}
		
		JPanel panelZureLehiaketak = new JPanel();
		panelZureLehiaketak.setBackground(new Color(211, 211, 211));
		panelZureLehiaketak.setBounds(0, 134, 377, 633);
		contentPane.add(panelZureLehiaketak);
		panelZureLehiaketak.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Zure lehiaketak:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(37, 11, 291, 55);
		panelZureLehiaketak.add(lblNewLabel_1);
		
		JPanel panelLehiaketak = new JPanel();
		panelLehiaketak.setLayout(new BoxLayout(panelLehiaketak, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(panelLehiaketak);
		scrollPane.setBounds(1024, 134, 377, 633);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		
		panelLehiaketak.add(Box.createVerticalStrut(20));

		JLabel lblNewLabel_1_1 = new JLabel("Parte hartu!");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_1_1.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra horizontalmente en el BoxLayout
		panelLehiaketak.add(lblNewLabel_1_1);
		
		panelLehiaketak.add(Box.createVerticalStrut(50));	

		List<Lehiaketa> lehiaketak = new ArrayList();

		try {
		    lehiaketak = DatuBasea.lehiaketaDB.getAllLehiaketak();
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
        
		for (int i = 0; i < lehiaketak.size(); i++) {

			JPanel panel = new JPanel();
		    panel.setBackground(new Color(248, 248, 255));
		    panel.setBorder(new LineBorder(new Color(119, 136, 153), 2, true));
		    panel.setLayout(null);
		    panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 121)); // Establece la altura máxima
		    panelLehiaketak.add(panel);

			ImageIcon icon = new ImageIcon(getClass().getResource(lehiaketak.get(i).getLogotipoaString()));
			Image iconResize = icon.getImage().getScaledInstance(116, 121, Image.SCALE_SMOOTH);
			ImageIcon iconLandare = new ImageIcon(iconResize);

			JLabel lblLogo = new JLabel("");
			lblLogo.setBounds(0, 0, 116, 121);
			lblLogo.setIcon(iconLandare);
			panel.add(lblLogo);

			JLabel lblNewLabel_3 = new JLabel(lehiaketak.get(i).getIzena());
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_3.setBounds(126, 11, 148, 14);
			panel.add(lblNewLabel_3);

			JLabel lblNewLabel_4 = new JLabel("10/30");
			lblNewLabel_4.setBounds(321, 13, 46, 14);
			panel.add(lblNewLabel_4);

			JButton btnParteHartu = new JButton("");
			btnParteHartu.setBounds(321, 87, 25, 23);
			panel.add(btnParteHartu);
			
			btnParteHartu.addActionListener(e -> {
				
			});

			JTextArea txtDeskripzioa = new JTextArea();
			txtDeskripzioa.setFont(new Font("Arial", Font.PLAIN, 11));
			String deskripzioa = lehiaketak.get(i).getDeskribapena();
			txtDeskripzioa.setLineWrap(true);
			txtDeskripzioa.setWrapStyleWord(true);
			txtDeskripzioa.setText(deskripzioa);
			txtDeskripzioa.setEditable(false);
			txtDeskripzioa.setBackground(new Color(248, 248, 255));
			txtDeskripzioa.setBounds(126, 36, 189, 74);
			panel.add(txtDeskripzioa);
			
			panelLehiaketak.add(Box.createVerticalStrut(15));			 

		}
		
		panelLehiaketak.setPreferredSize(new Dimension(350, lehiaketak.size() * 150));
		SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));

        JPanel panelIkusi = new JPanel();
        panelIkusi.setBounds(379, 134, 646, 633);
        getContentPane().add(panelIkusi);
	}
}
