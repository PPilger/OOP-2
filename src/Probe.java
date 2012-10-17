/**
 * Speichert die probenspezifischen Eigenschaften eines Termins ab.
 * 
 * @author Koegler Alexander
 * 
 */
public class Probe extends Termin {

	/**
	 * 
	 * @param ort
	 *            Ort
	 * @param zeitraum
	 *            Zeitraum
	 * @param dauer
	 *            Laenge in ms.
	 * @param raummiete
	 *            Miete fuer Veranstaltung
	 */
	public Probe(String ort, Zeitraum zeitraum, double raummiete) {
		super(ort, zeitraum);
		this.raummiete = raummiete;
	}
	
	public Probe(Probe other) {
		super(other);
		this.raummiete = other.raummiete;
	}
	
	private double raummiete;

	@Override
	/**
	 * Raummieten werden als Kosten verbucht
	 *  @return Raummiete 
	 */
	public double getKosten() {
		return raummiete;
	}

	@Override
	public double getUmsatz() {
		return 0;
	}

	@Override
	public String toString() {
		return "Probe: " + super.toString();
	}

	@Override
	public String toDetailString() {
		return String.format("%s, Raummiete: %,.2f", toString(), raummiete);
	}

	public Probe duplikat() {
		return new Probe(this);
	}
	
	public void setRaummiete(double raummiete) {
		this.save(this.duplikat());
		this.raummiete = raummiete;
	}
	
	/**
	 * 
	 * @author Christian Kletzander
	 * 
	 */
	public static class ProbeSelektor implements Selektor<Probe> {

		@Override
		public boolean select(Probe item) {
			return item instanceof Probe;
		}

	}

}
