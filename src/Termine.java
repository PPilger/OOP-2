import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Sammlung von Terminen.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Termine implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Termin> termine;
	private transient List<Selector<Termin>> selectors;

	public Termine() {
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
	public Termine(Termine base, List<Selector<Termin>> selectors) {
		this.termine = base.termine;
		this.selectors = selectors;
	}

	/**
	 * Fuegt einen neuen Termin hinzu, wenn dieser von den Selektoren selektiert
	 * werden kann. Kann er nicht selektiert werden, wird er nicht hinzugefuegt
	 * und false zurueckgegeben.
	 * 
	 * @param termin
	 *            der neue Termin
	 * @return true wenn der Termin hinzugefuegt wurde, false anderenfalls.
	 */
	public boolean add(Termin termin) {
		if (select(termin)) {
			return termine.add(termin);
		} else {
			return false;
		}
	}

	/**
	 * Entfernt alle selektierten Termine
	 * 
	 * @return die Anzahl der entfernten Termine
	 */
	public int remove() {
		int removed = 0;

		Iterator<Termin> iter = termine.iterator();
		while (iter.hasNext()) {
			Termin termin = iter.next();
			if (select(termin)) {
				iter.remove();
				removed++;
			}
		}

		return removed;
	}

	public void setOrt(Ort ort) {
		for (Termin termin : termine) {
			if (select(termin)) {
				termin.setOrt(ort);
			}
		}
	}
	
	public void setZeitraum(Zeitraum zeitraum) {
		for (Termin termin : termine) {
			if (select(termin)) {
				termin.setZeitraum(zeitraum);
			}
		}
	}

	public void setKosten(double kosten) {
		for (Termin termin : termine) {
			if (select(termin)) {
				termin.setKosten(kosten);
			}
		}
	}

	public void setUmsatz(double umsatz) {
		for (Termin termin : termine) {
			if (select(termin)) {
				termin.setUmsatz(umsatz);
			}
		}
	}
	
	public void undo() {
		for (Termin termin : termine) {
			if (select(termin)) {
				termin.undo();
				//false => delete termin?!?
			}
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

		for (Termin termin : termine) {
			if (select(termin)) {
				gewinn += termin.getUmsatz() - termin.getKosten();
			}
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

		for (Termin termin : termine) {
			if (select(termin)) {
				kosten += termin.getKosten();
			}
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
		
		builder.append('[');
		
		Iterator<Termin> iter = termine.iterator();
		while(iter.hasNext()) {
			Termin termin = iter.next();
			if (select(termin)) {
				builder.append(termin);
				break;
			}
		}
		while (iter.hasNext()) {
			Termin termin = iter.next();
			if (select(termin)) {
				builder.append(", ");
				builder.append(termin);
			}
		}
		
		builder.append(']');
		
		return builder.toString();
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		selectors = new ArrayList<Selector<Termin>>();
	}
}
