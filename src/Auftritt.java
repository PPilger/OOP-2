import java.io.Serializable;

/**
 * Speichert die auftrittspezifischen Eigenschaften eines Termins.
 * 
 * @author Koegler Alexander
 * 
 */
public class Auftritt extends Termin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double gage;

	/**
	 * 
	 * @param ort
	 *            Ort
	 * @param zeitraum
	 *            Zeitraum
	 * @param dauer
	 *            Laenge in ms.
	 * @param gage
	 *            Gage
	 */
	public Auftritt(String ort, Zeitraum zeitraum, double gage) {
		super(ort, zeitraum);
		this.gage = gage;
	}
	
	public Auftritt(Auftritt other) {
		super(other);
		this.gage = other.gage;
	}

	@Override
	/**
	 * Auftritte haben keinerlei Kosten
	 * @return 0
	 */
	public double getKosten() {
		return 0;
	}

	@Override
	public double getUmsatz() {
		return gage;
	}

	@Override
	public String toString() {
		return "Auftritt: " + super.toString();
	}

	@Override
	public String toDetailString() {
		return String.format("%s, Gage: %,.2f", toString(), gage);
	}
	
	public Auftritt duplikat() {
		return new Auftritt(this);
	}
	
	public void setGage(double gage) {
		this.save();
		this.gage = gage;
	}

	/**
	 * 
	 * @author Christian Kletzander
	 * 
	 */
	public static class AuftrittSelektor implements Selektor<Auftritt> {

		@Override
		public boolean select(Auftritt item) {
			return item instanceof Auftritt;
		}

	}
}
