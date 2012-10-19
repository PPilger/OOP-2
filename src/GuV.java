import java.io.IOException;
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
	 * 			Der zu hinzuf�gende Posten
	 */
	public void add(Posten posten) {
		this.posten.add(posten);
	}
	
	/**
	 * Entfernt alle selektierten Posten
	 * @return Die Anzahl der gel�schten Posten
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
	
	/**
	 * 
	 * @return Gibt die Summe der Einnahmen in einem selektierten Zeitraum an
	 */
	public double getEinnahmen() {
		double einnahmen = 0;
		
		//Durchiterieren der gespeicherten Posten
		Iterator<Posten> iter = posten.iterator();
		while(iter.hasNext()) {
			Posten posten = iter.next();
			if(select(posten)) {
				einnahmen += posten.getEinnahmen();
			}
		}
		
		//Durchiterieren aller Termine der Band
		Iterator<Termin> terminIter = band.getTermine().iterator();
		while(terminIter.hasNext()) {
			einnahmen += terminIter.next().getEinnahmen();
		}
		
		return einnahmen;
	}
	
	/**
	 * 
	 * @return Gibt die Summe der Ausgaben in einem selektierten Zeitraum an
	 */
	public double getAusgaben() {
		double ausgaben = 0;
		
		//Durchiterieren der gespeicherten Posten
		Iterator<Posten> iter = posten.iterator();
		while(iter.hasNext()) {
			Posten posten = iter.next();
			if(select(posten)) {
				ausgaben += posten.getEinnahmen();
			}
		}
		
		//Durchiterieren aller Termine der Band
		Iterator<Termin> terminIter = band.getTermine().iterator();
		while(terminIter.hasNext()) {
			ausgaben += terminIter.next().getAusgaben();
		}
		
		return ausgaben;
	}
	
	/**
	 * 
	 * @return Gibt den Gewinn in einem selektierten Zeitraum an
	 */
	public double getGewinn() {
		double gewinn = 0;
		
		//Durchiterieren der gespeicherten Posten
		Iterator<Posten> iter = posten.iterator();
		while(iter.hasNext()) {
			Posten posten = iter.next();
			if(select(posten)) {
				gewinn += posten.getEinnahmen() - posten.getAusgaben();
			}
		}
		
		//Durchiterieren aller Termine der Band
		Iterator<Termin> terminIter = band.getTermine().iterator();
		while(terminIter.hasNext()) {
			Termin termin = terminIter.next();
			gewinn += termin.getEinnahmen() - termin.getAusgaben();
		}
		
		return gewinn;
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
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		selectors = new ArrayList<Selector<Posten>>();
	}
	
	
	
}