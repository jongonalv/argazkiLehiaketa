package Interfazea;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import DatuBasea.irudiakDB;
import DatuBasea.lehiaketaDB;
import Interfazea.taulaLehiaketa.taulaInfo;
import programaKlaseak.Atala;
import programaKlaseak.Erabiltzailea;
import programaKlaseak.Lehiaketa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class kudeatzaileakInterfazea.
 */
public class kudeatzaileakInterfazea extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The card layout. */
	private CardLayout cardLayout;
	
	/** The lehiaketak ikusi panel. */
	private JPanel lehiaketakIkusiPanel;
	
	/** The lehiaketa sortu. */
	private JPanel lehiaketaSortu;
	
	/** The atalak sortu. */
	private JPanel atalakSortu;
	
	/** The erabiltzaileak. */
	private JPanel erabiltzaileak;
	
	/** The argazkiak ikusi. */
	private JPanel argazkiakIkusi;
	
	/** The helbidea irudia. */
	private String helbideaIrudia;
	
	/** The helbide absolutua. */
	private String helbideAbsolutua;
	
	/** The izena field. */
	private JTextField izenaField;
	
	/** The data hasiera field. */
	private JTextField dataHasieraField;
	
	/** The data bukaera field. */
	private JTextField dataBukaeraField;
	
	/** The deskribapena area. */
	private JTextArea deskribapenaArea;
	
	/** The logotipo button. */
	private JButton logotipoButton;
	
	/** The taula atalak info. */
	private taulaInfo taulaAtalakInfo = null;

	/**
	 * Instantiates a new kudeatzaileak interfazea.
	 *
	 * @param administratzaileaDa the administratzailea da
	 * @param erabiltzailea the erabiltzailea
	 */
	public kudeatzaileakInterfazea(boolean administratzaileaDa, Erabiltzailea erabiltzailea) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JLabel lblErabiltzailea;

		// adminitratzailea den ala ez arabera, textu bat edo beste jarri
		String labelText = administratzaileaDa ? "Administrazailea" : "Kudeatzaileak";

		// Label-a sortu goiko informazioaren arabera
		lblErabiltzailea = new JLabel(labelText);
		lblErabiltzailea.setFont(new Font(lblErabiltzailea.getFont().getName(), Font.BOLD, 40));
		lblErabiltzailea.setForeground(Color.BLACK);

		// Erabiltzailearen izena beran jarri
		JLabel subTitle = new JLabel(erabiltzailea.getErabiltzaileIzena());
		subTitle.setFont(new Font(subTitle.getFont().getName(), Font.PLAIN, 20));
		subTitle.setForeground(Color.GRAY);

		// Panelera gehitu tituloak
		JPanel zentralPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 5, 0);
		zentralPanel.add(lblErabiltzailea, gbc);

		gbc.gridy = 1;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(5, 0, 0, 0);
		zentralPanel.add(subTitle, gbc);

		contentPane.add(zentralPanel, BorderLayout.CENTER);

		cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel(cardLayout);

		lehiaketakIkusiPanel = new JPanel(new BorderLayout());

		try {
			taulaInfo taula = taulaLehiaketa.taulaSortu("SELECT * FROM Lehiaketa");

			JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel titleLabel = new JLabel("Lehiaketak aldatu");
			titleLabel.setFont(new Font(subTitle.getFont().getName(), Font.BOLD, 28));
			titlePanel.add(titleLabel);

			lehiaketakIkusiPanel.add(titlePanel, BorderLayout.NORTH);
			lehiaketakIkusiPanel.add(taula.getTable(), BorderLayout.CENTER);
			lehiaketakIkusiPanel.add(
					taulaLehiaketa.botoiGorde(taula, "UPDATE Lehiaketa SET ", " WHERE lehiaketa_ID = "),
					BorderLayout.SOUTH);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		lehiaketaSortu = new JPanel(new GridLayout(0, 2, 10, 10));

		JLabel izenaLabel = new JLabel("Izena:");
		JLabel dataHasieraLabel = new JLabel("Data hasiera (YYYY-MM-DD):");
		JLabel dataBukaeraLabel = new JLabel("Data bukaera (YYYY-MM-DD):");
		JLabel deskribapenaLabel = new JLabel("Deskribapena:");
		JLabel logotipoaLabel = new JLabel("Logotipoa:");

		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		izenaLabel.setFont(font);
		dataHasieraLabel.setFont(font);
		dataBukaeraLabel.setFont(font);
		deskribapenaLabel.setFont(font);
		logotipoaLabel.setFont(font);

		izenaField = new JTextField();
		dataHasieraField = new JTextField();
		dataBukaeraField = new JTextField();
		deskribapenaArea = new JTextArea();
		logotipoButton = new JButton("Logotipo hautatu");

		logotipoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Fitxategiak aukeratzeko paketea erabili eta filtroa irudiak aukeratzeko
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Irudiak", "jpg", "png"));
				int result = fileChooser.showOpenDialog(null);

				// Aukeratu bada, fitxategia, izena, eta helbidea lortu
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String filePath = selectedFile.getAbsolutePath();
					String izena = selectedFile.getName();

					// Classpath-eko helbidea lortu
					helbideaIrudia = "/logotipoak/" + izena;

					// Helbide nagusia lortu
					helbideAbsolutua = filePath;
				}
			}
		});

		// Lehiuaketa bat sortzeko erabiliko diren elementuak
		lehiaketaSortu.add(izenaLabel);
		lehiaketaSortu.add(izenaField);
		lehiaketaSortu.add(dataHasieraLabel);
		lehiaketaSortu.add(dataHasieraField);
		lehiaketaSortu.add(dataBukaeraLabel);
		lehiaketaSortu.add(dataBukaeraField);
		lehiaketaSortu.add(deskribapenaLabel);
		lehiaketaSortu.add(new JScrollPane(deskribapenaArea));
		lehiaketaSortu.add(logotipoaLabel);
		lehiaketaSortu.add(logotipoButton);

		// Lehiaketa sortzeko botoia eta bere konfigurazioa
		JButton lehiaketaSortuButton = new JButton("Lehiaketa Sortu");

		lehiaketaSortuButton.setBackground(new Color(30, 144, 255));
		lehiaketaSortuButton.setForeground(Color.WHITE);
		lehiaketaSortuButton.setFont(new Font("Arial", Font.BOLD, 14));
		lehiaketaSortuButton.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 1),
						BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		// Lehiaketa sortu ematerakoan aktibatuko diren ekintzat
		lehiaketaSortuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Testuak beteta dauden ala data formatu egokia dagoen konprobatuko duena
				if (!testuakBeteta() || !dataFormatua(dataHasieraField.getText())
						|| !dataFormatua(dataBukaeraField.getText())) {
					JOptionPane.showMessageDialog(null, "Datu guztiak beteta izan behar dira (datak UUUU-MM-DD)");
					return;
				}

				// Lehiaketa sortu datu ezberdinekin
				try {

					// Informazioa lortu field ezberdinetatik erabiltzaileak bertan idatziko due ta
					String izena = izenaField.getText();
					String dataHasiera = dataHasieraField.getText();
					String dataBukaera = dataBukaeraField.getText();
					String deskribapena = deskribapenaArea.getText();

					// logotipoak gordetzen diren helbidea lortu gero bertan gordetzeko irudia
					String folderPath = getClass().getClassLoader().getResource("logotipoak").getPath();
					folderPath = URLDecoder.decode(folderPath, "UTF-8");

					// Konprobatu existitzen den, bestela, sortu direktorioa (mkdirs)
					File direktorioa = new File(folderPath);
					if (!direktorioa.exists()) {
						direktorioa.mkdirs();
					}

					// Aukeratutako helbidea jarri (logotipoa listeneretik lortutakoa)
					File selectedFile = new File(helbideAbsolutua);
					String fileName = selectedFile.getName();

					// Ez bada existitzen, exception bat bota
					if (!selectedFile.exists()) {
						throw new FileNotFoundException("Ez da existitzen hurrengo helbidea: " + helbideAbsolutua);
					}

					// Existitzen bada, betan gorde irudia
					FileInputStream inputStream = new FileInputStream(selectedFile);
					OutputStream outputStream = new FileOutputStream(folderPath + File.separator + fileName);

					// Irudia gorde buffer bat erabiliz
					byte[] buffer = new byte[1024];
					int length;
					while ((length = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, length);
					}

					inputStream.close();
					outputStream.close();

					// Lehiaketa gorde datu basean
					lehiaketaDB.insertLehiaketa(izena, dataHasiera, dataBukaera, deskribapena, helbideaIrudia);

				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(lehiaketaSortuButton);

		lehiaketaSortu.add(buttonPanel);
		lehiaketaSortu.add(new JPanel());

		atalakSortu = createLehiaketaSortuPanel();

		erabiltzaileak = new JPanel(new BorderLayout());

		try {
			taulaInfo taula = taulaLehiaketa.taulaSortu("SELECT * FROM Erabiltzailea");

			JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel titleLabel = new JLabel("Erabiltzaileak ikusi");
			titleLabel.setFont(new Font(subTitle.getFont().getName(), Font.BOLD, 28));
			titlePanel.add(titleLabel);

			erabiltzaileak.add(titlePanel, BorderLayout.NORTH);
			erabiltzaileak.add(taula.getTable(), BorderLayout.CENTER);
			erabiltzaileak.add(
					taulaLehiaketa.botoiGorde(taula, "UPDATE Erabiltzailea SET ", " WHERE erabiltzailea_ID = "),
					BorderLayout.SOUTH);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JScrollPane scrollPane1 = new JScrollPane(erabiltzaileak);

		argazkiakIkusi = new JPanel(new BorderLayout());

		// combobox-entzeko panel-a sortu
		JPanel comboPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		gbc1.anchor = GridBagConstraints.EAST;
		gbc1.insets = new Insets(5, 5, 5, 5);

		// Lehiaketa eta atala label-ak
		JLabel lehiaketaLabel = new JLabel("Lehiaketa:");
		lehiaketaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel atalaLabel = new JLabel("Atala:");
		atalaLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		// Combobox-ak sortu lehiaketen eta atalen informazioa gordetzeko
		JComboBox<String> lehiaketaComboBox = new JComboBox<>();
		fillComboBox(lehiaketaComboBox);

		JComboBox<String> atalaComboBox = new JComboBox<>();

		// Lehiaketaren izenaren arabera comboBox-eko atalak azaltu
		lehiaketaComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Atalak combobox-eko gauzak lortu
				try {
					String lehiaketaIzena = (String) lehiaketaComboBox.getSelectedItem();
					int id = lehiaketaDB.getLehiaketaIDByIzena(lehiaketaIzena);
					java.util.List<Atala> atalak = lehiaketaDB.lortuAtalakIzena(id);

					// garbitu
					atalaComboBox.removeAllItems();

					// elementuak gehitu
					for (Atala atala : atalak) {
						atalaComboBox.addItem(atala.getIzena());
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Datu basean errorea");
				}

			}
		});

		lehiaketaComboBox.setPreferredSize(new Dimension(150, lehiaketaComboBox.getPreferredSize().height));
		atalaComboBox.setPreferredSize(new Dimension(150, atalaComboBox.getPreferredSize().height));

		comboPanel.add(lehiaketaLabel, gbc1);
		gbc1.gridx++;
		comboPanel.add(lehiaketaComboBox, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy++;
		comboPanel.add(atalaLabel, gbc1);
		gbc1.gridx++;
		comboPanel.add(atalaComboBox, gbc1);

		JLabel titleLabel = new JLabel("Erabiltzaileen argazkiak");
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 28));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));

		JLabel egileaLabel = new JLabel("Egilea:");
		egileaLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));

		JLabel botoKantitateaLabel = new JLabel("Boto kantitatea:");
		botoKantitateaLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));

		JButton saridunaButton = new JButton("Sariduna");
		saridunaButton.setBackground(Color.YELLOW); // Establecer el color de fondo amarillo

		ImageIcon flechaIzquierdaIcon = new ImageIcon(getClass().getResource("/Irudiak/ezkerra.png"));
		Image flechaIzquierdaImg = flechaIzquierdaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Icon flechaIzquierdaScaledIcon = new ImageIcon(flechaIzquierdaImg);

		ImageIcon flechaDerechaIcon = new ImageIcon(getClass().getResource("/Irudiak/eskubi.png"));
		Image flechaDerechaImg = flechaDerechaIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Icon flechaDerechaScaledIcon = new ImageIcon(flechaDerechaImg);

		JButton flechaIzquierdaButton = new JButton(flechaIzquierdaScaledIcon);
		JButton flechaDerechaButton = new JButton(flechaDerechaScaledIcon);

		panelPrincipal.add(egileaLabel);
		panelPrincipal.add(Box.createHorizontalStrut(200));
		panelPrincipal.add(botoKantitateaLabel);
		panelPrincipal.add(Box.createHorizontalStrut(200));
		panelPrincipal.add(saridunaButton);
		panelPrincipal.add(Box.createHorizontalStrut(200));
		panelPrincipal.add(flechaIzquierdaButton);
		panelPrincipal.add(Box.createHorizontalStrut(10));
		panelPrincipal.add(flechaDerechaButton);

		// Use a more descriptive variable name
		JLabel imageLabel = new JLabel();
		final int[] currentIndex = { 0 };

		atalaComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				currentIndex[0] = 0;

				// Datu basetik irudi guztien kokapenak atera
				List<String> kokapenak = null;

				try {
					kokapenak = irudiakDB.getIrudiak((String) atalaComboBox.getSelectedItem());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				// Lortu diren kokapen guztien irudiak imageIcon bihurtu
				List<ImageIcon> irudiak = irudiakDB.kargatuImageIcons(kokapenak);

				if (!irudiak.isEmpty()) {
				    JLabel imageLabel = new JLabel();
				    argazkiakIkusi.add(imageLabel, BorderLayout.CENTER);
				    imageLabel.setIcon(irudiaAjustatu(irudiak.get(currentIndex[0]), 400, 400));
				} else {
				    System.out.println("Error: irudiak list is empty");
				}
				
				final String[] kokapenakFinal = new String[kokapenak.size()];
				kokapenak.toArray(kokapenakFinal);
				
				actionListenerGarbitu(flechaDerechaButton);
				actionListenerGarbitu(flechaIzquierdaButton);

				// Irudiak eskuinera joateko
				flechaDerechaButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Konprobatzen da index a eta gehitu egiten da hurrengoa azaltzeko
						currentIndex[0]++;
						if (currentIndex[0] >= irudiak.size()) {
							currentIndex[0] = 0;
						}

						ImageIcon irudiBerria = null;
						try {
							irudiBerria = irudiaAjustatu(irudiak.get(currentIndex[0]), 400, 400);
							try {
								egileaLabel.setText("Egilea: " + irudiakDB
										.getErabiltzaileaIzenaAbizena(kokapenakFinal[currentIndex[0]]));
								botoKantitateaLabel.setText("Boto kantitatea: "
										+ irudiakDB.lortuBotoak(kokapenakFinal[currentIndex[0]]));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Datu basean errorea", "Errorea",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (IndexOutOfBoundsException e2) {
							JOptionPane.showMessageDialog(null, "Ez daude argazkirik");
							return;
						}

						imageLabel.setIcon(irudiBerria);
					}
				});
				
				// Irudiak eskuinera joateko
				flechaIzquierdaButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Konprobatzen da index a eta gehitu egiten da hurrengoa azaltzeko
						currentIndex[0]--;
						if (currentIndex[0] < 0) {
							currentIndex[0] = irudiak.size() - 1;
						}

						ImageIcon irudiBerria = null;
						try {
							irudiBerria = irudiaAjustatu(irudiak.get(currentIndex[0]), 400, 400);
							try {
								egileaLabel.setText("Egilea: " + irudiakDB
										.getErabiltzaileaIzenaAbizena(kokapenakFinal[currentIndex[0]]));
								botoKantitateaLabel.setText("Boto kantitatea: "
										+ irudiakDB.lortuBotoak(kokapenakFinal[currentIndex[0]]));
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Datu basean errorea", "Errorea",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (IndexOutOfBoundsException e2) {
							JOptionPane.showMessageDialog(null, "Ez daude argazkirik");
							return;
						}

						imageLabel.setIcon(irudiBerria);
					}
				});

			}
		});

		argazkiakIkusi.add(panelPrincipal, BorderLayout.SOUTH);
		argazkiakIkusi.add(titleLabel, BorderLayout.NORTH);
		argazkiakIkusi.add(comboPanel, BorderLayout.WEST);
		argazkiakIkusi.add(Box.createHorizontalStrut(10), BorderLayout.EAST);

		cardPanel.add(lehiaketaSortu, "lehiaketaSortu");

		cardPanel.add(zentralPanel, "titlePanel");
		cardPanel.add(lehiaketakIkusiPanel, "lehiaketakIkusi");
		cardPanel.add(lehiaketaSortu, "lehiaketaSortu");
		cardPanel.add(atalakSortu, "atalakSortu");
		cardPanel.add(scrollPane1, "erabiltzaileakIkusi");
		cardPanel.add(argazkiakIkusi, "argazkiak");

		contentPane.add(cardPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnLehiaketak = new JMenu("Lehiaketak");
		mnLehiaketak.setBackground(new Color(255, 255, 255));
		menuBar.add(mnLehiaketak);

		// Item ezberdinetako informazioa ikusteko
		JMenuItem itemLehiaketak = new JMenuItem("Lehiaketak ikusi");
		itemLehiaketak.setForeground(new Color(0, 0, 0));
		mnLehiaketak.add(itemLehiaketak);
		itemLehiaketak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "lehiaketakIkusi");
			}
		});

		JMenuItem itemSortu = new JMenuItem("Lehiaketa sortu");
		mnLehiaketak.add(itemSortu);
		itemSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "lehiaketaSortu");
			}
		});

		JMenuItem itemAtalak = new JMenuItem("Atalak ikusi");
		mnLehiaketak.add(itemAtalak);
		itemAtalak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "atalakSortu");
			}
		});

		// Menú "Erabiltzaileak"
		JMenu mnErabiltzaileak = new JMenu("Erabiltzaileak");
		menuBar.add(mnErabiltzaileak);

		JMenuItem itemErabiltzaileak = new JMenuItem("Erabiltzaileak ikusi");
		mnErabiltzaileak.add(itemErabiltzaileak);
		itemErabiltzaileak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "erabiltzaileakIkusi");
			}
		});

		JMenuItem mntmArgazkiakIkusi = new JMenuItem("Argazkiak ikusi");
		mnErabiltzaileak.add(mntmArgazkiakIkusi);
		mntmArgazkiakIkusi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "argazkiak");
			}
		});

		// Kudeatzailea administratzailea bada, erabiltzaileen lehioa ikusteko aukera
		// eman
		// Horrela, sistema osorako sarbidea izango du.
		if (administratzaileaDa) {
			JMenuItem erabiltzaileaBista = new JMenuItem("Erabiltzailea bista");
			menuBar.add(erabiltzaileaBista);
			erabiltzaileaBista.setHorizontalAlignment(SwingConstants.CENTER);
			erabiltzaileaBista.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					erabiltzaileaLehioa erabiltzaileaLehioa = new erabiltzaileaLehioa(administratzaileaDa,
							erabiltzailea);
					erabiltzaileaLehioa.setVisible(true);
				}
			});
		}

		JMenuItem mntmAtera = new JMenuItem("Sistematik atera");
		menuBar.add(mntmAtera);
		mntmAtera.setHorizontalAlignment(SwingConstants.CENTER);
		mntmAtera.addActionListener(e -> System.exit(0));
	}

	/**
	 * Testu kutxak beterik dauden konprobatzen duen metodoa.
	 *
	 * @return true, if successful
	 */
	private boolean testuakBeteta() {
		return !izenaField.getText().isEmpty() && !dataHasieraField.getText().isEmpty()
				&& !dataBukaeraField.getText().isEmpty() && !deskribapenaArea.getText().isEmpty()
				&& helbideaIrudia != null;
	}

	/**
	 * Dataren formatua egokia den konprobatzen duen metodoa. UUUU-MM-DD
	 *
	 * @param data konprobatu nahi den data
	 * @return true, formatua egokia bada
	 */
	private boolean dataFormatua(String data) {

		if (data.length() != 10)
			return false;

		if (data.charAt(4) != '-' || data.charAt(7) != '-')
			return false;

		for (int i = 0; i < data.length(); i++) {
			if (i != 4 && i != 7 && !Character.isDigit(data.charAt(i)))
				return false;
		}

		return true;
	}

	/**
	 * Atalak sortzeko panela egiteko metodoa, Bere funtzionalitate guztiekin.
	 *
	 * @return the Atalen panela
	 */
	private JPanel createLehiaketaSortuPanel() {
		JPanel lehiaketaSortuPanel = new JPanel();
		lehiaketaSortuPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Añadir un pequeño espacio entre los componentes

		// JLabel "Aukeratu Lehiaketa"
		JLabel aukeratuLabel = new JLabel("Aukeratu Lehiaketa eta beran azalduko dira atalak editatzeko:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;
		lehiaketaSortuPanel.add(aukeratuLabel, gbc);

		JComboBox<String> comboBox = new JComboBox<>();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		lehiaketaSortuPanel.add(comboBox, gbc);

		fillComboBox(comboBox);

		JPanel centerPanel = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		lehiaketaSortuPanel.add(centerPanel, gbc);

		// Lehiaketaren atalen datuak azaltzeko listener-a
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				centerPanel.removeAll();
				String lehiaketa;
				int lehiaketaID;

				// Lehiaketaren dautekin taula berria sortzen da
				try {
					lehiaketa = (String) comboBox.getSelectedItem();
					lehiaketaID = lehiaketaDB.getLehiaketaIDByIzena(lehiaketa);
					taulaAtalakInfo = taulaLehiaketa
							.taulaSortu("SELECT * FROM Atala WHERE lehiaketa_ID = " + lehiaketaID);
					centerPanel.repaint();
					centerPanel.revalidate();
					setVisible(true);
					JButton atalakTaulaEguneratuButton = taulaLehiaketa.botoiGorde(taulaAtalakInfo, "UPDATE Atala SET ",
							" WHERE atala_ID = ");
					gbc.gridx = 1;
					lehiaketaSortuPanel.add(atalakTaulaEguneratuButton, gbc);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				centerPanel.add(taulaAtalakInfo.getTable());

			}
		});

		JLabel izenaLabel = new JLabel("Izena:");
		JTextField izenaField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.EAST;
		lehiaketaSortuPanel.add(izenaLabel, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		lehiaketaSortuPanel.add(izenaField, gbc);
		gbc.weightx = 0;

		JLabel sariaLabel = new JLabel("Saria:");
		JTextField sariaField = new JTextField();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.EAST;
		lehiaketaSortuPanel.add(sariaLabel, gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		lehiaketaSortuPanel.add(sariaField, gbc);
		gbc.weightx = 0;

		JButton atalaSortuButton = new JButton("Atala Sortu");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		lehiaketaSortuPanel.add(atalaSortuButton, gbc);

		atalaSortuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String izena = null;
				String saria = null;

				// Izena eta sarien informazioa lortu field-etik, hutsik badago, errorea
				if (!izenaField.getText().isEmpty() && !sariaField.getText().isEmpty()) {
					izena = izenaField.getText();
					saria = sariaField.getText();
				} else {
					JOptionPane.showMessageDialog(null, "Testu kutxak beteta izan behar dute atal berri bat sartzeko.");
					return;
				}

				// comboBox ean elementuak badaude atal berria gehitu aukeratutako lehiaketaren
				// informazioarekin
				if (comboBox.getItemCount() > 0) {
					String lehiaketaIzena = (String) comboBox.getSelectedItem();
					int lehiaketaID = -1;
					try {
						lehiaketaID = lehiaketaDB.getLehiaketaIDByIzena(lehiaketaIzena);
						lehiaketaDB.insertAtala(izena, saria, lehiaketaID);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Datu basean errorea");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Ez da aukeratu lehiaketarik. Mesedez, aukeratu lehiaketa bat.");
				}
			}
		});

		return lehiaketaSortuPanel;
	}

	/**
	 * Lehiaketen combobox-a betetzen da lehiaketa izen guztiekin.
	 *
	 * @param comboBox gorde nahi dena
	 */
	public static void fillComboBox(JComboBox<String> comboBox) {
		try {
			// Lehiaketa guztiak lortu
			java.util.List<Lehiaketa> lehiaketak = lehiaketaDB.getAllLehiaketak();

			// Combo-box-a garbitu
			comboBox.removeAllItems();

			// Lehiaketa izen guztiak gehitu
			for (Lehiaketa lehiaketa : lehiaketak) {
				comboBox.addItem(lehiaketa.getIzena());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Irudia ajustatu.
	 *
	 * @param irudia the irudia
	 * @param width the width
	 * @param height the height
	 * @return the image icon
	 */
	private ImageIcon irudiaAjustatu(ImageIcon irudia, int width, int height) {
		Image img = irudia.getImage();
		Image nuevaImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(nuevaImg);
	}
	
	/**
	 * Action listener garbitu.
	 *
	 * @param boton the boton
	 */
	private void actionListenerGarbitu(JButton boton) {
		for (ActionListener listener : boton.getActionListeners()) {
			boton.removeActionListener(listener);
		}
	}

}
