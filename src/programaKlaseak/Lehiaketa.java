package programaKlaseak;

// TODO: Auto-generated Javadoc
/**
 * The Class Lehiaketa.
 */
public class Lehiaketa {

	/** The lehiaketa ID. */
	int lehiaketaID;

	/** The izena. */
	String izena;

	/** The data hasiera. */
	String data_hasiera;

	/** The data bukaera. */
	String data_bukaera;

	/** The deskribapena. */
	String deskribapena;

	/** The logotipoa string. */
	String logotipoaString;

	/**
	 * Instantiates a new lehiaketa.
	 *
	 * @param lehiaketaID     the lehiaketa ID
	 * @param izena           the izena
	 * @param data_hasiera    the data hasiera
	 * @param data_bukaera    the data bukaera
	 * @param deskribapena    the deskribapena
	 * @param logotipoaString the logotipoa string
	 */
	public Lehiaketa(int lehiaketaID, String izena, String data_hasiera, String data_bukaera, String deskribapena,
			String logotipoaString) {
		setData_bukaera(data_bukaera);
		setData_hasiera(data_hasiera);
		setDeskribapena(deskribapena);
		setIzena(izena);
		setLehiaketaID(lehiaketaID);
		setLogotipoaString(logotipoaString);
	}

	/**
	 * Sets the data bukaera.
	 *
	 * @param data_bukaera the new data bukaera
	 */
	public void setData_bukaera(String data_bukaera) {
		this.data_bukaera = data_bukaera;
	}

	/**
	 * Sets the data hasiera.
	 *
	 * @param data_hasiera the new data hasiera
	 */
	public void setData_hasiera(String data_hasiera) {
		this.data_hasiera = data_hasiera;
	}

	/**
	 * Sets the deskribapena.
	 *
	 * @param deskribapena the new deskribapena
	 */
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	/**
	 * Sets the izena.
	 *
	 * @param izena the new izena
	 */
	public void setIzena(String izena) {
		this.izena = izena;
	}

	/**
	 * Sets the lehiaketa ID.
	 *
	 * @param lehiaketaID the new lehiaketa ID
	 */
	public void setLehiaketaID(int lehiaketaID) {
		this.lehiaketaID = lehiaketaID;
	}

	/**
	 * Sets the logotipoa string.
	 *
	 * @param logotipoaString the new logotipoa string
	 */
	public void setLogotipoaString(String logotipoaString) {
		this.logotipoaString = logotipoaString;
	}

	/**
	 * Gets the data bukaera.
	 *
	 * @return the data bukaera
	 */
	public String getData_bukaera() {
		return data_bukaera;
	}

	/**
	 * Gets the data hasiera.
	 *
	 * @return the data hasiera
	 */
	public String getData_hasiera() {
		return data_hasiera;
	}

	/**
	 * Gets the deskribapena.
	 *
	 * @return the deskribapena
	 */
	public String getDeskribapena() {
		return deskribapena;
	}

	/**
	 * Gets the izena.
	 *
	 * @return the izena
	 */
	public String getIzena() {
		return izena;
	}

	/**
	 * Gets the lehiaketa ID.
	 *
	 * @return the lehiaketa ID
	 */
	public int getLehiaketaID() {
		return lehiaketaID;
	}

	/**
	 * Gets the logotipoa string.
	 *
	 * @return the logotipoa string
	 */
	public String getLogotipoaString() {
		return logotipoaString;
	}
}
