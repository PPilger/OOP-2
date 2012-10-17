/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Mitglied {

	// Variablendefinition
	private String name;
	private String telNr;
	private String instrument;
	private Zeitraum zeitraum;

	// Konstruktor
	public Mitglied(String name, String telNr, String instrument,
			Zeitraum zeitraum) {
		this.name = name;
		this.telNr = telNr;
		this.instrument = instrument;
		this.zeitraum = zeitraum;
	}

	public Zeitraum getZeitraum() {
		return this.zeitraum;
	}

	public String toString() {
		return this.name + " (" + this.instrument + ")";
	}

	public String toDetailString() {
		return toString() + " " + this.zeitraum + "\n" + "TelefonNr: "
				+ this.telNr;
	}
}
