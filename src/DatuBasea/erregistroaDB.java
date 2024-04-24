package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import programaKlaseak.Erabiltzailea;

/**
 * The Class erregistroaDB.
 */
public class erregistroaDB extends Konexioa{
	
	 /**
 	 * Erabiltzaile bat gehitzeko metodoa. Parametrotzat erabiltzaile bat
     * pasatzen da, eta honen informazioaren arabera insert bat egiten da datu
     * basearen konexioa gauzatu eta gero, informazio berria sartzeko.
 	 *
 	 * @param erabiltzailea the erabiltzailea
 	 * @throws SQLException the SQL exception
 	 */
		public static void insertErabiltzailea(Erabiltzailea erabiltzailea) throws SQLException {			
			String sql = "INSERT INTO Erabiltzailea (izena, abizena, korreoa, erabiltzaile_izena, pasahitza, mota) VALUES (?, ?, ?, ?, ?, 'arrunta')";

			try (Connection conn = getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, erabiltzailea.getIzena());
				pstmt.setString(2, erabiltzailea.getAbizena());
				pstmt.setString(3, erabiltzailea.getKorreoa());
				pstmt.setString(4, erabiltzailea.getErabiltzaileIzena());
				pstmt.setString(5, erabiltzailea.getPasahitza());

				pstmt.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Datu basean errorea: " + e.getMessage(), "Errorea",
				JOptionPane.ERROR_MESSAGE);
			}
		}
}
