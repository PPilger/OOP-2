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

	// Konstruktor
	public Mitglied(String name, String telNr, String instrument,
			Zeitraum zeitraum) {
		this.name = name;
		this.telNr = telNr;
		this.instrument = instrument;
		this.zeitraum = zeitraum;
		this.nachrichten = new LinkedList<String>();
		this.terminvorschlaege = new LinkedList<Terminvorschlag>();
	}

	public Zeitraum getZeitraum() {
		return this.zeitraum;
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

	/*
	 * private void writeObject(java.io.ObjectOutputStream out)throws
	 * IOException { out.writeObject(name); out.writeObject(telNr);
	 * out.writeObject(instrument); out.writeObject(zeitraum); out.close(); }
	 * 
	 * private void readObject(java.io.ObjectInputStream in) throws IOException,
	 * ClassNotFoundException { name = (String) in.readObject(); telNr =
	 * (String) in.readObject(); instrument = (String) in.readObject(); zeitraum
	 * = (Zeitraum) in.readObject(); in.close(); }
	 */

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
