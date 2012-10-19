import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Sammlung von Terminen.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Termine implements Iterable<Termin>, Serializable {
	private static final long serialVersionUID = 1L;

	private Band band;
	private List<Termin> termine;
	private transient List<Selector<Termin>> selectors;

	public Termine(Band band) {
		this.band = band;
		this.termine = new ArrayList<Termin>();
		this.selectors = new ArrayList<Selector<Termin>>();
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
		this.band = base.band;
		this.termine = base.termine;
		this.selectors = selectors;
		this.selectors.addAll(base.selectors);
	}

	public Termine selection(List<Selector<Termin>> selectors) {
		return new Termine(this, selectors);
	}

	public Termin getFirst() {
		Iterator<Termin> iter = iterator();
		if (iter.hasNext()) {
			return iterator().next();
		}
		return null;
	}

	/**
	 * Erstellt einen neuen Terminvorschlag. Dieser wird an alle Teilnehmer
	 * versendet. Wenn alle Teilnehmer annehmen, wird der Termin automatisch
	 * gespeichert.
	 * 
	 * @param termin
	 *            der neue Termin
	 */
	public void add(Termin termin) {
		Terminvorschlag vorschlag = new Terminvorschlag(termin, termine);
		Termin.TypSelektor auftritte = new Termin.TypSelektor(
				Termin.Typ.Auftritt);
		Mitglied.TypSelector ersatzmitglieder = new Mitglied.TypSelector(true);

		if (auftritte.select(termin)) {
			for (Mitglied teilnehmer : termin.getTeilnehmer()) {
				if (ersatzmitglieder.select(teilnehmer)) {
					List<Selector<Termin>> selectors = new ArrayList<Selector<Termin>>();
					selectors.add(new Termin.TypSelektor(Termin.Typ.Probe));
					selectors.add(new Termin.TeilnehmerSelektor(teilnehmer));

					if (selection(selectors).count() < band.getMinimumProben()) {
						return; // Mindestanzahl nicht erfuellt
					}
				}

			}
		}

		for (Mitglied teilnehmer : termin.getTeilnehmer()) {
			teilnehmer.sende(vorschlag);
		}
	}

	/**
	 * Entfernt alle selektierten Termine
	 * 
	 * @return die Anzahl der entfernten Termine
	 */
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

	/**
	 * Zaelt die Termine die Selektiert sind
	 * 
	 * @return Anzahl der Termine die selektiert sind
	 */
	public int count() {
		int cnt = 0;
		Iterator<Termin> iter = iterator();

		while (iter.hasNext()) {
			iter.next();
			cnt++;
		}

		return cnt;
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

	/**
	 * @param termin
	 * @return true, wenn alle Selektoren den Termin selektieren, false
	 *         anderenfalls.
	 */
	private boolean select(Termin termin) {
		for (Selector<Termin> selector : selectors) {
			if (!selector.select(termin)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Iterator<Termin> iter = iterator();

		builder.append('[');

		if (iter.hasNext()) {
			builder.append(iter.next());
		}
		while (iter.hasNext()) {
			builder.append(", ");
			builder.append(iter.next());
		}

		builder.append(']');

		return builder.toString();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		selectors = new ArrayList<Selector<Termin>>();
	}

	@Override
	public Iterator<Termin> iterator() {
		return new Iterator<Termin>() {
			Iterator<Termin> all = termine.iterator();

			Termin current = null; // das aktuelle Element

			// next: das naechste selektierte Element, oder null wenn das Ende
			// erreicht wurde
			Termin next = nextSelected();

			/**
			 * @return das naechste selektierte Element
			 */
			private Termin nextSelected() {
				while (all.hasNext()) {
					Termin next = all.next();

					if (select(next)) {
						return next;
					}
				}

				return null;
			}

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public Termin next() {
				current = next;
				next = nextSelected();
				return current;
			}

			@Override
			public void remove() {
				all.remove();
			}
		};
	}
}
