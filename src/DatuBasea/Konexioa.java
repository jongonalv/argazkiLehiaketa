package DatuBasea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Konexioa kLASEA.
 */
public class Konexioa {

	/** Datu basearen konexioa egiteko URL-a. */
	static final String DB_URL = "jdbc:mysql://localhost:3308/Lehiaketa?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

	/** Datu basearen erabiltzailea konstantea. */
	static final String USER = "root";

	/** Erabiltzailearen pasahitza datu basean konstantea. */
	static final String PASS = "root";

	/**
	 * Datu basearik konexioa lortzen du.
	 *
	 * @return the konexioa
	 * @throws SQLException the SQL exception
	 */
	public static Connection getKonexioa() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

}
