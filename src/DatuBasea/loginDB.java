package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import programaKlaseak.*;

// TODO: Auto-generated Javadoc
/**
 * The Class loginDB.
 */
public class loginDB extends Konexioa {

	/**
	 * Erabiltzailea datu basean dagoen konprobatzen eta egiaztatzen du. Saioan
	 * sartu nahi den erabiltzailearen datuak pasatzen dira parametrotzat eta
	 * true/false itzultzen du erabiltzailea datu basean dagoen arabera
	 *
	 * @param erabiltzailea the erabiltzailea
	 * @return true, if is erabiltzaile valid
	 */
	public static boolean isErabiltzaileValid(Erabiltzailea erabiltzailea) {
		String sql = "SELECT * FROM Erabiltzailea WHERE erabiltzaile_izena = ? AND pasahitza = ?";

		try (Connection conn = getKonexioa(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, erabiltzailea.getErabiltzaileIzena());
			stmt.setString(2, erabiltzailea.getPasahitza());
			ResultSet resultSet = stmt.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Erabiltzailea konprobatzen du ea zer rol duen.
	 *
	 * @param erabiltzailea el usuario a comprobar
	 * @param mota          rol
	 * @return true rol horretakoa bada
	 */
	public static boolean rolDa(Erabiltzailea erabiltzailea, String mota) {
		if (!isErabiltzaileValid(erabiltzailea)) {
			return false;
		}

		String sql = "SELECT mota FROM Erabiltzailea WHERE erabiltzaile_izena = ?";
		try (Connection conn = getKonexioa(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, erabiltzailea.getErabiltzaileIzena());
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				String userMota = resultSet.getString("mota");
				return mota.equalsIgnoreCase(userMota);
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
