package interfazeGrafikoa;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
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

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField erabiltzaileaSartu;
	private JPasswordField pasahitzaSartu;
	private JPasswordField pasahitzaErrepikatu;
	private int xx;
	private int xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		
		setAlwaysOnTop(true);
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
		        Home.this.setLocation(x - xx, y - xy);
		    }
		});

		
		argazkiaTituloaPanel.setBackground(Color.LIGHT_GRAY);
		argazkiaTituloaPanel.setBounds(0, -11, 357, 485);
		contentPane.add(argazkiaTituloaPanel);
		
		JLabel lblArgazkia = new JLabel("");
		lblArgazkia.setBounds(-71, 5, 450, 260);
		ImageIcon icono = new ImageIcon(Home.class.getResource("/Irudiak/login.jpg"));
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
		
		pasahitzaSartu = new JPasswordField();
		pasahitzaSartu.setBounds(404, 224, 254, 33);
		contentPane.add(pasahitzaSartu);
		
		pasahitzaErrepikatu = new JPasswordField();
		pasahitzaErrepikatu.setBounds(404, 161, 254, 33);
		contentPane.add(pasahitzaErrepikatu);
		
		Button erregistratuBtn = new Button("Erregistratu");
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
	}
}
