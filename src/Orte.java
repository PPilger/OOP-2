import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Orte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Ort> orte;
	private transient List<Selector<Ort>> selectors;

	public Orte() {
		this.orte = new ArrayList<Ort>();
		this.selectors = new ArrayList<Selector<Ort>>();
	}

	public Orte(Orte base, List<Selector<Ort>> selectors) {
		this.orte = base.orte;
		this.selectors = selectors;
		this.selectors.addAll(base.selectors);
	}

	public void add(Ort ort) {
		orte.add(ort);
	}

	public int remove() {
		int cnt = 0;
		Iterator<Ort> iter = orte.iterator();
		while (iter.hasNext()) {
			Ort o = iter.next();
			if (select(o)) {
				iter.remove();
				cnt++;
			}
		}

		return cnt;
	}

	private boolean select(Ort ort) {
		for (Selector<Ort> sel : selectors) {
			if (!sel.select(ort))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append('[');

		Iterator<Ort> iter = orte.iterator();
		while (iter.hasNext()) {
			Ort ort = iter.next();
			if (select(ort)) {
				builder.append(ort);
				break;
			}
		}
		while (iter.hasNext()) {
			Ort ort = iter.next();
			if (select(ort)) {
				builder.append(", ");
				builder.append(ort);
			}
		}

		builder.append(']');

		return builder.toString();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		selectors = new ArrayList<Selector<Ort>>();
	}
}
