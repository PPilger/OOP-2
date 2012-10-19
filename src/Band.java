import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Band implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Variablendefinition
	private String name;
	private String ausrichtung;
	private int minProben;

	private Songs repertoire;
	private Termine termine;
	private Mitglieder mitglieder;

	// Konstruktor
	public Band(String name, String ausrichtung, int minProben) {
		this.name = name;
		this.ausrichtung = ausrichtung;
		this.minProben = minProben;
		
		this.repertoire = new Songs();
		this.termine = new Termine();
		this.mitglieder = new Mitglieder();
	}
	
	public String getName() {
		return name;
	}

	public Songs getRepertoire() {
		return this.repertoire;
	}

	public Songs getRepertoire(List<Selector<Song>> selectors) {
		return new Songs(repertoire, selectors);
	}

	public Termine getTermine() {
		return this.termine;
	}

	public Termine getTermine(List<Selector<Termin>> selectors) {
		return new Termine(termine, selectors);
	}

	public Mitglieder getMitglieder() {
		return this.mitglieder;
	}

	public Mitglieder getMitglieder(List<Selector<Mitglied>> selectors) {
		return new Mitglieder(mitglieder, selectors);
	}

	public int getMinimumProben(){
		return this.minProben;
	}
	
	public String toString() {
		return this.name + ", " + this.ausrichtung;
	}
}
