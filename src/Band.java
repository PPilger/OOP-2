import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Christian Kletzander
 * 
 */
public class Band implements Serializable {
	private static final long serialVersionUID = 1L;

	// Variablendefinition
	private String name;
	private String ausrichtung;
	private int minProben;

	private Songs repertoire;
	private Termine termine;
	private Mitglieder mitglieder;

	// Konstruktor
	public Band(String name, String ausrichtung, int minProben) {
		this.name = name;
		this.ausrichtung = ausrichtung;
		this.minProben = minProben;

		this.repertoire = new Songs();
		this.termine = new Termine();
		this.mitglieder = new Mitglieder();
	}

	public String getName() {
		return name;
	}

	public Songs getRepertoire() {
		return this.repertoire;
	}

	public Songs getRepertoire(List<Selector<Song>> selectors) {
		return this.repertoire.select(selectors);
	}

	public Termine getTermine() {
		return this.termine;
	}

	public Termine getTermine(List<Selector<Termin>> selectors) {
		return this.termine.select(selectors);
	}

	public Mitglieder getMitglieder() {
		return this.mitglieder;
	}

	public Mitglieder getMitglieder(List<Selector<Mitglied>> selectors) {
		return this.mitglieder.select(selectors);
	}

	/**
	 * Erstellt einen Terminvorschlag der an alle Teilnehmer versendet wird. Der
	 * Termin wird erst im System uebernommen, wenn alle Teilnehmer zugestimmt
	 * haben.
	 * 
	 * Ein Termin ist ungueltig, wenn ein Teilnehmer nicht teilnehmen darf. Wenn
	 * <code>termin</code> ungueltig ist, wird false zurueckgegeben. Ein
	 * Ersatzmitglied muss mindestens <code>minProben</code> pro Jahr
	 * absolvieren um an einem Auftritt teilzunehmen.
	 * 
	 * @param termin
	 * @return true, wenn der Terminvorschlag an alle Teilnehmer gesendet wurde,
	 *         false wenn der Termin ungueltig ist.
	 */
	public boolean sendeTerminvorschlag(Termin termin) {
		Terminvorschlag vorschlag = new Terminvorschlag(termin, termine);
		Termin.TypSelektor auftritte = new Termin.TypSelektor(
				Termin.Typ.Auftritt);
		Mitglied.TypSelector ersatzmitglieder = new Mitglied.TypSelector(true);

		if (auftritte.select(termin)) {
			// Erstelle einen Zeitraum von einem Jahr vor Beginn des Auftrittes.
			Calendar calender = Calendar.getInstance();
			Date von;
			Date bis = termin.getZeitraum().getFirst();
			Zeitraum einJahr;

			calender.setTime(bis);
			calender.add(Calendar.YEAR, -1);
			von = calender.getTime();

			einJahr = new Zeitraum(von, bis);

			for (Mitglied teilnehmer : termin.getTeilnehmer()) {
				if (ersatzmitglieder.select(teilnehmer)) {
					List<Selector<Termin>> selectors = new ArrayList<Selector<Termin>>();
					selectors.add(new Termin.TypSelektor(Termin.Typ.Probe));
					selectors.add(new Termin.ZeitraumSelektor(einJahr));
					selectors.add(new Termin.TeilnehmerSelektor(teilnehmer));

					if (termine.select(selectors).count() < minProben) {
						return false; // Mindestanzahl nicht erfuellt
					}
				}
			}
		}

		for (Mitglied teilnehmer : termin.getTeilnehmer()) {
			teilnehmer.sende(vorschlag);
		}

		return true;
	}

	public String toString() {
		return this.name + ", " + this.ausrichtung;
	}
}
