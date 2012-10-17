import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Sammlung von Mitgliedern.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Mitglieder {
	List<Mitglied> mitglieder;
	List<Selektor<Mitglied>> selectors;

	public Mitglieder() {
		this.mitglieder = new ArrayList<Mitglied>();
		this.selectors = new ArrayList<Selektor<Mitglied>>();
	}

	/**
	 * Erstelle eine neue Mitglieder Sammlung die auf den selben Daten wie
	 * <code>base</code> arbeitet. Es sind jedoch nur Elemente sichtbar, die von
	 * den Selektoren selektiert werden.
	 * 
	 * @param base
	 * @param selectors
	 */
	public Mitglieder(Mitglieder base, List<Selektor<Mitglied>> selectors) {
		this.mitglieder = base.mitglieder;
		this.selectors = selectors;
	}

	/**
	 * Fuegt eine neues Mitglied hinzu, wenn dieser von den Selektoren selektiert
	 * werden kann. Kann er nicht selektiert werden, wird er nicht hinzugefuegt
	 * und false zurueckgegeben.
	 * 
	 * @param mitglied
	 *            das neue Mitglied
	 * @return true wenn das Mitglied hinzugefuegt wurde, false anderenfalls.
	 */
	public boolean add(Mitglied mitglied) {
		if (select(mitglied)) {
			return mitglieder.add(mitglied);
		} else {
			return false;
		}
	}

	/**
	 * Entfernt alle selektierten Mitglieder.
	 * 
	 * @return die Anzahl der entfernten Mitglieder
	 */
	public int remove() {
		int removed = 0;

		Iterator<Mitglied> iter = mitglieder.iterator();
		while (iter.hasNext()) {
			Mitglied mitglied = iter.next();
			if (select(mitglied)) {
				iter.remove();
				removed++;
			}
		}

		return removed;
	}

	/**
	 * @param mitglied
	 * @return true, wenn alle Selektoren das Mitglied selektieren, false
	 *         anderenfalls.
	 */
	private boolean select(Mitglied mitglied) {
		for (Selektor<Mitglied> selector : selectors) {
			if (!selector.select(mitglied)) {
				return false;
			}
		}
		return true;
	}
}