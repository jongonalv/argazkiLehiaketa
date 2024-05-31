package DatuBasea;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class botoak.
 */
public class botoak extends Konexioa {

	/**
	 * Erabiltzailea epailea den konprobatzeko metodoa. Erabiltzailearen ID-a
	 * jasotzen da parametrotzat eta epailea den konprobatzen da.
	 *
	 * @param erabiltzailea_ID the erabiltzailea ID
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public static boolean epaileaDa(int erabiltzailea_ID) throws SQLException {
		boolean esEpailea = false;
		String sql = "SELECT COUNT(*) AS count FROM Erabiltzailea WHERE erabiltzailea_ID = ? AND mota = 'epailea'";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, erabiltzailea_ID);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt("count");
					esEpailea = count > 0;
				}
			}
		}

		return esEpailea;
	}

	/**
	 * Epaile batek argazki bati botoak emateko metodoa. Erregistro berri bat
	 * sortuko da taulan erabiltzailearen informazioarekin eta bozkatu duen
	 * irudiaren informazioarekin. Argazkiaren botoei +1 egingo da.
	 *
	 * @param epailea_ID  the epailea ID
	 * @param argazkia_ID the argazkia ID
	 * @throws SQLException the SQL exception
	 */
	public static void botoaEman(int epailea_ID, int argazkia_ID, int lehiaketa_ID) throws SQLException {

		// Epailea den konprobatu
		if (!epaileaDa(epailea_ID)) {
			JOptionPane.showMessageDialog(null, "Ez zara epailea", "Errorea", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Konprobatu ea jadanik bozkatu duen
		if (bozkatuDu(epailea_ID, argazkia_ID)) {
			JOptionPane.showMessageDialog(null, "Jadanik bozkatu dezu argazki honetan");
			return;
		}

		// Bozkatu duen data konprobatu eta lehiaketa datetan dagoen
		LocalDate gaurkoData = LocalDate.now();
		if (!lehiaketaDatetanDago(gaurkoData, lehiaketa_ID)) {
			JOptionPane.showMessageDialog(null, "Lehiaketa ez da hasi edo bukatu da, ezin duzu bozkatu", "Errorea",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Botoak aldatu
		botoaGehitu(epailea_ID, argazkia_ID);
		botoakAldatu(argazkia_ID);

		// Erabiltzaileari jakinarazi bere botoa gorde dela
		JOptionPane.showMessageDialog(null, "Botoa gorde da!", "Errorea", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Epaile batek argazki batean botoa eman duen konfirmatzeko. Epaileak botoa
	 * eman badu argazkian, true itzuliko du. Bestela false. Metodo honi esker
	 * epaile batek argazki askori botoak ematea sahiesten du.
	 *
	 * @param epailea_ID  the epailea ID
	 * @param argazkia_ID the argazkia ID
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	private static boolean bozkatuDu(int epailea_ID, int argazkia_ID) throws SQLException {
		String sql = "SELECT COUNT(*) AS count FROM Bozkatu WHERE Epailea = ? AND Argazkia = ?";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, epailea_ID);
			pstmt.setInt(2, argazkia_ID);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt("count");
					return count > 0;
				}
			}
		}

		return false;
	}

	/**
	 * Zeinek bozkatu duen zein jakiteko, epailearen eta argazkiaren ID-a gordetzen
	 * duen taulan informazioa gorde
	 *
	 * @param epailea_ID  the epailea ID
	 * @param argazkia_ID the argazkia ID
	 * @throws SQLException the SQL exception
	 */
	private static void botoaGehitu(int epailea_ID, int argazkia_ID) throws SQLException {
		String sql = "INSERT INTO Bozkatu (Epailea, Argazkia) VALUES (?, ?)";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, epailea_ID);
			pstmt.setInt(2, argazkia_ID);

			pstmt.executeUpdate();
		}
	}

	/**
	 * Botoak aldatu. Metodo honi esker, argazki bat bozkatzen denean, botoei +1
	 * egingo du.
	 *
	 * @param argazkia_ID the argazkia ID
	 * @throws SQLException the SQL exception
	 */
	private static void botoakAldatu(int argazkia_ID) throws SQLException {
		String sql = "UPDATE Argazkia SET botoak = botoak + 1 WHERE irudia_ID = ?";

		try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, argazkia_ID);

			pstmt.executeUpdate();
		}
	}

	/**
	 * Data bat pasata, konprobatzen du ea lehiaketaren datetan dagoen ala ez. Datu
	 * baseko hasiera data eta bukaera data hartzen dira
	 *
	 * @param data the data
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	private static boolean lehiaketaDatetanDago(LocalDate data, int id) throws SQLException {
		String sql = "SELECT data_hasiera, data_bukaera FROM Lehiaketa WHERE lehiaketa_ID = " + id;

		try (Connection conn = getKonexioa();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet resultSet = pstmt.executeQuery()) {
			if (resultSet.next()) {
				LocalDate dataHasiera = resultSet.getDate("data_hasiera").toLocalDate();
				LocalDate dataBukaera = resultSet.getDate("data_bukaera").toLocalDate();

				return !data.isBefore(dataHasiera) && !data.isAfter(dataBukaera);
			}
		}

		return false;
	}
}
