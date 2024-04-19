package programaKlaseak;

import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * Erabiltzailea Klasea.
 */
public class Erabiltzailea {
	
	/** Erabiltzailearen Izena. */
	private String izena;
	
	/** Erabiltzailearen Pasahitza. */
	private String abizena;
	
	/** Erabiltzailearen erabiltzaile izena. */
	private String erabiltzaileIzena;
	
	/** Korreoa. */
	private String korreoa;
	
	/** Pasahitza. */
	private String pasahitza;
	
	/** Epailea den ala ez jakiteko aldagaia. */
	private boolean epaileaDa;
	
	/**
	 * Erabiltzaile berri bat sortzeko eraikitzailea. Erregistroan
	 * erabiliko dena datu guztiak sartzeko datu basean.
	 *
	 * @param izena the izena
	 * @param abizena the abizena
	 * @param erabiltzaileIzena the erabiltzaile izena
	 * @param korreoa the korreoa
	 * @param pasahitza the pasahitza
	 */
	public Erabiltzailea(String izena, String abizena, String erabiltzaileIzena, String korreoa, String pasahitza) {
		setAbizena(abizena);
		setIzena(izena);
		setErabiltzaileIzena(erabiltzaileIzena);
		setKorreoa(korreoa);
		setPasahitza(pasahitza);
	}
	
	/**
	 * Erabiltzaile berri bat sortzen du sisteman bere 
	 * erabiltzaile izenarekin eta pasahitzarekin.
	 *
	 * @param erabiltzaileIzena the erabiltzaile izena
	 * @param pasahitza the pasahitza
	 */
	public Erabiltzailea(String erabiltzaileIzena, String pasahitza) {
		setErabiltzaileIzena(erabiltzaileIzena);
		setPasahitza(pasahitza);
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
	 * Gets the abizena.
	 *
	 * @return the abizena
	 */
	public String getAbizena() {
		return abizena;
	}
	
	/**
	 * Gets the erabiltzaile izena.
	 *
	 * @return the erabiltzaile izena
	 */
	public String getErabiltzaileIzena() {
		return erabiltzaileIzena;
	}
	
	/**
	 * Gets the korreoa.
	 *
	 * @return the korreoa
	 */
	public String getKorreoa() {
		return korreoa;
	}
	
	/**
	 * Gets the pasahitza.
	 *
	 * @return the pasahitza
	 */
	public String getPasahitza() {
		return pasahitza;
	}
	
	/**
	 * Gets the epailea da.
	 *
	 * @return the epailea da
	 */
	public boolean getEpaileaDa(){
		return epaileaDa;
	}
	
	/**
	 * Sets the abizena.
	 *
	 * @param abizena the new abizena
	 */
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	
	/**
	 * Sets the erabiltzaile izena.
	 *
	 * @param erabiltzaileIzena the new erabiltzaile izena
	 */
	public void setErabiltzaileIzena(String erabiltzaileIzena) {
		this.erabiltzaileIzena = erabiltzaileIzena;
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
	 * Sets the korreoa.
	 *
	 * @param korreoa the new korreoa
	 */
	public void setKorreoa(String korreoa) {
		
        // Korreoa balidatzeko expresioa
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        if (korreoa.matches(emailRegex)) {
            this.korreoa = korreoa;
        } else {
            JOptionPane.showMessageDialog(null, "Korreoaren formatoa ez da egokia", "Errorea", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	/**
	 * Sets the pasahitza.
	 *
	 * @param pasahitza the new pasahitza
	 */
	public void setPasahitza(String pasahitza) {
	    if (pasahitza.length() < 8 || pasahitza.length() > 20) {
	        JOptionPane.showMessageDialog(null, "Pasahitzaren luzera 8 - 20 bitartekoa izen behar du", "Errorea", JOptionPane.ERROR_MESSAGE);
	    } else {
	        this.pasahitza = pasahitza;
	    }
	}
	
	/**
	 * Sets the epailea da.
	 *
	 * @param epaileaDa the new epailea da
	 */
	public void setEpaileaDa(boolean epaileaDa) {
		this.epaileaDa = epaileaDa;
	}
	
}
