import java.text.SimpleDateFormat;

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
	}

	private String ort;
	private Zeitraum zeitraum;

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
