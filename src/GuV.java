
/**
 * 
 * @author Christian Kletzander
 * 
 */

public class GuV extends Selection<Posten> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Band band;

	public GuV(Band band) {
		this.band = band;
	}

	/**
	 * Berechnet die Summe der Einnahmen aller selektierter Posten. Daber werden
	 * auch Posten der Termine beruecksichtigt.
	 * 
	 * @return Summe der Einnahmen der selektierten Posten
	 */
	public double getEinnahmen() {
		double einnahmen = 0;

		// Durchiterieren der gespeicherten Posten
		for (Posten posten : this) {
			einnahmen += posten.getEinnahmen();
		}

		// Durchiterieren aller Termine der Band
		for (Termin termin : band.getTermine()) {
			Posten posten = termin.getPosten();
			if (selected(posten)) {
				einnahmen += posten.getEinnahmen();
			}
		}

		return einnahmen;
	}

	/**
	 * Berechnet die Summe der Ausgaben aller selektierter Posten. Daber werden
	 * auch Posten der Termine beruecksichtigt.
	 * 
	 * @return Summe der Ausgaben der selektierten Posten
	 */
	public double getAusgaben() {
		double ausgaben = 0;

		// Durchiterieren der gespeicherten Posten
		for (Posten posten : this) {
			ausgaben += posten.getAusgaben();
		}

		// Durchiterieren aller Termine der Band
		for (Termin termin : band.getTermine()) {
			Posten posten = termin.getPosten();
			if (selected(posten)) {
				ausgaben += posten.getAusgaben();
			}
		}

		return ausgaben;
	}

	/**
	 * Berechnet den Gesamtgewinn aller selektierter Posten. Daber werden auch
	 * Posten der Termine beruecksichtigt.
	 * 
	 * @return Gesamtgewinn der selektierten Posten
	 */
	public double getGewinn() {
		double ausgaben = 0;

		// Durchiterieren der gespeicherten Posten
		for (Posten posten : this) {
			ausgaben += posten.getEinnahmen() - posten.getAusgaben();
		}

		// Durchiterieren aller Termine der Band
		for (Termin termin : band.getTermine()) {
			Posten posten = termin.getPosten();
			if (selected(posten)) {
				ausgaben += posten.getEinnahmen() - posten.getAusgaben();
			}
		}

		return ausgaben;
	}
}
