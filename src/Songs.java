import java.util.ArrayList;
import java.util.Date;

/**
 * Eine Sammlung von Songs.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Songs extends ArrayList<Song> {

	/**
	 * Serialisierungs ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gibt die Songs zurueck, die zu dem Zeitpunkt <code>zeitpunkt</code> aktuell waren.
	 * 
	 * @param zeitpunkt
	 * @return
	 */
	public Songs list(Date zeitpunkt) {
		Songs ausgabe = new Songs();

		for (Song element : this) {
			if (element.getZeitraum().inZeitraum(zeitpunkt)) {
				ausgabe.add(element);
			}
		}

		return ausgabe;
	}
}
