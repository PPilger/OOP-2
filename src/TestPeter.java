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

		erstelleMitglieder(band);
		
		// erstelleOrte(band);
		// testeOrte(band);

		// erstellePosten(band);
		// testeGuV(band);
	}

	public static void erstelleMitglieder(Band band) {
		Mitglieder mitglieder = band.getMitglieder();
		Mitglied mitglied;
		Zeitraum zeitraum;

		// Testfall: Hinzufuegen von Mitgliedern
		//
		// Erwartete Ausgabe:
		// Billie Joe Armstrong (Gitarre) [04.03.1989 - ]
		// TelefonNr: 123/45678
		// Mike Dirnt (Bass) [04.03.1989 - ]
		// TelefonNr: 321/12323
		// Al Sobrante (Schlagzeug) [04.03.1989 - 01.01.1990]
		// TelefonNr: 345/54327
		// Tre Cool (Schlagzeug) [01.01.1990 - ]
		// TelefonNr: 943/38321

		zeitraum = new Zeitraum(toDate(1989, 3, 4));
		mitglied = new Mitglied("Billie Joe Armstrong", "123/45678", "Gitarre",
				zeitraum, false);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());

		zeitraum = new Zeitraum(toDate(1989, 3, 4));
		mitglied = new Mitglied("Mike Dirnt", "321/12323", "Bass", zeitraum,
				false);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());

		zeitraum = new Zeitraum(toDate(1989, 3, 4), toDate(1990, 1, 1));
		mitglied = new Mitglied("Al Sobrante", "345/54327", "Schlagzeug",
				zeitraum, false);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());

		zeitraum = new Zeitraum(toDate(1990, 1, 1));
		mitglied = new Mitglied("Tre Cool", "943/38321", "Schlagzeug",
				zeitraum, false);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());
	}
	
	public static void testeMitglieder(Band band) {
		
	}

	public static void erstelleOrte(Band band) {
		Orte orte = band.getOrte();
		Ort ort;
		List<String> infrastruktur = new ArrayList<String>();

		// Testfall: Hinzufuegen von Orten
		//
		// Erwartete Ausgabe:
		// St. Poelten [Proberaum, Stadion]
		// Wien [Musikgeschaeft, Gitarren Fachhandel, Proberaum, Stadion]
		// Korneuburg [Proberaum]

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
	}

	public static void testeOrte(Band band) {
		List<Selector<Ort>> selektoren;

		// Testfall: Ausgabe aller Orte
		//
		// Erwartete Ausgabe:
		// [St. Poelten, Wien, Korneuburg]

		System.out.println();
		System.out.println(band.getOrte());

		// Testfall: Ausgabe von Wien
		//
		// Erwartete Ausgabe:
		// [Wien]

		selektoren = new ArrayList<Selector<Ort>>();
		selektoren.add(new Ort.BezeichnungSelektor("Wien"));

		System.out.println();
		System.out.println(band.getOrte(selektoren));

		// Testfall: Ausgabe aller Orte mit Stadion
		//
		// Erwartete Ausgabe:
		// [St. Poelten, Wien]

		selektoren = new ArrayList<Selector<Ort>>();
		selektoren.add(new Ort.InfrastrukturSelektor("Stadion"));

		System.out.println();
		System.out.println(band.getOrte(selektoren));

		// Testfall: Ausgabe aller Orte mit Musikgeschaeft
		//
		// Erwartete Ausgabe:
		// [Wien]

		selektoren = new ArrayList<Selector<Ort>>();
		selektoren.add(new Ort.InfrastrukturSelektor("Musikgeschaeft"));

		System.out.println();
		System.out.println(band.getOrte(selektoren));
	}

	public static void erstellePosten(Band band) {
		GuV guv = band.getGuV();
		Posten posten;

		// Testfall: Hinzufuegen von Posten
		//
		// Erwartete Ausgabe:
		// 20.10.2012: Kuenstlerfoerderung (+5.000,00/-0,00)
		// 18.10.2012: Schlagzeugreperatur (+0,00/-500,00)
		// 21.10.2012: Studiozubehoer (+0,00/-3.000,00)

		posten = new Posten(5000, 0, "Kuenstlerfoerderung",
				toDate(2012, 10, 20));
		guv.add(posten);
		System.out.println(posten.toDetailString());

		posten = new Posten(0, 500, "Schlagzeugreperatur", toDate(2012, 10, 18));
		guv.add(posten);
		System.out.println(posten.toDetailString());

		posten = new Posten(0, 3000, "Studiozubehoer", toDate(2012, 10, 21));
		guv.add(posten);
		System.out.println(posten.toDetailString());
	}

	public static void testeGuV(Band band) {
		GuV guv;
		List<Selector<Posten>> selektoren;
		Zeitraum zeitraum;

		// Testfall: Ausgabe aller GuV-Posten (ohne Termine)
		//
		// Erwartete Ausgabe:
		// [Kuenstlerfoerderung: +5.000,00, Schlagzeugreperatur: -500,00,
		// Studiozubehoer: -3.000,00]

		System.out.println();
		System.out.println(band.getGuV());

		// Testfall: Ausgabe von Einnahmen, Ausgaben und Gewinn aller Posten.
		// (incl. Termine)
		//
		// Erwartete Ausgabe:
		// Einnahmen: 5000.0
		// Ausgaben: 3500.0
		// Gewinn: 1500.0

		guv = band.getGuV();

		System.out.println();
		System.out.println("Einnahmen: " + guv.getEinnahmen());
		System.out.println("Ausgaben: " + guv.getAusgaben());
		System.out.println("Gewinn: " + guv.getGewinn());

		// Testfall: Ausgabe aller GuV-Posten (ohne Termine)
		// seit 19.10.2012
		//
		// Erwartete Ausgabe:
		// [Kuenstlerfoerderung: +5.000,00, Studiozubehoer: -3.000,00]

		zeitraum = new Zeitraum(toDate(2012, 10, 19));
		selektoren = new ArrayList<Selector<Posten>>();
		selektoren.add(new Posten.ZeitraumSelektor(zeitraum));
		guv = band.getGuV(selektoren);

		System.out.println();
		System.out.println(guv);

		// Testfall: Ausgabe von Einnahmen, Ausgaben und Gewinn aller Posten.
		// (incl. Termine)
		// seit 19.10.2012
		//
		// Erwartete Ausgabe:
		// Einnahmen: 5000.0
		// Ausgaben: 3000.0
		// Gewinn: 2000.0

		System.out.println();
		System.out.println("Einnahmen: " + guv.getEinnahmen());
		System.out.println("Ausgaben: " + guv.getAusgaben());
		System.out.println("Gewinn: " + guv.getGewinn());

	}
}
