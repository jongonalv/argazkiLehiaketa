package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import programaKlaseak.Atala;
import programaKlaseak.Erabiltzailea;

// TODO: Auto-generated Javadoc
/**
 * The Class irudiakDB.
 */
public class irudiakDB extends Konexioa {
	
	/**
	 * Atal bateko irudi guztiak lortzen dituen metodoa.
	 * Atal baten informazioa pasatzen zaio parametrotzat eta 
	 * atal horren irudi guztiak bueltatzen dira lista baten 
	 * (kokapenak itzultzen dira)
	 *
	 * @param atala the atala
	 * @return the irudiak
	 * @throws SQLException the SQL exception
	 */
	public static List<String> getIrudiak(String izenaAtala) throws SQLException {
		
		List<String> irudiak = new ArrayList<>();
		
		String sql = "SELECT kokapena FROM Argazkia "
		           + "INNER JOIN Atala ON Atala.atala_ID = Argazkia.atala "
		           + "WHERE Atala.izena = ?";
		Connection conn = getKonexioa();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, izenaAtala);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					String izena = resultSet.getString("kokapena");
					irudiak.add(izena);
				}
			}
		}
		return irudiak;
	}

	/**
	 * Kokapenak pasata, kokapen hauek irudi bihurtzen dituen metodoa da.
	 * ImageIcon zerrenda bat bueltatzen du irudi guztiekin
	 *
	 * @param kokapenak the kokapenak
	 * @return the list
	 */
	public static List<ImageIcon> kargatuImageIcons(List<String> kokapenak) {
		
	    List<ImageIcon> icons = new ArrayList<>();
	    for (String kokapena : kokapenak) {
	        ImageIcon icon = cargarImageIcon(kokapena);
	        if (icon != null) {
	            icons.add(icon);
	        }
	    }
	    return icons;
	}

	/**
	 * Kokapen bakarra pasata, irudi bakarra bueltatzen duen metodoa.
	 *
	 * @param kokapena the kokapena
	 * @return the image icon
	 */
	public static ImageIcon cargarImageIcon(String kokapena) {
	    try {
	        java.net.URL imageUrl = irudiakDB.class.getResource(kokapena);
	        if (imageUrl != null) {
	            return new ImageIcon(imageUrl);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	/** 
	 * Erabiltzaile konkretu batek igo duen irudia lortzeko metodoa.
	 * Atala eta erabiltzailea pasatzen dira parametrotzat, bi hauen
	 * ID-a lortzen da eta ze irudirekin erlazionatuta dauden lortzen da
	 * SQL kontsultan. Honek, Irudiaren kokapena bueltatuko du.
	 *
	 * @param atala the atala
	 * @param erabiltzailea the erabiltzailea
	 * @return the erabiltzailea irudia
	 * @throws SQLException the SQL exception
	 */
	public static String getErabiltzaileaIrudia(Atala atala, Erabiltzailea erabiltzailea) throws SQLException {
		
	    String kokapena = null;
	    String sql = "SELECT kokapena FROM Argazkia "
	               + "WHERE atala = ? AND egilea = ?";
	    
	    try (Connection conn = getKonexioa();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, lehiaketaDB.lortuAtalaID(atala));
	        pstmt.setInt(2, erabiltzaileakDB.getErabiltzaileaID(erabiltzailea.getErabiltzaileIzena()));

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                kokapena = resultSet.getString("kokapena");
	            }
	        }
	    }
	    return kokapena;
	}
	
	/**
	 * Ezabatu argazkia.
	 *
	 * @param atala the atala
	 * @param erabiltzailea the erabiltzailea
	 * @throws SQLException the SQL exception
	 */
	public static void ezabatuArgazkia(Atala atala, Erabiltzailea erabiltzailea) throws SQLException {

	    String sql = "DELETE FROM Argazkia "
	               + "WHERE atala = ? AND egilea = ?";

	    try (Connection conn = getKonexioa();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, lehiaketaDB.lortuAtalaID(atala));
	        pstmt.setInt(2, erabiltzaileakDB.getErabiltzaileaID(erabiltzailea.getErabiltzaileIzena()));

	        pstmt.executeUpdate();
	    }
	}

	
	/**
	 * Irudi baten egilearen izena lortzeko metodoa.
	 *
	 * @param kokapena the kokapena
	 * @return the egilea izena
	 * @throws SQLException the SQL exception
	 */
	public static String getErabiltzaileaIzenaAbizena(String kokapena) throws SQLException {
	    String izenaAbizena = null;
	    String sql = "SELECT e.izena, e.abizena " +
	                 "FROM Argazkia a " +
	                 "INNER JOIN Erabiltzailea e ON a.egilea = e.erabiltzailea_ID " +
	                 "WHERE a.kokapena = ?";

	    try (Connection conn = getKonexioa();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, kokapena);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                String izena = resultSet.getString("izena");
	                String abizena = resultSet.getString("abizena");
	                izenaAbizena = izena + " " + abizena;
	            }
	        }
	    }
	    return izenaAbizena;
	}
	
	/**
	 * Argazki baten boto kantitatea lortzeko metodoa.
	 *
	 * @param kokapena the kokapena
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public static int lortuBotoak(String kokapena) throws SQLException {
	    int botoak = 0;
	    String sql = "SELECT botoak FROM Argazkia WHERE kokapena = ?";

	    try (Connection conn = getKonexioa();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, kokapena);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                botoak = resultSet.getInt("botoak");
	            }
	        }
	    }
	    return botoak;
	}


	
	/**
	 * Erabiltzaile bat, atal bat, kokapen bat parametrotzat jasota,
	 * irudi berri bat sortu datu basean eta gehitu.
	 *
	 * @param erabiltzailea the erabiltzailea
	 * @param atala the atala
	 * @param kokapena the kokapena
	 * @throws SQLException the SQL exception
	 */
	public static void irudiaGorde(Erabiltzailea erabiltzailea, Atala atala, String kokapena) throws SQLException{
		String sql = "INSERT INTO Argazkia (kokapena, botoak, egilea, atala) VALUES (?, ?, ?, ?)";
		
		Connection conn = getKonexioa();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// parametroen baloreak jarri
		pstmt.setString(1, kokapena);
		pstmt.setInt(2, 0);
		pstmt.setInt(3, erabiltzaileakDB.getErabiltzaileaID(erabiltzailea.getErabiltzaileIzena()));
		pstmt.setInt(4, lehiaketaDB.lortuAtalaID(atala));

		// Kontsulta exetutatu
		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(null, "Irudi berria gorde da!");
	}
	
	/**
	 * Elimina una imagen de la base de datos.
	 *
	 * @param kokapena la ubicación de la imagen en el sistema de archivos o en el classpath
	 * @throws SQLException si hay un error al ejecutar la consulta SQL
	 */
	public static void irudiaEzabatu(String kokapena) throws SQLException {
	    String sql = "DELETE FROM Argazkia WHERE kokapena = ?";
	    
	    try (Connection conn = getKonexioa();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, kokapena);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Irudia ondo ezabatu da!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Errorea: Ez da aurkitu irudia", "Errorea", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Errorea: " + e.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
	        throw e;
	    }
	}
	
	/**
	 * Kokapenatik abiatuta, argazki baten ID-a lortzen duen metodoa
	 *
	 * @param kokapena 
	 * @return Irudiaren ID-a, ez bada aurkitzen -1.
	 * @throws SQLException SQL errore bat gertatzen bada.
	 */
	public static int lortuArgazkiaID(String kokapena) throws SQLException {
	    int argazkiaID = -1;
	    String sql = "SELECT irudia_ID FROM Argazkia WHERE kokapena = ?";

	    try (Connection conn = getKonexioa();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, kokapena);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                argazkiaID = resultSet.getInt("irudia_ID");
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Errorea: " + e.getMessage(), "Errorea", JOptionPane.ERROR_MESSAGE);
	        throw e;
	    }
	    return argazkiaID;
	}


}
