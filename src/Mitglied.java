import java.util.Date;

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
	
	/**
	 * 
	 * @author Kögler Alexander
	 *
	 */
	public static class ZeitraumSelektor implements Selektor<Mitglied>
	{
		private Date zeitpunkt;
		public ZeitraumSelektor(Date zeitpunkt)
		{
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
	public static class InstrumentSelektor implements Selektor<Mitglied>
	{
		private String instrument;
		public InstrumentSelektor(String instrument)
		{
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
	public static class NameSelektor implements Selektor<Mitglied>{

		private String name;
		public NameSelektor(String name)
		{
			this.name = name;
		}
		@Override
		public boolean select(Mitglied item) {
			return item.name.compareToIgnoreCase(name) == 0;
		}
	}
}
