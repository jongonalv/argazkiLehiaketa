package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.Query;

import programaKlaseak.Atala;
import programaKlaseak.Erabiltzailea;
import programaKlaseak.Lehiaketa;

// TODO: Auto-generated Javadoc
/**
 * The Class lehiaketaDB.
 */
public class lehiaketaDB extends Konexioa {

	/**
	 * Lehiaketa guztiak lortzeko kontsulta.
	 *
	 * @return the all lehiaketak
	 * @throws SQLException the SQL exception
	 */
	public static List<Lehiaketa> getAllLehiaketak() throws SQLException {
		return getLehiaketaKontsulta("SELECT * FROM Lehiaketa");
	}

	/**
	 * Erabiltzaile espezifiko bat emanda, parte hartzen duen lehiaketak eman.
	 *
	 * @param erabiltzaile_izena the erabiltzaile izena
	 * @return the erabiltzaile lehiaketak
	 * @throws SQLException the SQL exception
	 */
	public static List<Lehiaketa> getErabiltzaileLehiaketak(String erabiltzaile_izena) throws SQLException {
		int erabiltzaileID = erabiltzaileakDB.getErabiltzaileaID(erabiltzaile_izena);
		String query = "SELECT * FROM Lehiaketa INNER JOIN Parte_hartu ON Parte_hartu.lehiaketa = Lehiaketa.lehiaketa_ID WHERE Parte_hartu.erabiltzailea = ?";
		return getLehiaketaKontsulta(query, erabiltzaileID);
	}

	/**
	 * Lehiaketen informazio guztia lortzen du kontsulta bat emanda.
	 *
	 * @param query  the query
	 * @param params the params
	 * @return the lehiaketak from query
	 * @throws SQLException the SQL exception
	 */
	private static List<Lehiaketa> getLehiaketaKontsulta(String query, Object... params) throws SQLException {
		List<Lehiaketa> lehiaketak = new ArrayList<>();

		try (Connection conn = getKonexioa(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Lehiaketa lehiaketa = lehiaketaSortuKontsulta(resultSet);
					lehiaketak.add(lehiaketa);
				}
			}
		}
		return lehiaketak;
	}

	/**
	 * Kontsulta baten emaitzak eman eta gero, emaitza horien arabera, aldagaiak
	 * sortzen dira eta Lehiaketa objektu berri bat sortzen da kontsultan emandako
	 * datuekin.
	 *
	 * @param resultSet the result set
	 * @return the lehiaketa
	 * @throws SQLException the SQL exception
	 */
	private static Lehiaketa lehiaketaSortuKontsulta(ResultSet resultSet) throws SQLException {
		int lehiaketaID = resultSet.getInt("lehiaketa_ID");
		String izena = resultSet.getString("izena");
		String dataHasiera = resultSet.getString("data_hasiera");
		String dataBukaera = resultSet.getString("data_bukaera");
		String deskribapena = resultSet.getString("deskribapena");
		String logotipoa = resultSet.getString("logotipoa");

		return new Lehiaketa(lehiaketaID, izena, dataHasiera, dataBukaera, deskribapena, logotipoa);
	}

	/**
	 * Erabiltzaile bat lehiaketa batean parte hartzen duen gehitzeko metodoa.
	 * Erabiltzailea eta lehiaketa parametrotzat jasotzen dira eta hauen ID-a
	 * lortzen da taulan gehitzeko.
	 *
	 * @param erabiltzailea the erabiltzailea
	 * @param lehiaketa     the lehiaketa
	 * @throws SQLIntegrityConstraintViolationException the SQL integrity constraint
	 *                                                  violation exception
	 * @throws SQLException                             the SQL exception
	 */
	public static void gehituLehiaketaErabiltzailea(Erabiltzailea erabiltzailea, Lehiaketa lehiaketa)
			throws SQLIntegrityConstraintViolationException, SQLException {

		String query = "INSERT INTO Parte_hartu (erabiltzailea, lehiaketa) VALUES (?, ?)";

		Connection conn = getKonexioa();
		PreparedStatement pstmt = conn.prepareStatement(query);

		// parametroen baloreak jarri
		pstmt.setInt(1, erabiltzaileakDB.getErabiltzaileaID(erabiltzailea.getErabiltzaileIzena()));
		pstmt.setInt(2, lehiaketa.getLehiaketaID());

		// Kontsulta exetutatu
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Lehiaketan parte hartu duzu.");

	}

	/**
	 * Ezabatu lehiaketako erabiltzailea. Metodo honek erabiltzailea lehiaketan ez
	 * duela parte hartzen adierazten du datu basetik ezabatuz
	 *
	 * @param erabiltzailea the erabiltzailea
	 * @param lehiaketa     the lehiaketa
	 * @throws SQLException the SQL exception
	 */
	public static void ezabatuLehiaketakoErabiltzailea(Erabiltzailea erabiltzailea, Lehiaketa lehiaketa)
			throws SQLException {
		String query = "DELETE FROM Parte_hartu WHERE erabiltzailea = ? AND lehiaketa = ?";

		Connection conn = getKonexioa();
		PreparedStatement pstmt = conn.prepareStatement(query);

		pstmt.setInt(1, erabiltzaileakDB.getErabiltzaileaID(erabiltzailea.getErabiltzaileIzena()));
		pstmt.setInt(2, lehiaketa.getLehiaketaID());

		int rowsAffected = pstmt.executeUpdate();
		if (rowsAffected > 0) {
			JOptionPane.showMessageDialog(null, "Zure parte-hartzea lehiaketatik ezabatu da.");
		} else {
			JOptionPane.showMessageDialog(null, "Ezin izan da zure parte-hartzea ezabatu.");
		}
	}

	/**
	 * Lehiaketa baten atal guztiak lortzeko metodoa. Lehiaketa bat parametrotzat
	 * jasotzen du eta lehiaketa horren atal guztiak lortzen dira
	 *
	 * @param lehiaketa the lehiaketa
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	public static List<Atala> lortuAtalakIzena(int lehiaketa) throws SQLException {
		List<Atala> atalak = new ArrayList<>();

		String query = "SELECT izena FROM Atala WHERE lehiaketa_ID = ?";
		Connection conn = getKonexioa();

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, lehiaketa);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					String izena = resultSet.getString("izena");
					Atala atala = new Atala(izena);
					atalak.add(atala);
				}
			}
		}
		return atalak;
	}

	/**
	 * Atala bat jasota parametrotzat, bere ID-a bueltatzen duen metodoa.
	 *
	 * @param atala the atala
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public static int lortuAtalaID(Atala atala) throws SQLException {
		int atalaID = -1;

		String query = "SELECT atala_ID FROM Atala WHERE izena = ?";
		Connection conn = getKonexioa();

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, atala.getIzena());

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					atalaID = resultSet.getInt("atala_ID");
				}
			}
		}
		return atalaID;
	}

	/**
	 * Lehiaketa bat datu basean sartzeko datu guztiekin. Parametro guztiak taulan
	 * izango dituen datu guztiak dira, ezik ID-a, autoincrement delako eta
	 *
	 * @param izena        the izena
	 * @param dataHasiera  the data hasiera
	 * @param dataBukaera  the data bukaera
	 * @param deskribapena the deskribapena
	 * @param logotipoa    the logotipoa
	 * @throws SQLException the SQL exception
	 */
	public static void insertLehiaketa(String izena, String dataHasiera, String dataBukaera, String deskribapena,
			String logotipoa) throws SQLException {
		String query = "INSERT INTO Lehiaketa (izena, data_hasiera, data_bukaera, deskribapena, logotipoa) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, izena);
			pstmt.setString(2, dataHasiera);
			pstmt.setString(3, dataBukaera);
			pstmt.setString(4, deskribapena);
			pstmt.setString(5, logotipoa);

			pstmt.executeUpdate();
		}
	}

	/**
	 * Atal berri bat gehitzeko metodoa. ID-e ezik, aurreko metodoa berdina,
	 * auto-increment delako eta.
	 *
	 * @param izena       the izena
	 * @param saria       the saria
	 * @param lehiaketaID the lehiaketa ID
	 * @throws SQLException the SQL exception
	 */
	public static void insertAtala(String izena, String saria, int lehiaketaID) throws SQLException {
		String query = "INSERT INTO Atala (izena, saria, lehiaketa_ID) VALUES (?, ?, ?)";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, izena);
			pstmt.setString(2, saria);
			pstmt.setInt(3, lehiaketaID);

			pstmt.executeUpdate();
		}
	}

	/**
	 * Lehiaketa baten ID-a lortzeko bere izena emanda.
	 *
	 * @param izena the izena
	 * @return the lehiaketa ID by izena
	 * @throws SQLException the SQL exception
	 */
	public static int getLehiaketaIDByIzena(String izena) throws SQLException {
		int lehiaketaID = -1;

		String query = "SELECT lehiaketa_ID FROM Lehiaketa WHERE izena = ?";
		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, izena);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					lehiaketaID = resultSet.getInt("lehiaketa_ID");
				}
			}
		}
		return lehiaketaID;
	}

	/**
	 * Saria lortzeko metodoa. Atal bat pasata, bere saria
	 * zein den azalduko duena.
	 *
	 * @param atala the atala
	 * @return the saria from atala
	 * @throws SQLException the SQL exception
	 */
	public static String getSariaFromAtala(Atala atala) throws SQLException {
		String saria = null;
		String query = "SELECT saria FROM Atala WHERE izena = ?";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, atala.getIzena());

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					saria = resultSet.getString("saria");
				}
			}
		}

		return saria;
	}

}
