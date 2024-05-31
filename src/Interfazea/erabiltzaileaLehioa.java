package Interfazea;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import DatuBasea.botoak;
import DatuBasea.erabiltzaileakDB;
import DatuBasea.irudiakDB;
import DatuBasea.lehiaketaDB;
import programaKlaseak.Atala;
import programaKlaseak.Erabiltzailea;
import programaKlaseak.Lehiaketa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.print.DocFlavor.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

// TODO: Auto-generated Javadoc
/**
 * The Class erabiltzaileaLehioa.
 */
public class erabiltzaileaLehioa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Lehiaketa> zureLehiaketak = new ArrayList<>();
	private List<Lehiaketa> lehiaketak = new ArrayList<>();
	private int xx;
	private int xy;
	private static JPanel panelIkusi;
	private static JButton btnEzker;
	private static JButton btnEskubi;
	private static JComboBox<String> comboAtala;
	private static JButton btnIgo;
	private static JButton btnBozkatu;
	private static JButton btnEzabatu;
	private static JButton bisoreaBtn;
	private static JLabel lblAukeratu;
	private static JLabel lblZureArgazkia;
	private static JLabel lblArgazkiak;
	private static JLabel lblIzena;
	private static JLabel lblEgilea;
	private static JLabel lblBotoak;
	private static JLabel lblSaria;
	private static JButton btnAteraLehiaketatik;

	/**
	 * Erabiltzailearen lehioa sortuko du bere konponente guztiekin.
	 *
	 * @param epailea       epailea den ala ez jakiteko (true epailea)
	 * @param erabiltzailea erabiltzailearen informazioa lehioan lan egiteko
	 */
	public erabiltzaileaLehioa(boolean epailea, Erabiltzailea erabiltzailea) {

		// Lehioaren informazio nagusia aldatu
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

		// Goiko paneletik arratoiari click emanda, mugitzeko listenerrak
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

		// Titutloa
		JLabel lblTituloa = new JLabel("Argazki lehiaketak aplikazioa");
		lblTituloa.setForeground(new Color(255, 255, 255));
		lblTituloa.setFont(new Font("Arial", Font.BOLD, 34));
		lblTituloa.setBounds(52, 23, 530, 85);
		panelGorakoa.add(lblTituloa);

		// Erabiltzailea dekoratzeko ikonoa
		ImageIcon originalIcon = new ImageIcon(erabiltzaileaLehioa.class.getResource("/Irudiak/erabiltzailea.png"));
		Image resizedImage = originalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);

		// Hemen ikonoa jarri
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(resizedIcon);
		lblNewLabel.setBounds(1184, 11, 108, 101);
		panelGorakoa.add(lblNewLabel);

		// Erabiltzailearen datuak hemen jarriko dira
		JLabel lblErabiltzailea = new JLabel("");
		lblErabiltzailea.setHorizontalAlignment(SwingConstants.LEFT);
		lblErabiltzailea.setForeground(new Color(255, 255, 255));
		lblErabiltzailea.setFont(new Font("Arial", Font.BOLD, 20));
		lblErabiltzailea.setBounds(1007, 34, 210, 32);
		panelGorakoa.add(lblErabiltzailea);

		// Login egindako erabiltzailearen informazioa lortu eta erabiltzaile izena
		// goran jarri
		lblErabiltzailea.setText(erabiltzailea.getErabiltzaileIzena());

		// Epailea den edo arrunta den jakiteko labela
		JLabel lblErabiltzaileArrunta = new JLabel("");
		lblErabiltzaileArrunta.setHorizontalAlignment(SwingConstants.LEFT);
		lblErabiltzaileArrunta.setForeground(new Color(192, 192, 192));
		lblErabiltzaileArrunta.setFont(new Font("Arial", Font.BOLD, 13));
		lblErabiltzaileArrunta.setBounds(1007, 60, 210, 32);
		panelGorakoa.add(lblErabiltzaileArrunta);

		// Lehioa ixteko
		JLabel lehioaItxi = new JLabel("x");
		lehioaItxi.setBounds(1356, 0, 35, 38);
		panelGorakoa.add(lehioaItxi);
		lehioaItxi.setHorizontalAlignment(SwingConstants.CENTER);
		lehioaItxi.setForeground(new Color(220, 20, 60));
		lehioaItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lehioaItxi.setFont(new Font("Segoe UI", Font.PLAIN, 36));

		// Label-ari click eginda, lehioa itxiko du.
		lehioaItxi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component component = e.getComponent();
				JFrame frame = (JFrame) SwingUtilities.getRoot(component);
				frame.dispose();
			}
		});

		// Epailea bada, goran adierazi
		if (epailea) {
			lblErabiltzaileArrunta.setText("Epailea zara");
		} else {
			lblErabiltzaileArrunta.setText("Erabiltzaile arrunta");
		}

		// Erabiltzaileak dituen lehiaketak kargatu eta zerrendan gorde
		try {
			zureLehiaketak = DatuBasea.lehiaketaDB.getErabiltzaileLehiaketak(erabiltzailea.getErabiltzaileIzena());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// Hemen erabiltzailearen lehiaketak azalduko dira
		JPanel panelZureLehiaketak = new JPanel();
		panelZureLehiaketak.setLayout(new BoxLayout(panelZureLehiaketak, BoxLayout.Y_AXIS)); // BoxLayout vertical

		// Scrollpanel bat gehitu lehiaketa asko badaude
		JScrollPane scrollPane1 = new JScrollPane(panelZureLehiaketak);
		scrollPane1.setBounds(0, 134, 377, 633);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane1);

		// Lehiaketen informazioa erakusteko metodoa, erabiltzailearen datuekin eta
		// epailea den ala ez
		erakutsiLehiaketak(zureLehiaketak, panelZureLehiaketak, erabiltzailea, epailea);

		// Panelaren datuak
		panelZureLehiaketak.setPreferredSize(new Dimension(350, zureLehiaketak.size() * 150));
		SwingUtilities.invokeLater(() -> scrollPane1.getVerticalScrollBar().setValue(0));

		// Lehiaketa guztiak hemen azalduko dira
		JPanel panelLehiaketak = new JPanel();
		panelLehiaketak.setLayout(new BoxLayout(panelLehiaketak, BoxLayout.Y_AXIS));

		// Lehiaketa guztientzeko scrollpanel bat asko badaude
		JScrollPane scrollPane = new JScrollPane(panelLehiaketak);
		scrollPane.setBounds(1024, 134, 377, 633);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);

		panelLehiaketak.add(Box.createVerticalStrut(20));

		// Parte hartzekoa
		JLabel lblParteHartu = new JLabel("Parte hartu!");
		lblParteHartu.setHorizontalAlignment(SwingConstants.CENTER);
		lblParteHartu.setFont(new Font("Arial", Font.BOLD, 24));
		lblParteHartu.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelLehiaketak.add(lblParteHartu);

		panelLehiaketak.add(Box.createVerticalStrut(50));

		// Lehiaketa guztien informazioa lortu datu baseari deituz
		try {
			lehiaketak = DatuBasea.lehiaketaDB.getAllLehiaketak();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// Lehiaketa guztiak lortuta, panel ezberdinetan informazioa azaldu
		for (int i = 0; i < lehiaketak.size(); i++) {

			final Lehiaketa lehiaketa = lehiaketak.get(i);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(248, 248, 255));
			panel.setBorder(new LineBorder(new Color(119, 136, 153), 2, true));
			panel.setLayout(null);
			panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 121));
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

			JButton btnParteHartu = new JButton("");
			btnParteHartu.setIcon(new ImageIcon(erabiltzaileaLehioa.class.getResource("/Irudiak/gehitu.png")));
			btnParteHartu.setContentAreaFilled(false);
			btnParteHartu.setBounds(321, 87, 25, 23);
			panel.add(btnParteHartu);

			// Lehiaketa batean parte hartzeko listener-a
			btnParteHartu.addActionListener(e -> {
				try {

					// Sesioan dagoen erabiltzailearen datuak hartu eta lehiaketan jarri
					lehiaketaDB.gehituLehiaketaErabiltzailea(erabiltzailea, lehiaketa);
				} catch (SQLIntegrityConstraintViolationException e1) {
					JOptionPane.showMessageDialog(null, "Lehiaketa honetan jadanik parte hartu dezu.");
					return;
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Datu basean errorea.");
					return;
				}

				// Lehiaketa zerrendan gorde
				zureLehiaketak.add(lehiaketa);

				// Berriz erakutsi lehiaketak berriarenkin
				erakutsiLehiaketak(zureLehiaketak, panelZureLehiaketak, erabiltzailea, epailea);
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

		panelIkusi = new JPanel();
		panelIkusi.setBounds(369, 134, 649, 633);
		getContentPane().add(panelIkusi);
		panelIkusi.setLayout(null);

		btnEzker = new JButton("");
		btnEzker.setBackground(UIManager.getColor("Button.light"));
		btnEzker.setContentAreaFilled(false);
		btnEzker.setIcon(new ImageIcon(erabiltzaileaLehioa.class.getResource("/Irudiak/ezkerra.png")));
		btnEzker.setBounds(58, 107, 89, 48);
		panelIkusi.add(btnEzker);

		btnEskubi = new JButton("");
		btnEskubi.setBackground(UIManager.getColor("Button.light"));
		btnEskubi.setContentAreaFilled(false);
		btnEskubi.setIcon(new ImageIcon(erabiltzaileaLehioa.class.getResource("/Irudiak/eskubi.png")));
		btnEskubi.setBounds(146, 107, 89, 48);
		panelIkusi.add(btnEskubi);

		btnEzker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ez da aukeratu lehiaketarik", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		btnEskubi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ez da aukeratu lehiaketarik", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		comboAtala = new JComboBox<String>();
		comboAtala.setToolTipText("");
		comboAtala.setBounds(58, 230, 177, 31);
		panelIkusi.add(comboAtala);

		btnIgo = new JButton("Igo argazkia");
		btnIgo.setForeground(new Color(248, 248, 255));
		btnIgo.setBackground(new Color(241, 57, 83));
		btnIgo.setFont(new Font("Arial", Font.BOLD, 16));
		btnIgo.setBounds(58, 369, 149, 48);
		panelIkusi.add(btnIgo);

		btnIgo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnEzabatu = new JButton("Ezabatu argazkia");
		btnEzabatu.setForeground(new Color(248, 248, 255));
		btnEzabatu.setBackground(new Color(95, 158, 160));
		btnEzabatu.setFont(new Font("Arial", Font.BOLD, 13));
		btnEzabatu.setBounds(58, 450, 149, 48);
		panelIkusi.add(btnEzabatu);

		btnEzabatu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		lblAukeratu = new JLabel("Aukeratu atala:");
		lblAukeratu.setFont(new Font("Arial", Font.BOLD, 13));
		lblAukeratu.setBounds(58, 211, 177, 14);
		panelIkusi.add(lblAukeratu);

		lblZureArgazkia = new JLabel("");
		lblZureArgazkia.setIcon(new ImageIcon(erabiltzaileaLehioa.class.getResource("")));
		lblZureArgazkia.setHorizontalAlignment(SwingConstants.CENTER);
		lblZureArgazkia.setBounds(297, 338, 304, 273);
		panelIkusi.add(lblZureArgazkia);

		lblArgazkiak = new JLabel("");
		lblArgazkiak.setIcon(new ImageIcon(erabiltzaileaLehioa.class.getResource("")));
		lblArgazkiak.setHorizontalAlignment(SwingConstants.CENTER);
		lblArgazkiak.setBounds(297, 11, 304, 273);
		panelIkusi.add(lblArgazkiak);

		lblIzena = new JLabel("");
		lblIzena.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblIzena.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzena.setBounds(58, 30, 177, 31);
		panelIkusi.add(lblIzena);

		lblEgilea = new JLabel("Egilea: ");
		lblEgilea.setBounds(296, 296, 149, 14);
		panelIkusi.add(lblEgilea);

		lblBotoak = new JLabel("Boto kantitatea: ");
		lblBotoak.setBounds(452, 296, 149, 14);
		panelIkusi.add(lblBotoak);

		btnAteraLehiaketatik = new JButton("Atera lehiaketatik");
		btnAteraLehiaketatik.setForeground(new Color(248, 248, 255));
		btnAteraLehiaketatik.setFont(new Font("Arial", Font.BOLD, 13));
		btnAteraLehiaketatik.setBackground(new Color(95, 158, 160));
		btnAteraLehiaketatik.setBounds(58, 532, 149, 48);
		panelIkusi.add(btnAteraLehiaketatik);

		lblSaria = new JLabel("SARIA:");
		lblSaria.setBounds(58, 270, 149, 14);
		panelIkusi.add(lblSaria);

		bisoreaBtn = new JButton("Bisorea");
		bisoreaBtn.setBackground(new Color(211, 211, 211));
		bisoreaBtn.setForeground(new Color(0, 0, 0));
		bisoreaBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		bisoreaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ez daude argazkirik kargatuta");
			}
		});
		bisoreaBtn.setBounds(58, 177, 177, 23);
		panelIkusi.add(bisoreaBtn);

		if (epailea) {

			btnBozkatu = new JButton("Bozkatu");
			btnBozkatu.setForeground(new Color(248, 248, 255));
			btnBozkatu.setFont(new Font("Arial", Font.BOLD, 13));
			btnBozkatu.setBackground(new Color(95, 158, 160));
			btnBozkatu.setBounds(58, 292, 149, 48);
			panelIkusi.add(btnBozkatu);

		}
	}

	/**
	 * Erakutsi lehiaketak.
	 *
	 * @param zureLehiaketak      the zure lehiaketak
	 * @param panelZureLehiaketak the panel zure lehiaketak
	 * @param erabiltzailea       the erabiltzailea
	 */
	public void erakutsiLehiaketak(List<Lehiaketa> zureLehiaketak, JPanel panelZureLehiaketak,
			Erabiltzailea erabiltzailea, boolean epailea) {

		panelZureLehiaketak.removeAll();

		panelZureLehiaketak.add(Box.createVerticalStrut(20));

		JLabel lblNewLabel_1 = new JLabel("Zure lehiaketak:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelZureLehiaketak.add(lblNewLabel_1);

		panelZureLehiaketak.add(Box.createVerticalStrut(50));

		// Erabiltzaileak parte hartzen duen lehiaketa guztien informazioa
		for (int i = 0; i < zureLehiaketak.size(); i++) {

			final int index = i;
			final Lehiaketa lehiaketa = zureLehiaketak.get(i);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(248, 248, 255));
			panel.setBorder(new LineBorder(new Color(119, 136, 153), 2, true));
			panel.setLayout(null);
			panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 121));
			panelZureLehiaketak.add(panel);

			ImageIcon icon = new ImageIcon(getClass().getResource(zureLehiaketak.get(i).getLogotipoaString()));
			Image iconResize = icon.getImage().getScaledInstance(116, 121, Image.SCALE_SMOOTH);
			ImageIcon iconLandare = new ImageIcon(iconResize);

			JLabel lblLogo = new JLabel("");
			lblLogo.setBounds(0, 0, 116, 121);
			lblLogo.setIcon(iconLandare);
			panel.add(lblLogo);

			JLabel lblNewLabel_3 = new JLabel(zureLehiaketak.get(i).getIzena());
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_3.setBounds(156, 11, 148, 14);
			panel.add(lblNewLabel_3);

			JButton btnIkusi = new JButton("Ikusi lehiaketa");
			btnIkusi.setBounds(140, 87, 150, 23);
			panel.add(btnIkusi);

			// Lehiaketaren informazio guztia ikusteko listener-a
			btnIkusi.addActionListener(e -> {

				List<Atala> atalak = new ArrayList<Atala>();

				try {
					atalak = lehiaketaDB.lortuAtalakIzena(lehiaketa.getLehiaketaID());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Datu basean errorea");
				}

				// Lehiaketaren izena aldatu aukeratutakoen arabera
				lblIzena.setText(lehiaketa.getIzena());

				// Combo-box ean aurreko elementuak ezabatu
				comboAtala.removeAllItems();

				// Combo box-ean atal ezberdinak gehitu
				for (int j = 0; atalak.size() > j; j++) {
					comboAtala.addItem(atalak.get(j).getIzena());
				}

				final int[] currentIndex = { 0 };

				comboAtala.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Botoi ezberdinetako listener-ak garbitu ez errepikatzeko
						actionListenerGarbitu(btnEskubi);
						actionListenerGarbitu(btnEzker);
						actionListenerGarbitu(btnIgo);
						actionListenerGarbitu(btnEzabatu);
						actionListenerGarbitu(btnAteraLehiaketatik);
						actionListenerGarbitu(bisoreaBtn);

						if (epailea) {
							actionListenerGarbitu(btnBozkatu);
						}

						// Combo-box-ean index-a aukeratu
						int selectedIndex = comboAtala.getSelectedIndex();

						// Index 0 edo 0 baino handiago izan behar du (comboBox-etan -1 balorea azaltzen
						// da hutsik dagoenean)
						if (selectedIndex >= 0) {

							Atala atala = new Atala(comboAtala.getItemAt(selectedIndex));

							// Aukeratutako atalaren saria zein den erakutsiko duena
							try {
								lblSaria.setText("SARIA: " + lehiaketaDB.getSariaFromAtala(atala));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Errorea: Datu baseko errorea");
							}

							// Argazki bat igotzeko listener-a
							btnIgo.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {

									// lbl-a hutsik egon behar du
									if (lblZureArgazkia.getIcon() == null) {

										// File chooser bat ireki aukeraketa egiteko
										JFileChooser fileChooser = new JFileChooser();
										fileChooser.setDialogTitle("Igo argazkia");

										// PNG eta JPG fitxategiak hautatzeko
										FileNameExtensionFilter filter = new FileNameExtensionFilter(
												"JPG eta PNG irudiak", "jpg", "jpeg", "png");
										fileChooser.setFileFilter(filter);

										// Aukeratutako fitxategia
										int aukera = fileChooser.showOpenDialog(null);

										// Egokia bada, fitxategia gorde
										if (aukera == JFileChooser.APPROVE_OPTION) {
											File fitxAukera = fileChooser.getSelectedFile();
											String izenaFitx = fitxAukera.getName();

											try {

												// Fitxategiaren helbidea lortu
												String folderPath = URLDecoder.decode(
														getClass().getResource("/" + atala.getIzena()).getFile(),
														"UTF-8");

												// Fitxategi bat sortu (ez badago sortuta berria sortu)
												File folder = new File(folderPath);
												folder.mkdirs();

												// Irudia gorde datu basean eta dagokion erabiltzailearkein
												irudiakDB.irudiaGorde(erabiltzailea, atala,
														"/" + atala.getIzena() + "/" + izenaFitx);

												File outputFile = new File(folder, izenaFitx);

												// Irudia classpath-ean gorde, datu basean bakarrik kokapena gordetzen
												// da eta
												try (FileInputStream inputStream = new FileInputStream(fitxAukera);
														FileOutputStream outputStream = new FileOutputStream(
																outputFile)) {
													byte[] buffer = new byte[1024];
													int length;
													while ((length = inputStream.read(buffer)) != -1) {
														outputStream.write(buffer, 0, length);
													}
												} catch (IOException e1) {
													e1.printStackTrace();
												}
											} catch (UnsupportedEncodingException e1) {
												e1.printStackTrace();
											} catch (SQLException e2) {
												e2.printStackTrace();
											}
										}

									} else {
										JOptionPane.showMessageDialog(null, "Errorea: Irudia dagoeneko kargatuta dago");
										return;
									}

								}
							});

							// Erabiltzaileak igo duen argazkia ezabatzeko listener-a
							btnEzabatu.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									// Konprobatu ea ikonorik dagoen
									if (lblZureArgazkia.getIcon() != null) {

										// Erabiltzaileari galdetu ea irudia ezabatu nahi duen ala ez
										int erantzuna = JOptionPane.showConfirmDialog(null,
												"Seguro ezabatu nahi duzula zure irudia?", "Balidatu",
												JOptionPane.YES_NO_OPTION);

										// erantzuna == JOptionPane.YES_OPTION)
										if (erantzuna == JOptionPane.YES_OPTION) {

											// Irudiaren helbidea lortu classpath-ean
											java.net.URL imageUrl = null;
											try {
												imageUrl = getClass().getResource(
														irudiakDB.getErabiltzaileaIrudia(atala, erabiltzailea));
												irudiakDB.ezabatuArgazkia(atala, erabiltzailea);
												JOptionPane.showMessageDialog(null, "Irudia ezabatu da!",
														"konfirmazioa", JOptionPane.INFORMATION_MESSAGE);
											} catch (SQLException e1) {
												e1.printStackTrace();
											}
										} else {
											JOptionPane.showMessageDialog(null, "Irudia ez da aurkitu", "Errorea",
													JOptionPane.ERROR_MESSAGE);
										}

										// Label-etik irudia kendu
										lblZureArgazkia.setIcon(null);
									}

									else {
										JOptionPane.showMessageDialog(null, "Ez dago irudirik ezabatzeko", "Errorea",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							});

							// Lehiaketa batetik ateratzeko botoiaren listener-a
							btnAteraLehiaketatik.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									try {
										// Datu baseari deitu lehiaketatik ateratzeko erabiltzaile konkretua
										lehiaketaDB.ezabatuLehiaketakoErabiltzailea(erabiltzailea, lehiaketa);
										zureLehiaketak.remove(index);
										JOptionPane.showMessageDialog(null, "Lehiaketatik atera zara", "Konfirmazioa",
												JOptionPane.INFORMATION_MESSAGE);
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "Datu base errorea", "Errorea",
												JOptionPane.ERROR_MESSAGE);
									}

									// Berriz erakutsi lehiaketak berriarenkin
									erakutsiLehiaketak(zureLehiaketak, panelZureLehiaketak, erabiltzailea, epailea);
									repaint();
									revalidate();
								}
							});

							List<String> kokapenak = null;
							String kokapenaIrudia = null;

							try {
								kokapenak = irudiakDB.getIrudiak(atala.getIzena());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							// Datu basetik, erabiltzailearen irudiaren informazioa lortzen da
							try {
								kokapenaIrudia = irudiakDB.getErabiltzaileaIrudia(atala, erabiltzailea);

								if (kokapenaIrudia != null) {
									ImageIcon zureIrudia = irudiaAjustatu(irudiakDB.cargarImageIcon(kokapenaIrudia));
									lblZureArgazkia.setIcon(zureIrudia);
								} else {

									// Ez bada ezer aurkitzen, mezu bat azaldu
									lblZureArgazkia.setIcon(null);
									lblZureArgazkia.setText("Ez duzu sartu irudirik kategoria honetan :(");
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Datu basean errorea", "Errorea",
										JOptionPane.ERROR_MESSAGE);
							} catch (NullPointerException e1) {

								// Ez bada ezer aurkitzen (nullPointer errorea), mezu bat azaldu
								lblZureArgazkia.setIcon(null);
								lblZureArgazkia.setText("Ez duzu sartu irudirik kategoria honetan :(");
							}

							// Lortu diren kokapen guztien irudiak imageIcon bihurtu
							List<ImageIcon> irudiak = irudiakDB.kargatuImageIcons(kokapenak);

							// Combo box-eko aukeraketaren arabera irudia lortu
							if (selectedIndex < irudiak.size()) {

								// Irudia lortu
								ImageIcon irudia = irudiak.get(selectedIndex);

								// Oraingo kokapenaren balorea lortu
								final String[] kokapenakFinal = new String[kokapenak.size()];
								kokapenak.toArray(kokapenakFinal);

								// Epailea bada, btn bozkatu listener-a gehitu
								if (epailea) {
									btnBozkatu.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											try {
												// Oraingo kokapena lortu
												String selectedKokapena = kokapenakFinal[selectedIndex];

												// Kokapen horren argazkia lortu
												int argazkiaID = irudiakDB.lortuArgazkiaID(selectedKokapena);

												// Argazkia existitzen bada, botoa gehitu
												if (argazkiaID != -1) {
													botoak.botoaEman(
															erabiltzaileakDB.getErabiltzaileaID(
																	erabiltzailea.getErabiltzaileIzena()),
															argazkiaID, lehiaketa.getLehiaketaID());
												} else {
													JOptionPane.showMessageDialog(null, "Errorea: Ez da aurkitu irudia",
															"Errorea", JOptionPane.ERROR_MESSAGE);
												}
											} catch (SQLException e1) {
												e1.printStackTrace();
											}
										}
									});
								}

								// Argazkietako informazioa jarri
								try {
									// Egilea eta boto kantitatea
									lblEgilea.setText("Egilea: "
											+ irudiakDB.getErabiltzaileaIzenaAbizena(kokapenak.get(selectedIndex)));
									lblBotoak.setText(
											"Boto kantitatea: " + irudiakDB.lortuBotoak(kokapenak.get(selectedIndex)));
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, "Datu basean errorea", "Errorea",
											JOptionPane.ERROR_MESSAGE);
								}

								// Irudiak jarri
								Image img = irudia.getImage();
								Image nuevaImg = img.getScaledInstance(lblArgazkiak.getWidth(),
										lblArgazkiak.getHeight(), Image.SCALE_SMOOTH);
								irudia = new ImageIcon(nuevaImg);
								lblArgazkiak.setIcon(irudia);
								bisoreaBtn.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {

										ImageIcon originalIcon = irudiakDB
												.cargarImageIcon(kokapenakFinal[selectedIndex]);
										JFrame ventanaImagen = new JFrame("Imagen");
										JLabel lblImagen = new JLabel(originalIcon);
										ventanaImagen.getContentPane().add(lblImagen);
										ventanaImagen.pack();
										ventanaImagen.setLocationRelativeTo(null);
										ventanaImagen.setVisible(true);
									}
								});

							} else {
								lblArgazkiak.setIcon(null);
								lblArgazkiak.setText("Ez dira argazkirik igo oraindik atal honetan.");
							}

							// Botoietako action listenerrak garbitzen dira
							actionListenerGarbitu(btnEzker);
							actionListenerGarbitu(btnEskubi);

							final String[] kokapenakFinal = new String[kokapenak.size()];
							kokapenak.toArray(kokapenakFinal);

							// Irudiak ezkerrera joateko
							btnEzker.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {

									// Konprobatzen da index a eta kendu egiten da hurrengoa azaltzeko
									currentIndex[0]--;
									if (currentIndex[0] < 0) {
										currentIndex[0] = irudiak.size() - 1;
									}

									ImageIcon irudiBerria = null;

									// Index horren irudi berria kargatzen da bere egile eta botoekin
									try {
										irudiBerria = irudiaAjustatu(irudiak.get(currentIndex[0]));
										try {
											lblEgilea.setText("Egilea: " + irudiakDB
													.getErabiltzaileaIzenaAbizena(kokapenakFinal[currentIndex[0]]));
											lblBotoak.setText("Boto kantitatea: "
													+ irudiakDB.lortuBotoak(kokapenakFinal[currentIndex[0]]));
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, "Datu basean errorea", "Errorea",
													JOptionPane.ERROR_MESSAGE);
										}
									} catch (IndexOutOfBoundsException e2) {
										JOptionPane.showMessageDialog(null, "Ez daude argazkirik");
										return;
									}

									lblArgazkiak.setIcon(irudiBerria);
									actionListenerGarbitu(bisoreaBtn);
									bisoreaBtn.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {

											ImageIcon originalIcon = irudiakDB
													.cargarImageIcon(kokapenakFinal[currentIndex[0]]);
											JFrame lehioIrudia = new JFrame("Imagen");
											JLabel lblImagen = new JLabel(originalIcon);
											lehioIrudia.getContentPane().add(lblImagen);
											lehioIrudia.pack();
											lehioIrudia.setLocationRelativeTo(null);
											lehioIrudia.setVisible(true);
										}
									});
								}
							});

							// Irudiak eskuinera joateko
							btnEskubi.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {

									// Konprobatzen da index a eta gehitu egiten da hurrengoa azaltzeko
									currentIndex[0]++;
									if (currentIndex[0] >= irudiak.size()) {
										currentIndex[0] = 0;
									}

									ImageIcon irudiBerria = null;

									// Irudi berria cargatzen da bere egile eta botoekin
									try {
										irudiBerria = irudiaAjustatu(irudiak.get(currentIndex[0]));
										try {
											lblEgilea.setText("Egilea: " + irudiakDB
													.getErabiltzaileaIzenaAbizena(kokapenakFinal[currentIndex[0]]));
											lblBotoak.setText("Boto kantitatea: "
													+ irudiakDB.lortuBotoak(kokapenakFinal[currentIndex[0]]));
										} catch (SQLException e1) {
											JOptionPane.showMessageDialog(null, "Datu basean errorea", "Errorea",
													JOptionPane.ERROR_MESSAGE);
										}
									} catch (IndexOutOfBoundsException e2) {
										JOptionPane.showMessageDialog(null, "Ez daude argazkirik");
										return;
									}

									lblArgazkiak.setIcon(irudiBerria);
									actionListenerGarbitu(bisoreaBtn);
									bisoreaBtn.addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {

											ImageIcon originalIcon = irudiakDB
													.cargarImageIcon(kokapenakFinal[currentIndex[0]]);
											JFrame lehioIrudia = new JFrame("Imagen");
											JLabel lblImagen = new JLabel(originalIcon);
											lehioIrudia.getContentPane().add(lblImagen);
											lehioIrudia.pack();
											lehioIrudia.setLocationRelativeTo(null);
											lehioIrudia.setVisible(true);
										}
									});
								}
							});
						}
					}
				});
			});

			// Deskribapena hemen azalduko da.
			JTextArea txtDeskripzioa = new JTextArea();
			txtDeskripzioa.setFont(new Font("Arial", Font.PLAIN, 11));
			String deskripzioa = zureLehiaketak.get(i).getDeskribapena();
			txtDeskripzioa.setLineWrap(true);
			txtDeskripzioa.setWrapStyleWord(true);
			txtDeskripzioa.setText(deskripzioa);
			txtDeskripzioa.setEditable(false);
			txtDeskripzioa.setBackground(new Color(248, 248, 255));
			txtDeskripzioa.setBounds(126, 36, 189, 74);
			panel.add(txtDeskripzioa);

			panelZureLehiaketak.add(Box.createVerticalStrut(15));
		}
	}

	/**
	 * Irudia ajustatatzeko metodoa, irudi bat pasatzen da parametrotzat eta
	 * reeskalatu egiten du argazkiak labelean.
	 *
	 * @param irudia the irudia
	 * @return the image icon
	 */
	private ImageIcon irudiaAjustatu(ImageIcon irudia) {
		Image img = irudia.getImage();
		Image nuevaImg = img.getScaledInstance(lblArgazkiak.getWidth(), lblArgazkiak.getHeight(), Image.SCALE_SMOOTH);
		return new ImageIcon(nuevaImg);
	}

	/**
	 * Action listener garbitu. Action listener bat ezabatzeko botoit batena.
	 *
	 * @param boton the boton
	 */
	private void actionListenerGarbitu(JButton boton) {
		for (ActionListener listener : boton.getActionListeners()) {
			boton.removeActionListener(listener);
		}
	}
}
