import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class TestAlexander {
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
		Programm prog = new Programm(false);
		Band band;
		Mitglieder mg;
		Mitglieder teilnehmer;
		Zeitraum zeitraum;
		Termin termin;
		Ort ort;
		Date von;
		Date bis;
		boolean erfolgreich;

		// Erstelle Band
		prog.addBand(new Band("Sido", "Berlin-Rap", 4));
		band = prog.getBand("Sido");
		mg = band.getMitglieder();

		// Erstelle Mitglieder
		zeitraum = new Zeitraum(toDate(1999, 5, 10));
		mg.add(new Mitglied("Sido", "12345", "Gesang", zeitraum, false));

		zeitraum = new Zeitraum(toDate(1999, 10, 10));
		mg.add(new Mitglied("Bg Girl1", "123765", "Getanze", zeitraum, false));

		zeitraum = new Zeitraum(toDate(1999, 10, 10));
		mg.add(new Mitglied("Bg Girl2", "3412345", "Getanze", zeitraum, false));

		zeitraum = new Zeitraum(toDate(2002, 1, 25));
		mg.add(new Mitglied("Bg Girl3", "1234475", "Getanze", zeitraum, true));

		zeitraum = new Zeitraum(toDate(2002, 1, 25));
		mg.add(new Mitglied("Bg Girl4", "12342315", "Getanze", zeitraum, true));

		zeitraum = new Zeitraum(toDate(2012, 25, 8));
		mg.add(new Mitglied("Heinzl", "12345", "Gesang", zeitraum, true));

		zeitraum = new Zeitraum(toDate(2010, 9, 1));
		mg.add(new Mitglied("Sidos Double", "1234778", "Gesang", zeitraum, true));

		// Erstelle Orte
		List<String> aaa = new ArrayList<String>();
		aaa.add("Umkleideraum");
		aaa.add("Presseraum");
		aaa.add("Proberaum");
		aaa.add("Buehne");
		aaa.add("10000Watt Sound");
		List<String> bbb = new ArrayList<String>();
		bbb.add("Umkleideraum");
		bbb.add("Proberaum");
		bbb.add("Buehne");
		bbb.add("5000Watt Sound");
		List<String> ccc = new ArrayList<String>();
		ccc.add("Umkleideraum");
		ccc.add("Restaurant");
		ccc.add("Pult mit Mikrofon");
		ccc.add("1000Watt Sound");

		band.getOrte().add(new Ort("Kueniglberg", aaa));
		band.getOrte().add(new Ort("KdW-Berlin", ccc));
		band.getOrte().add(new Ort("Kreuzberger-Club", ccc));
		band.getOrte().add(new Ort("Wr. Stadthalle", aaa));
		band.getOrte().add(new Ort("Szene Wien", bbb));
		band.getOrte().add(new Ort("Babenbergerhalle Kloburg", bbb));

		// Testfall: Termin hinzufuegen mit allen Mitgliedern
		// Schlaegt fehl, da Ersatzmitglieder noch keine Proben haben
		//
		// Erwartete Ausgabe:
		// Termin erstellen erfolgreich = false
		// Ersatzmitglieder haben noch keine Probe

		ort = band.getOrte(new Ort.BezeichnungSelektor("Kueniglberg"))
				.getFirst();
		von = toDate(2012, 4, 1);
		bis = toDate(2012, 4, 1);
		teilnehmer = band.getMitglieder();
		termin = new Termin(Termin.Typ.Auftritt, ort, von, bis, 2500.99, .0,
				teilnehmer.asList());
		erfolgreich = band.sendeTerminvorschlag(termin);

		System.out.println("Termin erstellen erfolgreich = " + erfolgreich);
		System.out.println("Ersatzmitglieder haben noch keine Proben");
		System.out.println();

		// Testfall: Termin hinzufuegen mit regulaeren Mitgliedern
		//
		// Erwartete Ausgabe:
		// Termin erstellen erfolgreich = true

		// ort wie beim Vorherigen
		von = toDate(2012, 4, 1);
		bis = toDate(2012, 4, 1);
		teilnehmer = band.getMitglieder(new Mitglied.TypSelector(false));
		termin = new Termin(Termin.Typ.Auftritt, ort, von, bis, 2500.99, .0,
				teilnehmer.asList());
		erfolgreich = band.sendeTerminvorschlag(termin);
		System.out.println("Termin erstellen erfolgreich = " + erfolgreich);
		System.out.println();

		// Testfall: Terminvorschlaege anzeigen und akzeptieren
		//
		// Erwartete Ausgabe:
		// Sido: Auftritt: Kueniglberg [01.04.2012 10:25 - 01.04.2012 10:25]
		// Bg Girl1: Auftritt: Kueniglberg [01.04.2012 10:25 - 01.04.2012 10:25]
		// Bg Girl2: Auftritt: Kueniglberg [01.04.2012 10:25 - 01.04.2012 10:25]
		// Bg Girl3: null
		// Bg Girl4: null
		// Heinzl: null
		// Sidos Double: null
		//
		// Akzeptierte Termine: [Auftritt: Kueniglberg [01.04.2012 10:25 -
		// 01.04.2012 10:25]]

		for (Mitglied mitglied : band.getMitglieder()) {
			Terminvorschlag vorschlag = mitglied.getTerminvorschlaege().poll();

			System.out.println(mitglied + ": " + vorschlag);

			if (vorschlag != null) {
				vorschlag.accept(mitglied);
			}
		}

		System.out.println();
		System.out.println("Akzeptierte Termine: " + band.getTermine());
		System.out.println();

		// Testfall: Termin entfernen und Nachrichten ausgeben
		//
		// Erwartete Ausgabe:
		// Anzahl entfernter Termine: 1
		//
		// Sido: Auftritt: Kueniglberg [01.04.2012 10:49 - 01.04.2012 10:49]
		// wurde entfernt!
		// Bg Girl1: Auftritt: Kueniglberg [01.04.2012 10:49 - 01.04.2012 10:49]
		// wurde entfernt!
		// Bg Girl2: Auftritt: Kueniglberg [01.04.2012 10:49 - 01.04.2012 10:49]
		// wurde entfernt!

		int entfernt = band.getTermine().remove();
		System.out.println("Anzahl entfernter Termine: " + entfernt);
		System.out.println();

		for (Mitglied mitglied : band.getMitglieder()) {
			Queue<String> nachrichten = mitglied.getNachrichten();
			while (!nachrichten.isEmpty()) {
				System.out.println(mitglied + ": " + nachrichten.poll());
			}
		}
		System.out.println();

		// Testfall: geloeschten Termin wiederherstellen
		//
		// Erwartete Ausgabe:
		// Wiederhergestellte Termine: [Auftritt: Kueniglberg [01.04.2012 10:56
		// - 01.04.2012 10:56]]

		Termine termine = band.getTermine(new Termin.TypSelektor(
				Termin.Typ.Auftritt));
		termine.restore();

		for (Mitglied m : teilnehmer) {
			Terminvorschlag tv = m.getTerminvorschlaege().poll();
			if (tv != null) {
				tv.accept(m);
			}
		}
		System.out.println("Wiederhergestellte Termine: " + termine);

		// Testfall: 5 Proben erstellen
		//
		// Erwartete Ausgabe:
		// Termin erstellen erfolgreich = true
		// Termin erstellen erfolgreich = true
		// Termin erstellen erfolgreich = true
		// Termin erstellen erfolgreich = true
		// Termin erstellen erfolgreich = true

		ort = band.getOrte(new Ort.BezeichnungSelektor("Kueniglberg"))
				.getFirst();
		teilnehmer = band.getMitglieder();

		von = toDate(2012, 6, 15);
		bis = toDate(2012, 6, 16);
		termin = new Termin(Termin.Typ.Probe, ort, von, bis, 2600.99, .0,
				teilnehmer.asList());
		System.out.println("Termin erstellen erfolgreich = "
				+ band.sendeTerminvorschlag(termin));

		von = toDate(2012, 6, 6);
		bis = toDate(2012, 6, 7);
		termin = new Termin(Termin.Typ.Probe, band.getOrte().getFirst(), von,
				bis, 2600.99, .0, teilnehmer.asList());
		System.out.println("Termin erstellen erfolgreich = "
				+ band.sendeTerminvorschlag(termin));

		von = toDate(2012, 6, 7);
		bis = toDate(2012, 6, 8);
		termin = new Termin(Termin.Typ.Probe, ort, von, bis, 2700.99, .0,
				teilnehmer.asList());
		System.out.println("Termin erstellen erfolgreich = "
				+ band.sendeTerminvorschlag(termin));

		von = toDate(2012, 6, 8);
		bis = toDate(2012, 6, 9);
		termin = new Termin(Termin.Typ.Probe, band.getOrte().getFirst(), von,
				bis, 2800.99, .0, teilnehmer.asList());
		System.out.println("Termin erstellen erfolgreich = "
				+ band.sendeTerminvorschlag(termin));

		von = toDate(2012, 6, 9);
		bis = toDate(2012, 6, 10);
		termin = new Termin(Termin.Typ.Probe, ort, von, bis, 2900.99, .0,
				teilnehmer.asList());
		System.out.println("Termin erstellen erfolgreich = "
				+ band.sendeTerminvorschlag(termin));
		System.out.println();

		// Testfall: 4 Proben aktzeptieren, eine Probe wird abgelehnt
		//
		// Erwartete Ausgabe:
		// Bg Girl1:
		// "Sido: Ne, nicht mit mir!!! - Probe: Kueniglberg [15.06.2012 11:16 - 16.06.2012 11:16]"
		// Bg Girl2:
		// "Sido: Ne, nicht mit mir!!! - Probe: Kueniglberg [15.06.2012 11:16 - 16.06.2012 11:16]"
		// Bg Girl3:
		// "Sido: Ne, nicht mit mir!!! - Probe: Kueniglberg [15.06.2012 11:16 - 16.06.2012 11:16]"
		// Bg Girl4:
		// "Sido: Ne, nicht mit mir!!! - Probe: Kueniglberg [15.06.2012 11:16 - 16.06.2012 11:16]"
		// Heinzl:
		// "Sido: Ne, nicht mit mir!!! - Probe: Kueniglberg [15.06.2012 11:16 - 16.06.2012 11:16]"
		// Sidos Double:
		// "Sido: Ne, nicht mit mir!!! - Probe: Kueniglberg [15.06.2012 11:16 - 16.06.2012 11:16]"

		Mitglied m = band.getMitglieder(new Mitglied.NameSelektor("Sido"))
				.getFirst();
		m.getTerminvorschlaege().poll().decline(m, "Ne, nicht mit mir!!!");

		for (Mitglied mitglied : band.getMitglieder()) {
			Queue<Terminvorschlag> vorschlaege = mitglied
					.getTerminvorschlaege();
			Queue<String> nachrichten = mitglied.getNachrichten();

			while (!vorschlaege.isEmpty()) {
				vorschlaege.poll().accept(mitglied);
			}
			while (!nachrichten.isEmpty()) {
				System.out.println(mitglied + ": \"" + nachrichten.poll()
						+ "\"");
			}
		}
		System.out.println();

		// Testfall: Auftritt anlegen an dem alle Mitglieder teilnehmen
		// Da alle Ersatzmitglieder mehr als 4 Proben im letzten Jahr absolviert
		// haben, funktioniert das Erstellen.
		//
		// Erwartete Ausgabe:
		// Auftritt erstellen erfolgreich = true

		von = toDate(2012, 7, 20);
		bis = toDate(2012, 7, 20);
		teilnehmer = band.getMitglieder();
		termin = new Termin(Termin.Typ.Auftritt, ort, von, bis, 17999.98,
				57025.11, teilnehmer.asList());

		erfolgreich = band.sendeTerminvorschlag(termin);
		System.out.println("Auftritt erstellen erfolgreich = " + erfolgreich);
		System.out.println();

		for (Mitglied mitglieder : band.getMitglieder()) {
			Terminvorschlag tv = mitglieder.getTerminvorschlaege().poll();
			while (tv != null) {
				tv.accept(mitglieder);
				tv = mitglieder.getTerminvorschlaege().poll();
			}
		}

		// Testfall: Termine anzeigen (mit verschiedenen Abfragen)
		//
		// Erwartete Ausgabe:
		// Alle Termine:
		// [Auftritt: Kueniglberg [01.04.2012 11:28 - 01.04.2012 11:28],
		// Probe: Kueniglberg [06.06.2012 11:28 - 07.06.2012 11:28],
		// Probe: Kueniglberg [07.06.2012 11:28 - 08.06.2012 11:28],
		// Probe: Kueniglberg [08.06.2012 11:28 - 09.06.2012 11:28],
		// Probe: Kueniglberg [09.06.2012 11:28 - 10.06.2012 11:28],
		// Auftritt: Kueniglberg [20.07.2012 11:28 - 20.07.2012 11:28]]
		//
		// Alle Termine ab 8.6.2012:
		// [Probe: Kueniglberg [08.06.2012 11:28 - 09.06.2012 11:28],
		// Probe: Kueniglberg [09.06.2012 11:28 - 10.06.2012 11:28],
		// Auftritt: Kueniglberg [20.07.2012 11:28 - 20.07.2012 11:28]]
		//
		// Alle Proben:
		// [Probe: Kueniglberg [06.06.2012 11:28 - 07.06.2012 11:28],
		// Probe: Kueniglberg [07.06.2012 11:28 - 08.06.2012 11:28],
		// Probe: Kueniglberg [08.06.2012 11:28 - 09.06.2012 11:28],
		// Probe: Kueniglberg [09.06.2012 11:28 - 10.06.2012 11:28]]
		//
		// Alle Proben ab 8.6.2012:
		// [Probe: Kueniglberg [08.06.2012 11:28 - 09.06.2012 11:28],
		// Probe: Kueniglberg [09.06.2012 11:28 - 10.06.2012 11:28]]
		//
		// Alle Auftritte:
		// [Auftritt: Kueniglberg [01.04.2012 11:28 - 01.04.2012 11:28],
		// Auftritt: Kueniglberg [20.07.2012 11:28 - 20.07.2012 11:28]]
		//
		// Alle Auftritte ab 8.6.2012:
		// [Auftritt: Kueniglberg [20.07.2012 11:28 - 20.07.2012 11:28]]

		zeitraum = new Zeitraum(toDate(2012, 6, 8));
		List<Selector<Termin>> selektoren = new ArrayList<Selector<Termin>>();
		selektoren.add(new Termin.ZeitraumSelektor(zeitraum));

		System.out.println("Alle Termine: " + band.getTermine());
		System.out.println();
		
		System.out.println("Alle Termine ab 8.6.2012: "
				+ band.getTermine(selektoren));
		System.out.println();

		selektoren = new ArrayList<Selector<Termin>>();
		selektoren.add(new Termin.TypSelektor(Termin.Typ.Probe));
		System.out.println("Alle Proben: " + band.getTermine(selektoren));
		System.out.println();
		
		selektoren.add(new Termin.ZeitraumSelektor(zeitraum));
		System.out.println("Alle Proben ab 8.6.2012: "
				+ band.getTermine(selektoren));
		System.out.println();

		selektoren = new ArrayList<Selector<Termin>>();
		selektoren.add(new Termin.TypSelektor(Termin.Typ.Auftritt));
		System.out.println("Alle Auftritte: " + band.getTermine(selektoren));
		System.out.println();
		
		selektoren.add(new Termin.ZeitraumSelektor(zeitraum));
		System.out.println("Alle Auftritte ab 8.6.2012: "
				+ band.getTermine(selektoren));
		System.out.println();

		// Testen von varianten
		ArrayList<Variante> stdVar = new ArrayList<Variante>();
		stdVar.add(new Variante("Radio", 6000));
		stdVar.add(new Variante("Maxi", 4500));
		stdVar.add(new Variante("Single", 3600));
		ArrayList<Variante> hzlVar = new ArrayList<Variante>();
		hzlVar.add(new Variante("Heinzl-Edition", 6000));
		hzlVar.add(new Variante("ORF-Version", 4500));
		hzlVar.add(new Variante("Unplugged", 3600));
		band.getRepertoire().add(
				new Song("Ghetto", new Zeitraum(toDate(1990, 1, 1), toDate(
						2000, 1, 1)), stdVar));
		band.getRepertoire().add(
				new Song("Strassenjunge", new Zeitraum(toDate(1998, 1, 10),
						toDate(2002, 1, 1)), stdVar));
		band.getRepertoire().add(
				new Song("Augen auf", new Zeitraum(toDate(2012, 10, 1), toDate(
						2012, 12, 31)), hzlVar));
		band.getRepertoire().add(
				new Song("Endstation", new Zeitraum(toDate(2012, 10, 1),
						toDate(2012, 12, 31)), hzlVar));
		band.getRepertoire().add(
				new Song("Auf die Fresse", new Zeitraum(toDate(2012, 10, 20),
						toDate(2012, 12, 31)), stdVar));
		System.out.println("Das gesamte Repertoire ist: "
				+ band.getRepertoire().toString()
				+ "\nMit folgende Varianten: "
				+ band.getRepertoire().getSongVarianten().toString());
		ArrayList<Selector<Song>> songs12 = new ArrayList<Selector<Song>>();
		songs12.add(new Song.ZeitpunktSelektor(toDate(2012, 12, 5)));
		System.out.println("Das Repertoire 2012 ist: "
				+ band.getRepertoire(songs12).toString()
				+ "\nMit folgende Varianten: "
				+ band.getRepertoire().getSongVarianten().toString());
		ArrayList<Selector<Variante>> varis = new ArrayList<Selector<Variante>>();
		varis.add(new Variante.BezeichnungSelektor("Heinzl-Edition"));
		List<SongVariante> lil = band.getRepertoire().getSongVarianten(varis);
		System.out.println("Das Repertoire 2012 ist: "
				+ band.getRepertoire(songs12).toString()
				+ "\nMit folgenden Heinzl Varianten: " + lil.toString());
		// prog.quit();
	}
}
