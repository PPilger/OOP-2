import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Selektion von Elementen. Welche Elemente sichtbar sind, wird mit
 * Selector-Objekten bestimmt.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Selection<T> implements Iterable<T>, Serializable {
	private static final long serialVersionUID = 1L;

	private List<T> list;
	private transient List<Selector<T>> selectors;

	public Selection() {
		this.list = new ArrayList<T>();
		this.selectors = new ArrayList<Selector<T>>();
	}

	/**
	 * Erstelle eine neue Sicht die auf den selben Daten wie <code>base</code>
	 * arbeitet. Die uebergebenen Selektoren werden zusaetzlich zu den in
	 * <code>base</code> bestehenden uebernommen.
	 * 
	 * @param base
	 * @param selectors
	 */
	public Selection(Selection<T> base, List<Selector<T>> selectors) {
		this.list = base.list;
		this.selectors = selectors;
		this.selectors.addAll(base.selectors);
	}

	/**
	 * @return das erste selektierte Element
	 */
	public T getFirst() {
		Iterator<T> iter = iterator();
		if (iter.hasNext()) {
			return iterator().next();
		}
		return null;
	}

	/**
	 * Fuegt ein Element zur Liste hinzu.
	 * 
	 * @param element
	 */
	public boolean add(T element) {
		return list.add(element);
	}

	/**
	 * Entfernt alle selektierten Elemente.
	 * 
	 * @return die Anzahl der entfernten Elemente
	 */
	public int remove() {
		int removed = 0;

		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
			removed++;
		}

		return removed;
	}

	/**
	 * @return Anzahl der selektierten Elemente
	 */
	public int count() {
		int count = 0;
		Iterator<T> iter = iterator();

		while (iter.hasNext()) {
			iter.next();
			count++;
		}

		return count;
	}

	/**
	 * @param element
	 * @return true, wenn alle Selektoren das Element selektieren, false
	 *         anderenfalls.
	 */
	public boolean selected(T element) {
		for (Selector<T> selector : selectors) {
			if (!selector.select(element)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gibt die Selection im Format einer gewoehnlichen java.util.Collection
	 * zurueck.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Iterator<T> iter = iterator();

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

	/**
	 * Initialisiert die Selektoren, da Selektoren nicht serialisierbar sind.
	 * 
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		selectors = new ArrayList<Selector<T>>();
	}

	/**
	 * Erstellt einen neuen Iterator, der alle selektierten Elemente
	 * durchlaeuft.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			// der Iterator ueber alle Elemente
			Iterator<T> all = list.iterator();

			T current = null; // das aktuelle Element

			// next: das naechste selektierte Element, oder null wenn das Ende
			// erreicht wurde
			T next = nextSelected();

			/**
			 * @return das naechste selektierte Element
			 */
			private T nextSelected() {
				while (all.hasNext()) {
					T next = all.next();

					if (selected(next)) {
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
			public T next() {
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
