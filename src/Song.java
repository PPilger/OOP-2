import java.util.Date;


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
	
	/**
	 * 
	 * @author K�gler Alexander
	 *
	 */
	public static class ZeitpunktSelektor implements Selektor<Song>
	{
		private Date zeitpunkt;
		public ZeitpunktSelektor(Date zeitpunkt)
		{
			this.zeitpunkt = zeitpunkt;
		}
		@Override
		public boolean select(Song item) {
			return item.getZeitraum().inZeitraum(zeitpunkt);
		}
		
	}

	/**
	 * 
	 * @author K�gler Alexander
	 *
	 */
	public static class NameSelektor implements Selektor<Song>{

		private String name;
		public NameSelektor(String name)
		{
			this.name = name;
		}
		@Override
		public boolean select(Song item) {
			return item.name.compareToIgnoreCase(name) == 0;
		}
	}

	/**
	 * 
	 * @author K�gler Alexander
	 *
	 */
	public static class LaengeSelektor implements Selektor<Song>{

		private int min, max;
		
		/**
		 * Selektor, der sicher stellt, dass beide Parameter passen in bezug auf die L�nge des Musikst�ckes
		 * @param min Musikst�ck muss mindesten so lange dauern, alternativ -inf angeben
		 * @param max Musikst�ck darf maximal solange dauern, alternativ +inf angeben
		 */
		public LaengeSelektor(int min, int max)
		{
			this.min = min;
			this.max = max;					
		}
		
		/**
		 * Selektor der sicherstellt, dass das Musikst�ck genau so lange ist
		 * @param equalTo Dauer des Musikst�cks muss genau solange sein wie der Parameter
		 */
		public LaengeSelektor(int equalTo)
		{
			this(equalTo, equalTo);
		}
		
		@Override
		public boolean select(Song item) {
			if(min == max)
			{
				return item.laenge == min;
			}
			else
			{
				return min <= item.laenge && item.laenge<= max;
			}
		}
		
	}
}
