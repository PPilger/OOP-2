import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Speichert Ort, Zeitraum, Dauer ab. Bietet Methoden fuer die kaufmaennische
 * Berechnungslehre.
 * 
 * @author Koegler Alexander
 * 
 */
public class Termin implements Serializable {
	private static final long serialVersionUID = 1L;

	private Typ typus;
	private Ort ort;
	private Zeitraum zeitraum;
	private double kosten;
	private double umsatz;
	private List<Mitglied> teilnehmer; // aenderungen der teilnehmer sind nicht
										// zugelassen

	private Termin orig;

	public Termin(Typ typus, Ort ort, Zeitraum zeitraum, double kosten,
			double umsatz, List<Mitglied> teilnehmer) {
		this.typus = typus;
		this.ort = ort;
		this.zeitraum = zeitraum;
		this.kosten = kosten;
		this.umsatz = umsatz;
		this.teilnehmer = teilnehmer;
		this.orig = null;
	}

	private Termin(Termin other) {
		this.typus = other.typus;
		this.ort = other.ort;
		this.zeitraum = new Zeitraum(other.zeitraum);
		this.kosten = other.kosten;
		this.umsatz = other.umsatz;
		// flache kopie, aenderungen der teilnehmer sind nicht zugelassen
		this.teilnehmer = other.teilnehmer;
		this.orig = other.orig;
	}

	public double getKosten() {
		return kosten;
	}

	public double getUmsatz() {
		return umsatz;
	}

	/**
	 * @return Teilnehmerliste. Diese darf nicht geaendert werden!
	 */
	public List<Mitglied> getTeilnehmer() {
		return teilnehmer;
	}

	public void prepareUpdate() {
		this.orig = new Termin(this);
	}
	
	public void meldeUpdate(String aenderung) {
		for(Mitglied t : teilnehmer) {
			t.sende(orig + " wurde geaendert: " + aenderung);
		}
	}

	/**
	 * @author Christian Kletzander
	 * @param ort
	 *            ueberspeichern des Ortes
	 */
	public void setOrt(Ort ort) {
		this.prepareUpdate();
		this.ort = ort;
		this.meldeUpdate(orig.ort + " -> " + ort);
	}

	/**
	 * @author Christian Kletzander
	 * @param zeitraum
	 *            ueberspeichern des Zeitraums
	 */
	public void setZeitraum(Zeitraum zeitraum) {
		this.prepareUpdate();
		this.zeitraum = zeitraum;
		this.meldeUpdate(orig.zeitraum + " -> " + zeitraum);
	}

	public void setKosten(double kosten) {
		this.prepareUpdate();
		this.kosten = kosten;
		this.meldeUpdate("Kosten: " + orig.kosten + " -> " + kosten);
	}

	public void setUmsatz(double umsatz) {
		this.prepareUpdate();
		this.umsatz = umsatz;
		this.meldeUpdate("Umsatz: " + orig.umsatz + " -> " + umsatz);
	}

	public boolean undo() {
		if (orig == null) {
			return false;
		}

		meldeUpdate("zurueckgesetzt auf vorige Version");
		
		this.typus = orig.typus;
		this.ort = orig.ort;
		this.zeitraum = orig.zeitraum;
		this.kosten = orig.kosten;
		this.umsatz = orig.umsatz;
		this.teilnehmer = orig.teilnehmer;
		this.orig = orig.orig;

		return true;
	}

	@Override
	public String toString() {
		return typus + ": " + ort + " "
				+ zeitraum.toString(new SimpleDateFormat("dd.MM.yyyy hh:mm"));
	}

	public String toDetailString() {
		return String.format("%s, Kosten: %,.2f, Umsatz: %,.2f", toString(),
				kosten, umsatz);
	}

	public static enum Typ {
		Probe, Auftritt;
	}

	/**
	 * Selektiert jene Termine in denen ein gegebenes Mitglied auch beteiligt ist
	 * @author VHD
	 *
	 */
	public static class TeilnehmerSelektor implements Selector<Termin>{
		private Mitglied m;
		public TeilnehmerSelektor(Mitglied m){
			this.m = m;
		}
		
		@Override
		public boolean select(Termin item) {
			return item.teilnehmer.contains(m);
		}
	}
	
	public static class ZeitraumSelektor implements Selector<Termin> {

		private Zeitraum zeitraum;

		public ZeitraumSelektor(Zeitraum zeitraum) {
			this.zeitraum = zeitraum;
		}

		@Override
		public boolean select(Termin item) {
			return this.zeitraum.enthaelt(item.zeitraum);
		}

	}

	public static class TypSelektor implements Selector<Termin> {
		private Typ typus;

		public TypSelektor(Typ typus) {
			this.typus = typus;
		}

		@Override
		public boolean select(Termin item) {
			return this.typus == item.typus;
		}

	}
}
