import java.util.ArrayList;
import java.util.Date;

/**
 * Eine Sammlung von Mitgliedern.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Mitglieder extends ArrayList<Mitglied> {

	/**
	 * Serialisierungs ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gibt die Mitglieder zurueck, die zu dem Zeitpunkt <code>zeitpunkt</code> bei der Band waren.
	 * 
	 * @param zeitpunkt
	 * @return
	 */
	public Mitglieder list(Date zeitpunkt) {
		Mitglieder ausgabe = new Mitglieder();

		for (Mitglied element : this) {
			if (element.getZeitraum().inZeitraum(zeitpunkt)) {
				ausgabe.add(element);
			}
		}

		return ausgabe;
	}
}