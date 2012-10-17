/**
 * Speichert die auftrittspezifischen Eigenschaften eines Termins.
 * 
 * @author Koegler Alexander
 * 
 */
public class Auftritt extends Termin {

	private double gage;

	/**
	 * 
	 * @param ort
	 *            Ort
	 * @param zeitIntervall
	 *            ZeitIntervall
	 * @param dauer
	 *            Laenge in ms.
	 * @param gage
	 *            Gage
	 */
	public Auftritt(String ort, ZeitIntervall zeitIntervall, double gage) {
		super(ort, zeitIntervall);
		this.gage = gage;
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

	/**
	 * 
	 * @author Christian Kletzander
	 * 
	 */
	public static class GageSelektor implements Selektor<Auftritt> {

		private double gage;

		public GageSelektor(double gage) {
			this.gage = gage;
		}

		@Override
		public boolean select(Auftritt item) {
			return item.gage == this.gage;
		}

	}
}
