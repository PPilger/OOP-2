import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Sammlung von Songs.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Songs {
	List<Song> songs;
	List<Selektor<Song>> selectors;

	public Songs() {
		this.songs = new ArrayList<Song>();
		this.selectors = new ArrayList<Selektor<Song>>();
	}

	/**
	 * Erstelle eine neue Song Sammlung die auf den selben Daten wie
	 * <code>base</code> arbeitet. Es sind jedoch nur Elemente sichtbar, die von
	 * den Selektoren selektiert werden.
	 * 
	 * @param base
	 * @param selectors
	 */
	public Songs(Songs base, List<Selektor<Song>> selectors) {
		this.songs = base.songs;
		this.selectors = selectors;
	}

	/**
	 * Fuegt einen neuen Song hinzu, wenn dieser von den Selektoren selektiert
	 * werden kann. Kann er nicht selektiert werden, wird er nicht hinzugefuegt
	 * und false zurueckgegeben.
	 * 
	 * @param song
	 *            der neue Song
	 * @return true wenn der Song hinzugefuegt wurde, false anderenfalls.
	 */
	public boolean add(Song song) {
		if (select(song)) {
			return songs.add(song);
		} else {
			return false;
		}
	}

	/**
	 * Entfernt alle selektierten Songs aus der Sammlung
	 * 
	 * @return die Anzahl der entfernten Songs
	 */
	public int remove() {
		int removed = 0;

		Iterator<Song> iter = songs.iterator();
		while (iter.hasNext()) {
			Song song = iter.next();
			if (select(song)) {
				iter.remove();
				removed++;
			}
		}

		return removed;
	}

	/**
	 * @param song
	 * @return true, wenn alle Selektoren den Song selektieren, false
	 *         anderenfalls.
	 */
	private boolean select(Song song) {
		for (Selektor<Song> selector : selectors) {
			if (!selector.select(song)) {
				return false;
			}
		}
		return true;
	}
}
