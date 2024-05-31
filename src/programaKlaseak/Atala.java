package programaKlaseak;

// TODO: Auto-generated Javadoc
/**
 * The Class Atala.
 */
public class Atala {
	
	/** The izena. */
	private String izena;
	
	/** The saria. */
	private String saria;
	
	/** The argazki max. */
	private int argazkiMax;
	
	/**
	 * Instantiates a new atala.
	 *
	 * @param izena the izena
	 * @param saria the saria
	 * @param argazkiMax the argazki max
	 */
	public Atala(String izena, String saria, int argazkiMax) {
		setArgazkiMax(argazkiMax);
		setIzena(izena);
		setSaria(saria);
	}
	
	/**
	 * Instantiates a new atala.
	 *
	 * @param izena the izena
	 */
	public Atala(String izena) {
		setIzena(izena);
	}
	
	/**
	 * Sets the argazki max.
	 *
	 * @param argazkiMax the new argazki max
	 */
	public void setArgazkiMax(int argazkiMax) {
		this.argazkiMax = argazkiMax;
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
	 * Sets the saria.
	 *
	 * @param saria the new saria
	 */
	public void setSaria(String saria) {
		this.saria = saria;
	}
	
	/**
	 * Gets the argazki max.
	 *
	 * @return the argazki max
	 */
	public int getArgazkiMax() {
		return argazkiMax;
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
	 * Gets the saria.
	 *
	 * @return the saria
	 */
	public String getSaria() {
		return saria;
	}
}
