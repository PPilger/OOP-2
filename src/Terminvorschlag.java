import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Terminvorschlag implements Serializable {
	private static final long serialVersionUID = 1L;

	private Termin termin;
	private List<Termin> target;
	private List<Mitglied> offen;

	public Terminvorschlag(Termin termin, List<Termin> target) {
		this.termin = termin;
		this.target = target;
		this.offen = new ArrayList<Mitglied>(termin.getTeilnehmer());
	}

	public void accept(Mitglied mitglied) {
		offen.remove(mitglied);
		if (offen.isEmpty()) {
			target.add(termin);
		}
	}

	public void decline(Mitglied mitglied, String nachricht) {
		if (termin.getTeilnehmer().contains(mitglied)) {
			for (Mitglied m : termin.getTeilnehmer()) {
				m.revidiere(this);
				m.sende(mitglied + ": " + nachricht + " - " + termin);
			}
		}
	}

	public String toString() {
		return termin.toString();
	}

	public String toDetailString() {
		return termin.toDetailString() + ", Ausstehend: " + offen;
	}
}
