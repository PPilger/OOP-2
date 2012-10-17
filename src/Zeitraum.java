import java.text.DateFormat;
import java.util.Date;

/**
 * Ermoeglicht die ueberpruefung ob sich Zeitraeume ueberschneiden.
 * @author Koegler Alexander
 *
 */
public interface Zeitraum {

	/**
	 * 
	 * @param z Das ZeitIntervall das mit dem lokalen auf ueberschneidung geprueft wird. Darf nicht null sein!
	 * @return True bei ueberschneidung des Lokalen ZeitIntervalls mit dem in z, false wenn keine ueberschneidung existiert.
	 */
	public boolean inZeitraum(Date z);
	
	/**
	 * 
	 * @param z Das ZeitIntervall das mit dem lokalen auf ueberschneidung geprueft wird. 
	 * @return True bei ueberschneidung des Lokalen ZeitIntervalls mit dem uebergebenen. False wenn keine ueberschneidung existiert, d.h. wenn das lokale Intervall beginnt nachdem das uebergebene endet und vice versa.
	 */
	public boolean enthaelt(ZeitIntervall intervall);
	
	public String toString(DateFormat df);
}
