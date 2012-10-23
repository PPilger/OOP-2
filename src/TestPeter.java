import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestPeter {
	private static Calendar kalender = Calendar.getInstance();

	private static Date toDate(int jahr, int monat, int tag) {
		kalender.set(jahr, monat - 1, tag);
		return kalender.getTime();
	}

	private static Date toDate(int jahr, int monat, int tag, int stunde,
			int minute) {
		kalender.set(jahr, monat - 1, tag, stunde, minute);
		return kalender.getTime();
	}
	
	public static void main(String[] args) {
		Band band = new Band("Green Day", "Rock", 10);
		
		erstelleOrte(band);
	}

	public static void erstelleOrte(Band band) {
		Orte orte = band.getOrte();
		Ort ort;
		List<String> infrastruktur = new ArrayList<String>();
		List<Selector<Ort>> selektoren;

		//Testfall: Hinzufuegen von Orten
		//
		//Erwartete Ausgabe:
		//St. Poelten [Proberaum, Stadion]
		//Wien [Musikgeschaeft, Gitarren Fachhandel, Proberaum, Stadion]
		//Korneuburg [Proberaum]
		
		infrastruktur = new ArrayList<String>();
		infrastruktur.add("Proberaum");
		infrastruktur.add("Stadion");
		ort = new Ort("St. Poelten", infrastruktur);
		System.out.println(ort.toDetailString());
		orte.add(ort);
		
		infrastruktur = new ArrayList<String>();
		infrastruktur.add("Musikgeschaeft");
		infrastruktur.add("Gitarren Fachhandel");
		infrastruktur.add("Proberaum");
		infrastruktur.add("Stadion");
		ort = new Ort("Wien", infrastruktur);
		System.out.println(ort.toDetailString());
		orte.add(ort);

		infrastruktur = new ArrayList<String>();
		infrastruktur.add("Proberaum");
		ort = new Ort("Korneuburg", infrastruktur);
		System.out.println(ort.toDetailString());
		orte.add(ort);
		
		
		//Testfall: Ausgabe aller Orte
		//
		//Erwartete Ausgabe:
		//[St. Poelten, Wien, Korneuburg]
		
		System.out.println();
		System.out.println(band.getOrte());

		
		//Testfall: Ausgabe von Wien
		//
		//Erwartete Ausgabe:
		//[Wien]

		selektoren = new ArrayList<Selector<Ort>>();
		selektoren.add(new Ort.BezeichnungSelektor("Wien"));
		
		System.out.println();
		System.out.println(band.getOrte(selektoren));

		
		//Testfall: Ausgabe aller Orte mit Stadion
		//
		//Erwartete Ausgabe:
		//[St. Poelten, Wien]
		
		selektoren = new ArrayList<Selector<Ort>>();
		selektoren.add(new Ort.InfrastrukturSelektor("Stadion"));
		
		System.out.println();
		System.out.println(band.getOrte(selektoren));

		
		//Testfall: Ausgabe aller Orte mit Musikgeschaeft
		//
		//Erwartete Ausgabe:
		//[Wien]

		selektoren = new ArrayList<Selector<Ort>>();
		selektoren.add(new Ort.InfrastrukturSelektor("Musikgeschaeft"));
		
		System.out.println();
		System.out.println(band.getOrte(selektoren));
	}
}
