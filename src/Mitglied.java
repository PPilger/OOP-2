import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Mitglied implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variablendefinition
	private String name;
	private String telNr;
	private String instrument;
	private Zeitraum zeitraum;
	private Queue<String> nachrichten;
	private Queue<Terminvorschlag> terminvorschlaege;
	private boolean ersatzmitglied;

	// Konstruktor
	public Mitglied(String name, String telNr, String instrument,
			Zeitraum zeitraum, boolean ersatzmitglied) {
		this.name = name;
		this.telNr = telNr;
		this.instrument = instrument;
		this.zeitraum = zeitraum;
		this.nachrichten = new LinkedList<String>();
		this.terminvorschlaege = new LinkedList<Terminvorschlag>();
		this.ersatzmitglied = ersatzmitglied;
	}

	public Zeitraum getZeitraum() {
		return this.zeitraum;
	}
	
	public boolean isErsatzmitglied() {
		return this.ersatzmitglied;
	}

	public void sende(String nachricht) {
		this.nachrichten.offer(nachricht);
	}

	public void sende(Terminvorschlag terminvorschlag) {
		this.terminvorschlaege.offer(terminvorschlag);
	}

	public void revidiere(Terminvorschlag terminvorschlag) {
		this.terminvorschlaege.remove(terminvorschlag);
	}

	public Queue<String> getNachrichten() {
		return nachrichten;
	}

	public Queue<Terminvorschlag> getTerminvorschlaege() {
		return terminvorschlaege;
	}

	public String toString() {
		return this.name;
	}

	public String toDetailString() {
		return toString() + " (" + this.instrument + ") " + this.zeitraum + "\n" + "TelefonNr: "
				+ this.telNr;
	}

	/**
	 * Gibt entweder Mitglieder die Ersatzmitglieder sind aus, oder jene die keine sind.
	 * @author VHD
	 *
	 */
	public static class ErsatzmitgliedSelector implements Selector<Mitglied> {
		private boolean isE;

		/**
		 * Vergleicht den im Parameter übergebenen Wert mit dem boolschen Wert für Ersatzmitglied.
		 * @param isErsatzmitglied True gibt nur Ersatzmitglieder zurueck, False hingegen nur Stammmitglieder
		 */
		public ErsatzmitgliedSelector(boolean isErsatzmitglied) {
			isE = isErsatzmitglied;
		}

		@Override
		public boolean select(Mitglied item) {
			return item.ersatzmitglied == isE;
		}
	}

	/**
	 * 
	 * @author Kögler Alexander
	 * 
	 */
	public static class ZeitraumSelektor implements Selector<Mitglied> {
		private Date zeitpunkt;

		public ZeitraumSelektor(Date zeitpunkt) {
			this.zeitpunkt = zeitpunkt;
		}

		@Override
		public boolean select(Mitglied item) {
			return item.getZeitraum().inZeitraum(zeitpunkt);
		}

	}

	/**
	 * 
	 * @author Kögler Alexander
	 * 
	 */
	public static class InstrumentSelektor implements Selector<Mitglied> {
		private String instrument;

		public InstrumentSelektor(String instrument) {
			this.instrument = instrument;
		}

		@Override
		public boolean select(Mitglied item) {
			return item.instrument.compareToIgnoreCase(instrument) == 0;
		}
	}

	/**
	 * 
	 * @author Kögler Alexander
	 * 
	 */
	public static class NameSelektor implements Selector<Mitglied> {

		private String name;

		public NameSelektor(String name) {
			this.name = name;
		}

		@Override
		public boolean select(Mitglied item) {
			return item.name.compareToIgnoreCase(name) == 0;
		}
	}
}
