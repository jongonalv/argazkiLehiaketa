package Interfazea;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import programaKlaseak.Erabiltzailea;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SwingConstants;

public class loginLehioa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField erabiltzaileaSartu;
	private JPasswordField pasahitzaErrepikatu;
	private JPasswordField pasahitzaSartu;
	private int xx;
	private int xy;

	/**
	 * Create the frame.
	 */
	public loginLehioa() {

		setTitle("Argazki lehiaketa");

		// Barruko barra kentzeko
		setUndecorated(true);

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 474);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel hau erabilita, actionListener bat gehitu lehioa edonoraino mugitzeko
		JPanel argazkiaTituloaPanel = new JPanel();
		argazkiaTituloaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});

		argazkiaTituloaPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				loginLehioa.this.setLocation(x - xx, y - xy);
			}
		});

		argazkiaTituloaPanel.setBackground(Color.LIGHT_GRAY);
		argazkiaTituloaPanel.setBounds(0, -11, 357, 485);
		contentPane.add(argazkiaTituloaPanel);

		JLabel lblArgazkia = new JLabel("");
		lblArgazkia.setBounds(-71, 5, 450, 260);
		ImageIcon icono = new ImageIcon(loginLehioa.class.getResource("/Irudiak/login.jpg"));
		Image image = icono.getImage();
		Image nuevaImagen = image.getScaledInstance(450, 260, Image.SCALE_SMOOTH);
		icono = new ImageIcon(nuevaImagen);
		argazkiaTituloaPanel.setLayout(null);
		lblArgazkia.setIcon(icono);
		argazkiaTituloaPanel.add(lblArgazkia);

		JLabel lblTituloa = new JLabel("Argazki lehiaketa aplikazioa");
		lblTituloa.setForeground(Color.WHITE);
		lblTituloa.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblTituloa.setBounds(21, 308, 358, 43);
		argazkiaTituloaPanel.add(lblTituloa);

		JLabel lblSubtituloa = new JLabel("Euskal Herriko elkartea");
		lblSubtituloa.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblSubtituloa.setForeground(new Color(255, 255, 255));
		lblSubtituloa.setBounds(90, 362, 176, 17);
		argazkiaTituloaPanel.add(lblSubtituloa);

		Button loginBtn = new Button("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Erabiltzaile berri bat sortu emandako erabiltzaile izenarekin eta pasahitzarekin
				Erabiltzailea erabiltzailea;

				if (erabiltzaileaSartu.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Erabiltzailea hutsik dago", "Errorea",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (pasahitzaSartu.getText().equals(pasahitzaErrepikatu.getText())) {
					erabiltzailea = new Erabiltzailea(erabiltzaileaSartu.getText(), pasahitzaErrepikatu.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Pasahitzak ez dute koinziditzen.", "Errorea",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				erabiltzaileaLehioa erabiltzaileaFrame;
				kudeatzaileakInterfazea kudeatuInterfazea;

				if (DatuBasea.loginDB.isErabiltzaileValid(erabiltzailea)) {
					if (DatuBasea.loginDB.rolDa(erabiltzailea, "epailea")) {
						erabiltzaileaFrame = new erabiltzaileaLehioa(true, erabiltzailea);
						setVisible(false);
						erabiltzaileaFrame.setVisible(true);
					} else if (DatuBasea.loginDB.rolDa(erabiltzailea, "kudeatzailea")){
						kudeatuInterfazea = new kudeatzaileakInterfazea(false, erabiltzailea);
						setVisible(false);
						kudeatuInterfazea.setVisible(true);
					} else if (DatuBasea.loginDB.rolDa(erabiltzailea, "admin")){
						kudeatuInterfazea = new kudeatzaileakInterfazea(true, erabiltzailea);
						setVisible(false);
						kudeatuInterfazea.setVisible(true);
					} else {
						erabiltzaileaFrame = new erabiltzaileaLehioa(false, erabiltzailea);
						setVisible(false);
						erabiltzaileaFrame.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erabiltzailea edo pasahitza gaizki sartu da.", "Errorea",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		loginBtn.setFont(new Font("Arial", Font.BOLD, 15));
		loginBtn.setForeground(SystemColor.text);
		loginBtn.setBackground(new Color(241, 57, 83));
		loginBtn.setBounds(437, 305, 181, 33);
		contentPane.add(loginBtn);

		erabiltzaileaSartu = new JTextField();
		erabiltzaileaSartu.setBounds(404, 99, 254, 33);
		contentPane.add(erabiltzaileaSartu);
		erabiltzaileaSartu.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(404, 130, 254, 2);
		contentPane.add(separator_1);

		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Arial", Font.BOLD, 13));
		lblPasahitza.setBounds(405, 143, 90, 14);
		contentPane.add(lblPasahitza);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(404, 192, 254, 2);
		contentPane.add(separator_1_1);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(404, 255, 254, 2);
		contentPane.add(separator_1_2);

		JLabel lblErabiltzailea = new JLabel("Erabiltzailea");
		lblErabiltzailea.setFont(new Font("Arial", Font.BOLD, 13));
		lblErabiltzailea.setBounds(405, 80, 90, 14);
		contentPane.add(lblErabiltzailea);

		JLabel lblErrepikatuPasahitza = new JLabel("Errepikatu pasahitza");
		lblErrepikatuPasahitza.setFont(new Font("Arial", Font.BOLD, 13));
		lblErrepikatuPasahitza.setBounds(405, 205, 159, 14);
		contentPane.add(lblErrepikatuPasahitza);

		pasahitzaErrepikatu = new JPasswordField();
		pasahitzaErrepikatu.setBounds(404, 224, 254, 33);
		contentPane.add(pasahitzaErrepikatu);

		pasahitzaSartu = new JPasswordField();
		pasahitzaSartu.setBounds(404, 161, 254, 33);
		contentPane.add(pasahitzaSartu);

		Button erregistratuBtn = new Button("Erregistratu");
		erregistratuBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerLehioa erregistroa = new registerLehioa();

		        // Lehioaren kokapena lortu
		        Point currentLocation = getLocation();

		        // erregistroaren kokapena jarri lehio honen kokapenaren arabera		     
		        int newX = currentLocation.x + (getWidth() - erregistroa.getWidth()) / 2;
		        int newY = currentLocation.y + (getHeight() - erregistroa.getHeight()) / 2;
		        erregistroa.setLocation(newX, newY);

		        setVisible(false);
		        erregistroa.setVisible(true);

			}
		});
		erregistratuBtn.setForeground(SystemColor.text);
		erregistratuBtn.setFont(new Font("Arial", Font.BOLD, 15));
		erregistratuBtn.setBackground(new Color(95, 158, 160));
		erregistratuBtn.setBounds(437, 375, 181, 33);
		contentPane.add(erregistratuBtn);

		JLabel lehioaItxi = new JLabel("X");
		lehioaItxi.setHorizontalAlignment(SwingConstants.CENTER);
		lehioaItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lehioaItxi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lehioaItxi.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lehioaItxi.setForeground(new Color(205, 92, 92));
		lehioaItxi.setBounds(705, 11, 25, 35);
		contentPane.add(lehioaItxi);

		// Zentratu
		setLocationRelativeTo(null);
	}
	
	// Programa exekutatzeko
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					loginLehioa frame = new loginLehioa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}