package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import programaKlaseak.Lehiaketa;

// TODO: Auto-generated Javadoc
/**
 * The Class lehiaketaDB.
 */
public class lehiaketaDB extends Konexioa {
	
	/**
	 * Datu basetik lehiaketa guztiak lortzen dituen metodoa,
	 * SQL kontsulta baten bidez. Lehiaketak, objektu bihurtzen dira
	 * eta lehiaketa zerrenda bat pasatzen da informazio guztiarekin.
	 *
	 * @return the all lehiaketak
	 * @throws SQLException the SQL exception
	 */
	public static List<Lehiaketa> getAllLehiaketak() throws SQLException {
		
		// Lehiaketak gordetzeko zerrenda
        List<Lehiaketa> lehiaketak = new ArrayList<>();
        
        // Informazio guztia lortu
        String query = "SELECT * FROM Lehiaketa";

        try (Connection conn = getKonexioa();
             PreparedStatement preparedStatement = conn.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	
                int lehiaketaID 	= resultSet.getInt("lehiaketa_ID");
                String izena 		= resultSet.getString("izena");
                String dataHasiera 	= resultSet.getString("data_hasiera");
                String dataBukaera 	= resultSet.getString("data_bukaera");
                String deskribapena = resultSet.getString("deskribapena");
                String logotipoa 	= resultSet.getString("logotipoa");

                // Objektua sortu eta zerrendara gehitu
                Lehiaketa lehiaketa = new Lehiaketa(lehiaketaID, izena, dataHasiera, dataBukaera, deskribapena, logotipoa);
                lehiaketak.add(lehiaketa);
            }
        }
        return lehiaketak;
    }

}
