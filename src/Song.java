
/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Song {

	private String name;
	private int laenge;
	private Zeitraum zeitraum;

	// Konstruktor
	public Song(String name, int laenge, Zeitraum zeitraum) {
		this.name = name;
		this.laenge = laenge;
		this.zeitraum = zeitraum;
	}

	public Zeitraum getZeitraum() {
		return this.zeitraum;
	}

	public String toString() {
		return String.format("%s (%d:%02d)", this.name, this.laenge / 60,
				this.laenge % 60);
	}

	public String toDetailString() {
		return toString() + " " + this.zeitraum;
	}
}
