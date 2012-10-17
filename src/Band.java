import java.util.List;

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

	public Songs getRepertoire(List<Selektor<Song>> selectors) {
		return new Songs(repertoire, selectors);
	}

	public Termine getTermine() {
		return this.termine;
	}

	public Termine getTermine(List<Selektor<Termin>> selectors) {
		return new Termine(termine, selectors);
	}

	public Mitglieder getMitglieder() {
		return this.mitglieder;
	}

	public Mitglieder getMitglieder(List<Selektor<Mitglied>> selectors) {
		return new Mitglieder(mitglieder, selectors);
	}

	public String toString() {
		return this.name + ", " + this.ausrichtung;
	}
}
