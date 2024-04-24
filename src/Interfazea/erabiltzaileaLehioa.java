package Interfazea;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import programaKlaseak.Erabiltzailea;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingConstants;
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
		panelLehiaketak.setBackground(new Color(211, 211, 211));
		panelLehiaketak.setBounds(1024, 134, 377, 633);
		contentPane.add(panelLehiaketak);
		panelLehiaketak.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Parte hartu!");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_1_1.setBounds(46, 11, 291, 55);
		panelLehiaketak.add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(10, 75, 357, 121);
		panelLehiaketak.add(panel);
		LineBorder bordePanel = new LineBorder(new Color(119, 136, 153), 2, true); 
        panel.setBorder(bordePanel);
		panel.setLayout(null);
		
		ImageIcon icon = new ImageIcon(erabiltzaileaLehioa.class.getResource("/logotipoak/natura1.jpg"));
        Image iconResize = icon.getImage().getScaledInstance(116, 121, Image.SCALE_SMOOTH);
        ImageIcon iconLandare = new ImageIcon(iconResize);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(0, 0, 116, 121);
        lblLogo.setIcon(iconLandare);
		panel.add(lblLogo);
		
		JLabel lblNewLabel_3 = new JLabel("Landare Lehiaketa");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(126, 11, 148, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("10/30");
		lblNewLabel_4.setBounds(321, 13, 46, 14);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(321, 87, 25, 23);
		panel.add(btnNewButton);
		
		JTextArea txtDeskripzioa = new JTextArea();
		txtDeskripzioa.setFont(new Font("Arial", Font.PLAIN, 11));
		txtDeskripzioa.setText("Lorem ipsum dolor sit amet\r\nconsectetur adipiscing elit eu\r\nmetus cum urna sagittis vivamus \r\ndignissim habitant diam nunc, conubia \r\nturpis at mauris facilisi nascetur id.");
		txtDeskripzioa.setEditable(false);
		txtDeskripzioa.setBackground(new Color(248, 248, 255));
		txtDeskripzioa.setBounds(126, 36, 189, 74);
		panel.add(txtDeskripzioa);
		
		JPanel panelIkusi = new JPanel();
		panelIkusi.setBounds(379, 134, 646, 633);
		contentPane.add(panelIkusi);
	}
}
