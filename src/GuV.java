import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Christian Kletzander
 *
 */

public class GuV implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Posten> posten;
	private Band band;
	private transient List<Selector<Posten>> selectors;
	
	public GuV(Band band) {
		this.band = band;
		this.posten = new ArrayList<Posten>();
		this.selectors = new ArrayList<Selector<Posten>>();
	}
	
	/**
	 * 
	 * @param posten
	 * 			Der zu hinzufügende Posten
	 */
	public void add(Posten posten) {
		this.posten.add(posten);
	}
	
	/**
	 * Entfernt alle selektierten Posten
	 * @return Die Anzahl der gelöschten Posten
	 */
	public int remove() {
		int removed = 0;
		
		Iterator<Posten> iter = posten.iterator();
		while(iter.hasNext()) {
			Posten posten = iter.next();
			if(select(posten)) {
				iter.remove();
				removed++;
			}
		}
		
		return removed;
	}
	
	private boolean select(Posten posten) {
		for(Selector<Posten> selector : this.selectors) {
			if(!selector.select(posten)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append('[');
		
		Iterator<Posten> iter = this.posten.iterator();
		while(iter.hasNext()) {
			Posten posten = iter.next();
			if (select(posten)) {
				builder.append(posten);
				break;
			}
		}
		while (iter.hasNext()) {
			Posten posten = iter.next();
			if (select(posten)) {
				builder.append(", ");
				builder.append(posten);
			}
		}
		
		builder.append(']');
		
		return builder.toString();
	}
	
	
	
	
}
