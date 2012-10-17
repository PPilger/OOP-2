import java.text.SimpleDateFormat;

/**
 * Speichert Ort, Zeitraum, Dauer ab. Bietet Methoden fuer die kaufmaennische
 * Berechnungslehre.
 * 
 * @author Koegler Alexander
 * 
 */
public abstract class Termin {

	public Termin(String ort, ZeitIntervall zeitIntervall) {
		this.ort = ort;
		this.zeitIntervall = zeitIntervall;
	}

	private String ort;
	private ZeitIntervall zeitIntervall;

	/**
	 * 
	 * @return Gewinn
	 */
	public double getGewinn() {
		return getUmsatz() - getKosten();
	}

	/**
	 * 
	 * @return Kosten
	 */
	public abstract double getKosten();

	/**
	 * Berechnet den Umsatz mittels Addition der Kosten und des Gewinns.
	 * 
	 * @return Umsatz
	 */
	public abstract double getUmsatz();

	/**
	 * @return Gibt das lokale Zeitintervall zurueck
	 */
	public ZeitIntervall getZeitIntervall() {
		return zeitIntervall;
	}

	@Override
	public String toString() {
		return ort + " "
				+ zeitIntervall.toString(new SimpleDateFormat("dd.MM.yyyy hh:mm"));
	}

	public abstract String toDetailString();
}
