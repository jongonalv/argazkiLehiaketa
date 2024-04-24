package DatuBasea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	    return getLehiaketKontsulta("SELECT * FROM Lehiaketa");
	}

	/**
	 * Erabiltzaile espezifiko bat emanda, parte hartzen duen lehiaketak
	 * eman.
	 *
	 * @param erabiltzaile_izena the erabiltzaile izena
	 * @return the erabiltzaile lehiaketak
	 * @throws SQLException the SQL exception
	 */
	public static List<Lehiaketa> getErabiltzaileLehiaketak(String erabiltzaile_izena) throws SQLException {
	    int erabiltzaileID = erabiltzaileakDB.getErabiltzaileaID(erabiltzaile_izena);
	    String query = "SELECT * FROM Lehiaketa INNER JOIN Parte_hartu ON Parte_hartu.lehiaketa = Lehiaketa.lehiaketa_ID WHERE Parte_hartu.erabiltzailea = ?";
	    return getLehiaketKontsulta(query, erabiltzaileID);
	}

	/**
	 * Lehiaketen informazio guztia lortzen du kontsulta bat emanda.
	 *
	 * @param query the query
	 * @param params the params
	 * @return the lehiaketak from query
	 * @throws SQLException the SQL exception
	 */
	private static List<Lehiaketa> getLehiaketKontsulta(String query, Object... params) throws SQLException {
	    List<Lehiaketa> lehiaketak = new ArrayList<>();

	    try (Connection conn = getKonexioa();
	         PreparedStatement preparedStatement = conn.prepareStatement(query)) {

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
	 * Kontsulta baten emaitzak eman eta gero, emaitza horien arabera,
	 * aldagaiak sortzen dira eta Lehiaketa objektu berri bat sortzen da 
	 * kontsultan emandako datuekin.
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
}
