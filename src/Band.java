import java.util.Date;

/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Band {

	// Variablendefinition
	private String name;
	private String ausrichtung;
	private Songs repertoire;
	private Termine termine;
	private Mitglieder mitglieder;

	// Konstruktor
	public Band(String name, String ausrichtung) {
		this.name = name;
		this.ausrichtung = ausrichtung;

		this.repertoire = new Songs();
		this.termine = new Termine();
		this.mitglieder = new Mitglieder();
	}

	public Songs getRepertoire() {
		return this.repertoire;
	}

	public Songs getRepertoire(Date zeitpunkt) {
		return this.repertoire.list(zeitpunkt);
	}

	public Termine getTermine() {
		return this.termine;
	}

	public Termine getTermine(Zeitraum zeitraum) {
		return this.termine.list(zeitraum);
	}

	public Termine getTermine(Class<? extends Termin> typ, Zeitraum zeitraum) {
		return this.termine.list(typ, zeitraum);
	}

	public Mitglieder getMitglieder() {
		return this.mitglieder;
	}

	public Mitglieder getMitglieder(Date zeitpunkt) {
		return this.mitglieder.list(zeitpunkt);
	}

	public String toString() {
		return this.name + ", " + this.ausrichtung;
	}

}
