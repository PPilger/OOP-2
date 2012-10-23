import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

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
		testeBands();

		Band band = new Band("Green Day", "Rock", 2);

		System.out.println();
		System.out.println("erstelle Mitglieder:");
		erstelleMitglieder(band);
		System.out.println();
		System.out.println("teste Mitglieder:");
		testeMitglieder(band);

		System.out.println();
		System.out.println("erstelle Orte:");
		erstelleOrte(band);
		System.out.println();
		System.out.println("teste Orte:");
		testeOrte(band);

		System.out.println();
		System.out.println("erstelle Termine:");
		erstelleTermine(band);

		System.out.println();
		System.out.println("erstelle Posten:");
		erstellePosten(band);
		System.out.println();
		System.out.println("teste GuV:");
		testeGuV(band);

		System.out.println();
		System.out.println("erstelle Repertoire:");
		erstelleRepertoire(band);

		System.out.println();
		System.out.println("teste Sicherung:");
		testeSicherung(band);
	}

	public static void testeBands() {
		Programm programm = new Programm(false);
		Band band;

		// Testfall: Hinzufuegen von Bands
		//
		// Erwartete Ausgabe:
		// Green Day, Rock
		// Sunrise Avenue, Rock

		band = new Band("Green Day", "Rock", 2);
		programm.addBand(band);

		band = new Band("Sunrise Avenue", "Rock", 2);
		programm.addBand(band);

		System.out.println(programm.getBand("Green Day"));
		System.out.println(programm.getBand("Sunrise Avenue"));
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
		// Schlagzeug Tim (Schlagzeug) [01.01.1990 - ]
		// TelefonNr: 453/56233, Ersatzmitglied
		// Gitarren Joe (Gitarre) [01.01.1990 - ]
		// TelefonNr: 233/12437, Ersatzmitglied
		// Bob Bass (Bass) [01.01.1990 - ]
		// TelefonNr: 111/78755, Ersatzmitglied

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

		zeitraum = new Zeitraum(toDate(1990, 1, 1));
		mitglied = new Mitglied("Schlagzeug Tim", "453/56233", "Schlagzeug",
				zeitraum, true);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());

		zeitraum = new Zeitraum(toDate(1990, 1, 1));
		mitglied = new Mitglied("Gitarren Joe", "233/12437", "Gitarre",
				zeitraum, true);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());

		zeitraum = new Zeitraum(toDate(1990, 1, 1));
		mitglied = new Mitglied("Bob Bass", "111/78755", "Bass", zeitraum, true);
		mitglieder.add(mitglied);
		System.out.println(mitglied.toDetailString());
	}

	public static void testeMitglieder(Band band) {
		List<Selector<Mitglied>> selektoren;
		Mitglied mitglied0;
		Mitglied mitglied1;

		// Testfall: Ausgabe aller Mitglieder
		//
		// Erwartete Ausgabe:
		// [Billie Joe Armstrong, Mike Dirnt, Al Sobrante, Tre Cool, Schlagzeug
		// Tim, Gitarren Joe, Bob Bass]

		System.out.println();
		System.out.println(band.getMitglieder());

		// Testfall: Gitarren Joe wird permanentes Mitglied
		//
		// Erwartete Ausgabe:
		// Gitarren Joe (Gitarre) [01.01.1990 - ]
		// TelefonNr: 233/12437

		selektoren = new ArrayList<Selector<Mitglied>>();
		selektoren.add(new Mitglied.NameSelektor("Gitarren Joe"));

		mitglied0 = band.getMitglieder(selektoren).getFirst();
		mitglied0.setErsatzmitglied(false);

		System.out.println();
		System.out.println(mitglied0.toDetailString());

		// Testfall: Mike Dirnt wird Ersatzmitglied
		//
		// Erwartete Ausgabe:
		// Mike Dirnt (Bass) [04.03.1989 - ]
		// TelefonNr: 321/12323, Ersatzmitglied

		selektoren = new ArrayList<Selector<Mitglied>>();
		selektoren.add(new Mitglied.NameSelektor("Mike Dirnt"));

		mitglied1 = band.getMitglieder(selektoren).getFirst();
		mitglied1.setErsatzmitglied(true);

		System.out.println();
		System.out.println(mitglied1.toDetailString());

		// Mache Aenderungen rueckgaengig, damit weitere Tests auf der
		// urspruenglichen Datenbasis arbeiten koennen!
		mitglied0.setErsatzmitglied(true);
		mitglied1.setErsatzmitglied(false);
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

	public static void erstelleTermine(Band band) {
		Termin termin;
		Ort ort;
		Date von;
		Date bis;
		List<Mitglied> mitglieder;

		// Testfall: Hinzufuegen von Terminen
		//
		// Erwartete Ausgabe:
		// Auftritt: Wien [16.10.2012 06:00 - 16.10.2012 11:00], Kosten: 500,00,
		// Umsatz: 10.000,00
		// Probe: St. Poelten [20.10.2012 08:00 - 20.10.2012 04:00], Kosten:
		// 100,00, Umsatz: 0,00
		// Probe: Korneuburg [21.10.2012 07:00 - 21.10.2012 04:00], Kosten:
		// 200,00, Umsatz: 0,00
		// Auftritt: Wien [22.10.2012 08:00 - 23.10.2012 01:00], Kosten: 200,00,
		// Umsatz: 8.000,00

		ort = band.getOrte(new Ort.BezeichnungSelektor("Wien")).getFirst();
		mitglieder = band.getMitglieder(new Mitglied.TypSelector(false))
				.asList(); // alle permanenten Mitglieder
		von = toDate(2012, 10, 16, 18, 0);
		bis = toDate(2012, 10, 16, 23, 0);
		termin = new Termin(Termin.Typ.Auftritt, ort, von, bis, 500, 10000,
				mitglieder);
		System.out.println(termin.toDetailString());
		band.sendeTerminvorschlag(termin);

		ort = band.getOrte(new Ort.BezeichnungSelektor("St. Poelten"))
				.getFirst();
		mitglieder = band
				.getMitglieder(new Mitglied.InstrumentSelektor("Bass"))
				.asList(); // alle Bass-Spieler
		von = toDate(2012, 10, 20, 8, 0);
		bis = toDate(2012, 10, 20, 16, 0);
		termin = new Termin(Termin.Typ.Probe, ort, von, bis, 100, 0, mitglieder);
		System.out.println(termin.toDetailString());
		band.sendeTerminvorschlag(termin);

		ort = band.getOrte(new Ort.BezeichnungSelektor("Korneuburg"))
				.getFirst();
		mitglieder = band.getMitglieder(new Mitglied.TypSelector(true))
				.asList(); // alle Ersatzmitglieder
		von = toDate(2012, 10, 21, 7, 0);
		bis = toDate(2012, 10, 21, 16, 0);
		termin = new Termin(Termin.Typ.Probe, ort, von, bis, 200, 0, mitglieder);
		System.out.println(termin.toDetailString());
		band.sendeTerminvorschlag(termin);

		for (Mitglied mitglied : band.getMitglieder()) {
			Queue<Terminvorschlag> vorschlaege = mitglied
					.getTerminvorschlaege();
			while (!vorschlaege.isEmpty()) {
				vorschlaege.poll().accept(mitglied);
			}
		}

		ort = band.getOrte(new Ort.BezeichnungSelektor("Wien")).getFirst();
		mitglieder = band.getMitglieder(
				new Mitglied.NameSelektor("Billie Joe Armstrong", "Bob Bass",
						"Al Sobrante", "Tre Cool")).asList();
		von = toDate(2012, 10, 22, 20, 0);
		bis = toDate(2012, 10, 23, 1, 0);
		termin = new Termin(Termin.Typ.Auftritt, ort, von, bis, 200, 8000,
				mitglieder);
		System.out.println(termin.toDetailString());
		band.sendeTerminvorschlag(termin);

		for (Mitglied mitglied : band.getMitglieder()) {
			Queue<Terminvorschlag> vorschlaege = mitglied
					.getTerminvorschlaege();
			while (!vorschlaege.isEmpty()) {
				vorschlaege.poll().accept(mitglied);
			}
		}
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
		// Einnahmen: 23000.0
		// Ausgaben: 4500.0
		// Gewinn: 18500.0

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
		// Einnahmen: 13000.0
		// Ausgaben: 3500.0
		// Gewinn: 9500.0

		System.out.println();
		System.out.println("Einnahmen: " + guv.getEinnahmen());
		System.out.println("Ausgaben: " + guv.getAusgaben());
		System.out.println("Gewinn: " + guv.getGewinn());
	}

	public static void erstelleRepertoire(Band band) {
		Songs songs = band.getRepertoire();
		Song song;
		Zeitraum zeitraum;
		List<Variante> varianten;

		// Testfall: Hinzufuegen von Mitgliedern
		//
		// Erwartete Ausgabe:
		// Song 1 [04.03.2000 - ] [Studio: 3:20, Akustik: 3:30]
		// Song 2 [01.02.2005 - ] [Studio: 3:18]
		// Song 3 [12.09.2003 - ] [Studio: 3:10]

		zeitraum = new Zeitraum(toDate(2000, 3, 4));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Studio", 200));
		varianten.add(new Variante("Akustik", 210));
		song = new Song("Song 1", zeitraum, varianten);
		songs.add(song);
		System.out.println(song.toDetailString());

		zeitraum = new Zeitraum(toDate(2005, 2, 1));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Studio", 198));
		song = new Song("Song 2", zeitraum, varianten);
		songs.add(song);
		System.out.println(song.toDetailString());

		zeitraum = new Zeitraum(toDate(2003, 9, 12));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Studio", 190));
		song = new Song("Song 3", zeitraum, varianten);
		songs.add(song);
		System.out.println(song.toDetailString());
	}

	public static void testeSicherung(Band band) {
		Programm prog;

		// Testfall: Sichern und Laden des Programmzustandes
		//
		// Erwartete Ausgabe:
		// Green Day, Rock
		// Mitglieder: [Billie Joe Armstrong, Mike Dirnt, Al Sobrante, Tre Cool,
		// Schlagzeug Tim, Gitarren Joe, Bob Bass]
		// Termine: [Auftritt: Wien [16.10.2012 06:00 - 16.10.2012 11:00],
		// Probe: St. Poelten [20.10.2012 08:00 - 20.10.2012 04:00], Probe:
		// Korneuburg [21.10.2012 07:00 - 21.10.2012 04:00], Auftritt: Wien
		// [22.10.2012 08:00 - 23.10.2012 01:00]]
		// Repertoire: [Song 1, Song 2, Song 3]
		// Orte: [St. Poelten, Wien, Korneuburg]
		// GuV: [Kuenstlerfoerderung: +5.000,00, Schlagzeugreperatur: -500,00,
		// Studiozubehoer: -3.000,00]

		prog = new Programm(false);
		prog.addBand(band);
		prog.quit();

		prog = new Programm();
		band = prog.getBand("Green Day");

		System.out.println(band);
		System.out.println("Mitglieder: " + band.getMitglieder());
		System.out.println("Termine: " + band.getTermine());
		System.out.println("Repertoire: " + band.getRepertoire());
		System.out.println("Orte: " + band.getOrte());
		System.out.println("GuV: " + band.getGuV());
	}
}
