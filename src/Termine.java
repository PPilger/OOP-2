import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Sammlung von Terminen.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Termine extends Selection<Termin> {
	private static final long serialVersionUID = 1L;

	public Termine() {
	}

	/**
	 * Erstelle eine neue Termin Sammlung die auf den selben Daten wie
	 * <code>base</code> arbeitet. Es sind jedoch nur Elemente sichtbar, die von
	 * den Selektoren selektiert werden.
	 * 
	 * @param base
	 * @param selectors
	 */
	private Termine(Termine base, List<Selector<Termin>> selectors) {
		super(base, selectors);
	}

	public Termine select(List<Selector<Termin>> selectors) {
		return new Termine(this, selectors);
	}

	/**
	 * Fuegt einen neuen Termin hinzu, sofern dieser keine Teilnehmer besitzt.
	 * Um einen Termin mit Teilnehmern anzulegen sollte
	 * <code>Band.sendeTerminvorschlag()</code> verwendet werden.
	 * 
	 * @return true, wenn der Termin hinzugefuegt wurde, false wenn er
	 *         Teilnehmer hat.
	 */
	@Override
	public boolean add(Termin termin) {
		if (termin.getTeilnehmer().isEmpty()) {
			return super.add(termin);
		}

		return false;
	}

	/**
	 * Fuegt einen Termin zur Liste hinzu, wenn alle Teilnehmer dem
	 * entsprechenden Terminvorschlag zugestimmt haben.
	 * 
	 * @param vorschlag
	 */
	public void add(Terminvorschlag vorschlag) {
		if (vorschlag.accepted()) {
			super.add(vorschlag.getTermin());
		}
	}

	/**
	 * Entfernt alle selektierten Termine
	 * 
	 * @return die Anzahl der entfernten Termine
	 */
	@Override
	public int remove() {
		int removed = 0;

		Iterator<Termin> iter = iterator();
		while (iter.hasNext()) {
			Termin termin = iter.next();

			for (Mitglied teilnehmer : termin.getTeilnehmer()) {
				teilnehmer.sende(termin + " wurde entfernt!");
			}

			iter.remove();
			removed++;
		}

		return removed;
	}

	public void setOrt(Ort ort) {
		for (Termin termin : this) {
			termin.setOrt(ort);
		}
	}

	public void setZeitraum(Date von, Date bis) {
		for (Termin termin : this) {
			termin.setZeitraum(von, bis);
		}
	}

	public void setKosten(double kosten) {
		for (Termin termin : this) {
			termin.setAusgaben(kosten);
		}
	}

	public void setUmsatz(double umsatz) {
		for (Termin termin : this) {
			termin.setEinnahmen(umsatz);
		}
	}

	public void undo() {
		for (Termin termin : this) {
			termin.undo();
		}
	}

	/**
	 * Berechnet den Gewinn aller selektierten Termine.
	 * 
	 * @param zeitpunkt
	 * @return der Gewinn
	 */
	public double getGewinn() {
		double gewinn = 0;

		for (Termin termin : this) {
			gewinn += termin.getEinnahmen() - termin.getAusgaben();
		}

		return gewinn;
	}

	/**
	 * Berechnet die Kosten aller selektierten Termine.
	 * 
	 * @param zeitpunkt
	 * @return die Kosten
	 */
	public double getKosten() {
		double kosten = 0;

		for (Termin termin : this) {
			kosten += termin.getAusgaben();
		}

		return kosten;
	}
}
