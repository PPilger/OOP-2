import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Eine Sammlung von Songs.
 * 
 * @author Peter Pilgerstorfer
 * 
 */
public class Songs implements Serializable {
	private static final long serialVersionUID = 1L;
	
	List<Song> songs;
	List<Selector<Song>> selectors;

	public Songs() {
		this.songs = new ArrayList<Song>();
		this.selectors = new ArrayList<Selector<Song>>();
	}

	/**
	 * Erstelle eine neue Song Sammlung die auf den selben Daten wie
	 * <code>base</code> arbeitet. Es sind jedoch nur Elemente sichtbar, die von
	 * den Selektoren selektiert werden.
	 * 
	 * @param base
	 * @param selectors
	 */
	public Songs(Songs base, List<Selector<Song>> selectors) {
		this.songs = base.songs;
		this.selectors = selectors;
		this.selectors.addAll(base.selectors);
	}

	/**
	 * Fuegt einen neuen Song hinzu.
	 * 
	 * @param song
	 *            der neue Song
	 */
	public void add(Song song) {
		songs.add(song);
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
	
	public List<SongVariante> getSongVarianten() {
		return getSongVarianten(new ArrayList<Selector<Variante>>());
	}
	
	public List<SongVariante> getSongVarianten(List<Selector<Variante>> selectors) {
		List<SongVariante> songVarianten = new ArrayList<SongVariante>();
		for(Song song : songs) {
			if(select(song)) {
				for(Variante variante : song.getVarianten()) {
					if(select(variante, selectors)) {
						songVarianten.add(new SongVariante(song, variante));
					}
				}
			}
		}
		return songVarianten;
	}

	/**
	 * @param song
	 * @return true, wenn alle Selektoren den Song selektieren, false
	 *         anderenfalls.
	 */
	private boolean select(Song song) {
		for (Selector<Song> selector : selectors) {
			if (!selector.select(song)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * @param variante
	 * @return true, wenn alle Selektoren die Variante selektieren, false
	 *         anderenfalls.
	 */
	private boolean select(Variante variante, List<Selector<Variante>> selectors) {
		for (Selector<Variante> selector : selectors) {
			if (!selector.select(variante)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append('[');
		
		Iterator<Song> iter = songs.iterator();
		while(iter.hasNext()) {
			Song song = iter.next();
			if (select(song)) {
				builder.append(song);
				break;
			}
		}
		while (iter.hasNext()) {
			Song song = iter.next();
			if (select(song)) {
				builder.append(", ");
				builder.append(song);
			}
		}
		
		builder.append(']');
		
		return builder.toString();
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		selectors = new ArrayList<Selector<Song>>();
	}
}
