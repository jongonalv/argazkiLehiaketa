package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * erabiltzaileakDB klasea, erabiltzaileen kontsultak egiteko.
 */
public class erabiltzaileakDB extends Konexioa {

	/**
	 * Datu basean dauden erabiltzaile izen guztiak lortzen ditu.
	 *
	 * @return the erabiltzailea izenak
	 * @throws SQLException the SQL exception
	 */
	public static ResultSet getErabiltzaileaIzenak() throws SQLException {
		Connection conn = getKonexioa();
		Statement stmt = conn.createStatement();
		return stmt.executeQuery("SELECT erabiltzaile_izena FROM Erabiltzailea;");
	}
	
	/**
	 * Erabiltzaile izena.
	 *
	 * @param erabiltzaileIzena the erabiltzaile izena
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public static boolean erabiltzaileIzenaBerdina(String erabiltzaileIzena) throws SQLException {
		
	    ResultSet resultSet = getErabiltzaileaIzenak();

	    while (resultSet.next()) {
	        String currentErabiltzaileIzena = resultSet.getString("erabiltzaile_izena");
	        if (erabiltzaileIzena.equals(currentErabiltzaileIzena)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Erabiltzaile izen bat pasate, erabiltzaile horren ID-a lortzen duen kontsulta.
	 *
	 * @param erabiltzaileIzena the erabiltzaile izena
	 * @return the erabiltzailea ID
	 * @throws SQLException the SQL exception
	 */
	public static int getErabiltzaileaID(String erabiltzaileIzena) throws SQLException {
	    int erabiltzaileaID = -1;
	    Connection conn = getKonexioa();
	    String sql = "SELECT erabiltzailea_ID FROM Erabiltzailea WHERE erabiltzaile_izena = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, erabiltzaileIzena);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                erabiltzaileaID = rs.getInt("erabiltzailea_ID");
	            }
	        }
	    }
	    conn.close();
	    return erabiltzaileaID;
	}
}
