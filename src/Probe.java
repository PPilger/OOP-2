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
	 * @param zeitIntervall
	 *            ZeitIntervall
	 * @param dauer
	 *            Laenge in ms.
	 * @param raummiete
	 *            Miete fuer Veranstaltung
	 */
	public Probe(String ort, ZeitIntervall zeitIntervall, double raummiete) {
		super(ort, zeitIntervall);
		this.raummiete = raummiete;
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
}
