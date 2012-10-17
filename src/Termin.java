import java.text.SimpleDateFormat;
import java.util.Stack;

/**
 * Speichert Ort, Zeitraum, Dauer ab. Bietet Methoden fuer die kaufmaennische
 * Berechnungslehre.
 * 
 * @author Koegler Alexander
 * 
 */
public abstract class Termin {

	public Termin(String ort, Zeitraum zeitraum) {
		this.ort = ort;
		this.zeitraum = zeitraum;
		stack = new Stack<Termin>();
	}
	
	public Termin(Termin other) {
		this.ort = other.ort;
		this.zeitraum = other.zeitraum;
		this.stack = new Stack<Termin>();
	}
	
	private String ort;
	private Zeitraum zeitraum;
	Stack<Termin> stack;

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
	public Zeitraum getZeitIntervall() {
		return zeitraum;
	}

	@Override
	public String toString() {
		return ort + " "
				+ zeitraum.toString(new SimpleDateFormat("dd.MM.yyyy hh:mm"));
	}

	/**
	 * @author Christian Kletzander
	 * @param t
	 *            Termin der verändert wird
	 */
	public void save(Termin t) {
		stack.push(t);
	}
	
	/**
	 * @author Christian Kletzander
	 * @param ort
	 * 		Überspeichern des Ortes
	 */
	public void setOrt(String ort) {
		stack.push(this);
		this.ort = ort;
	}
	
	/**
	 * @author Christian Kletzander
	 * @param zeitraum
	 * 		Überspeichern des Zeitraums
	 */
	public void setZeitraum(Zeitraum zeitraum) {
		stack.push(this);
		this.zeitraum = zeitraum;
	}

	public abstract String toDetailString();

	/**
	 * 
	 * @author Christian Kletzander
	 * 
	 */
	public static class ZeitraumSelektor implements Selektor<Termin> {

		private Zeitraum zeitraum;

		public ZeitraumSelektor(Zeitraum zeitraum) {
			this.zeitraum = zeitraum;
		}

		@Override
		public boolean select(Termin item) {
			return this.zeitraum.enthaelt(item.zeitraum);
		}

	}
}
