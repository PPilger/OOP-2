import java.util.Arrays;

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

	public Songs getRepertoire(Selektor<Song>... selectors) {
		return new Songs(repertoire, Arrays.asList(selectors));
	}

	public Termine getTermine() {
		return this.termine;
	}

	public Termine getTermine(Selektor<Termin>... selectors) {
		return new Termine(termine, Arrays.asList(selectors));
	}

	public Mitglieder getMitglieder() {
		return this.mitglieder;
	}

	public Mitglieder getMitglieder(Selektor<Mitglied>... selectors) {
		return new Mitglieder(mitglieder, Arrays.asList(selectors));
	}

	public String toString() {
		return this.name + ", " + this.ausrichtung;
	}
}
