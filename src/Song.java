import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	/*private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.write(laenge);
		out.writeObject(name);
		out.writeObject(zeitraum);
		out.close();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		laenge = in.readInt();
		name = (String) in.readObject();
		zeitraum = (Zeitraum) in.readObject();
		in.close();
	}*/

	/**
	 * 
	 * @author Kögler Alexander
	 * 
	 */
	public static class ZeitpunktSelektor implements Selektor<Song> {
		private Date zeitpunkt;

		public ZeitpunktSelektor(Date zeitpunkt) {
			this.zeitpunkt = zeitpunkt;
		}

		@Override
		public boolean select(Song item) {
			return item.getZeitraum().inZeitraum(zeitpunkt);
		}

	}

	/**
	 * 
	 * @author Kögler Alexander
	 * 
	 */
	public static class NameSelektor implements Selektor<Song> {

		private String name;

		public NameSelektor(String name) {
			this.name = name;
		}

		@Override
		public boolean select(Song item) {
			return item.name.compareToIgnoreCase(name) == 0;
		}
	}

	/**
	 * 
	 * @author Kögler Alexander
	 * 
	 */
	public static class LaengeSelektor implements Selektor<Song> {

		private int min, max;

		/**
		 * Selektor, der sicher stellt, dass beide Parameter passen in bezug auf
		 * die Länge des Musikstückes
		 * 
		 * @param min
		 *            Musikstück muss mindesten so lange dauern, alternativ -inf
		 *            angeben
		 * @param max
		 *            Musikstück darf maximal solange dauern, alternativ +inf
		 *            angeben
		 */
		public LaengeSelektor(int min, int max) {
			this.min = min;
			this.max = max;
		}

		/**
		 * Selektor der sicherstellt, dass das Musikstück genau so lange ist
		 * 
		 * @param equalTo
		 *            Dauer des Musikstücks muss genau solange sein wie der
		 *            Parameter
		 */
		public LaengeSelektor(int equalTo) {
			this(equalTo, equalTo);
		}

		@Override
		public boolean select(Song item) {
			if (min == max) {
				return item.laenge == min;
			} else {
				return min <= item.laenge && item.laenge <= max;
			}
		}

	}
}
