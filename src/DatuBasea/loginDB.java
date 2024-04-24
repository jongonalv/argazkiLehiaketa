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
	 * Checks if is erabiltzaile valid.
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
	 * Epailea da.
	 *
	 * @param erabiltzailea the erabiltzailea
	 * @return true, if successful
	 */
	public static boolean epaileaDa(Erabiltzailea erabiltzailea) {
		if (!isErabiltzaileValid(erabiltzailea)) {
			return false;
		}

		String sql = "SELECT mota FROM Erabiltzailea WHERE erabiltzaile_izena = ?";
		try (Connection conn = getKonexioa(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, erabiltzailea.getErabiltzaileIzena());
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				String epaileaIzan = resultSet.getString("mota");
				
				//Konprobatzen da ea epaileaIzan null den ala ez. Null bada false izango da.
				if (epaileaIzan != null) {
					return "epailea".equalsIgnoreCase(epaileaIzan);
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}
