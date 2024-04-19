package Interfazea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import DatuBasea.*;
import programaKlaseak.Erabiltzailea;

// TODO: Auto-generated Javadoc
/**
 * The Class Register.
 */
public class registerFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The izena field. */
	private JTextField izenaField;

	/** The abizena field. */
	private JTextField abizenaField;

	/** The posta field. */
	private JTextField postaField;

	/** The erabiltzaile field. */
	private JTextField erabiltzaileField;

	/** The separator. */
	private JSeparator separator;

	/** The separator 1. */
	private JSeparator separator_1;

	/** The separator 2. */
	private JSeparator separator_2;

	/** The separator 3. */
	private JSeparator separator_3;

	/** The pasahitza field. */
	private JPasswordField pasahitzaField;

	/** The pasahitza errepikatu field. */
	private JPasswordField pasahitzaErrepikatuField;

	/** The separator 4. */
	private JSeparator separator_4;

	/** The separator 5. */
	private JSeparator separator_5;

	/** The lbl izena. */
	private JLabel lblIzena;

	/** The lbl abizena. */
	private JLabel lblAbizena;

	/** The lbl posta. */
	private JLabel lblPosta;

	/** The lbl erabiltzailea. */
	private JLabel lblErabiltzailea;

	/** The lbl pass. */
	private JLabel lblPass;

	/** The lbl pass 2. */
	private JLabel lblPass2;

	/** The lehioa itxi. */
	private JLabel lehioaItxi;

	/**
	 * Field ez hutsa.
	 *
	 * @param field        the field
	 * @param errorMessage the error message
	 * @return true, if successful
	 */
	private boolean fieldEzHutsa(JTextField field, String errorMessage) {
		if (field.getText().isEmpty() || field.getText() == null) {
			JOptionPane.showMessageDialog(null, errorMessage, "Errorea", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Create the frame.
	 */
	public registerFrame() {
		// Barruko barra kentzeko
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 531);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(241, 57, 83));
		panel_1.setBounds(0, 11, 450, 67);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblErregistroaTituloa = new JLabel("ERREGISTROA");
		lblErregistroaTituloa.setHorizontalAlignment(SwingConstants.CENTER);
		lblErregistroaTituloa.setForeground(UIManager.getColor("Button.background"));
		lblErregistroaTituloa.setBounds(58, 11, 336, 31);
		lblErregistroaTituloa.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_1.add(lblErregistroaTituloa);

		lehioaItxi = new JLabel("X");
		lehioaItxi.setBounds(404, 11, 25, 35);
		panel_1.add(lehioaItxi);
		lehioaItxi.setHorizontalAlignment(SwingConstants.CENTER);
		lehioaItxi.setForeground(new Color(0, 0, 0));
		lehioaItxi.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lehioaItxi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(69, 24, 322, 415);
		contentPane.add(panel);
		panel.setLayout(null);

		izenaField = new JTextField();
		izenaField.setBounds(113, 75, 160, 29);
		panel.add(izenaField);
		izenaField.setColumns(10);

		abizenaField = new JTextField();
		abizenaField.setColumns(10);
		abizenaField.setBounds(113, 131, 160, 29);
		panel.add(abizenaField);

		postaField = new JTextField();
		postaField.setColumns(10);
		postaField.setBounds(113, 184, 160, 29);
		panel.add(postaField);

		erabiltzaileField = new JTextField();
		erabiltzaileField.setColumns(10);
		erabiltzaileField.setBounds(113, 241, 160, 29);
		panel.add(erabiltzaileField);

		separator = new JSeparator();
		separator.setBounds(113, 268, 160, 2);
		panel.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(113, 212, 160, 2);
		panel.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBounds(113, 158, 160, 2);
		panel.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setBounds(113, 102, 160, 2);
		panel.add(separator_3);

		pasahitzaField = new JPasswordField();
		pasahitzaField.setBounds(113, 297, 160, 29);
		panel.add(pasahitzaField);

		pasahitzaErrepikatuField = new JPasswordField();
		pasahitzaErrepikatuField.setBounds(113, 353, 160, 29);
		panel.add(pasahitzaErrepikatuField);

		separator_4 = new JSeparator();
		separator_4.setBounds(113, 325, 160, 2);
		panel.add(separator_4);

		separator_5 = new JSeparator();
		separator_5.setBounds(113, 380, 160, 2);
		panel.add(separator_5);

		lblIzena = new JLabel("Izena");
		lblIzena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIzena.setFont(new Font("Arial", Font.PLAIN, 11));
		lblIzena.setBounds(10, 79, 93, 21);
		panel.add(lblIzena);

		lblAbizena = new JLabel("Abizena");
		lblAbizena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAbizena.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAbizena.setBounds(10, 131, 93, 21);
		panel.add(lblAbizena);

		lblPosta = new JLabel("Posta elektronikoa");
		lblPosta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPosta.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPosta.setBounds(10, 191, 93, 21);
		panel.add(lblPosta);

		lblErabiltzailea = new JLabel("Erabiltzaile izena");
		lblErabiltzailea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblErabiltzailea.setFont(new Font("Arial", Font.PLAIN, 11));
		lblErabiltzailea.setBounds(10, 248, 93, 21);
		panel.add(lblErabiltzailea);

		lblPass = new JLabel("Pasahitza");
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPass.setBounds(10, 304, 93, 21);
		panel.add(lblPass);

		lblPass2 = new JLabel("Errepikatu pasahitza");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPass2.setBounds(0, 357, 110, 21);
		panel.add(lblPass2);

		Button bueltatuBtn = new Button("Bueltatu");
		bueltatuBtn.setBounds(103, 456, 110, 53);
		contentPane.add(bueltatuBtn);
		bueltatuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginFrame login = new loginFrame();
				setVisible(false);

				// Lehioaren kokapena lortu
				Point currentLocation = getLocation();

				// erregistroaren kokapena jarri lehio honen kokapenaren arabera
				int newX = currentLocation.x + (getWidth() - login.getWidth()) / 2;
				int newY = currentLocation.y + (getHeight() - login.getHeight()) / 2;
				login.setLocation(newX, newY);
				login.setVisible(true);
			}
		});
		bueltatuBtn.setForeground(SystemColor.text);
		bueltatuBtn.setFont(new Font("Arial", Font.BOLD, 15));
		bueltatuBtn.setBackground(new Color(95, 158, 160));

		Button erregistratuBtn = new Button("Erregistratu!");
		erregistratuBtn.setBounds(253, 456, 110, 53);
		contentPane.add(erregistratuBtn);
		erregistratuBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// Izena hutsik dagoen konprobatu
				if (!fieldEzHutsa(izenaField, "Izena ezin du hutsik egon")) {
					return;
				}

				// Abizena hutsik dagoen konprobatu
				if (!fieldEzHutsa(abizenaField, "Abizena ezin du hutsik egon")) {
					return;
				}

				// Erabiltzaile izena hutsik dagoen konprobatu eta datu basean existitzen den
				// konprobatu
				if (!fieldEzHutsa(erabiltzaileField, "Erabiltzaile izena ezin du hutsik egon")) {
					return;
				} else {
					try {
						if (DatuBasea.erabiltzaileakDB.erabiltzaileIzenaBerdina(erabiltzaileField.getText())) {
							JOptionPane.showMessageDialog(null,
									"Erabiltzaile izen hori existitzen da. Beste bat sartu, mesedez.", "Errorea",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Errore ezezaguna datu basean.", "Errorea",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				// Pasahitzak berdinak diren konprobatu
				if (!pasahitzaField.getText().equals(pasahitzaErrepikatuField.getText())) {
					JOptionPane.showMessageDialog(null, "Pasahitzak ez dute koinziditzen", "Errorea",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Erabiltzaile berri bat sortu emandako datuekin
				Erabiltzailea erabiltzailea = new Erabiltzailea(izenaField.getText(), abizenaField.getText(),
						erabiltzaileField.getText(), postaField.getText(), pasahitzaErrepikatuField.getText());

				// Datu baseari deitu erabiltzailea sartzeko
				try {
					DatuBasea.erregistroaDB.insertErabiltzailea(erabiltzailea);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Errore ezezaguna datu basean.", "Errorea",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Dena ondo joan bada, mezu bat erakutsi erregistroa egin dela erakusteko
				JOptionPane.showMessageDialog(null, "Erregistratuta zaude sisteman!", "Erregistroa",
						JOptionPane.INFORMATION_MESSAGE);

				// Erregistro lehioa kendu eta login-era bueltatu
				
				setVisible(false);
				loginFrame login = new loginFrame();
				
				// Lehioaren kokapena lortu
				Point currentLocation = getLocation();

				// erregistroaren kokapena jarri lehio honen kokapenaren arabera
				int newX = currentLocation.x + (getWidth() - login.getWidth()) / 2;
				int newY = currentLocation.y + (getHeight() - login.getHeight()) / 2;
				login.setLocation(newX, newY);
				
				login.setVisible(true);
			}
		});

		erregistratuBtn.setForeground(SystemColor.text);
		erregistratuBtn.setFont(new Font("Arial", Font.BOLD, 15));
		erregistratuBtn.setBackground(new Color(241, 57, 83));

		// Zentratu
		setLocationRelativeTo(null);

	}
}
