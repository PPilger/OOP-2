import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Test {
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
		Programm prog = new Programm();
		
		Band band = new Band("Green Day", "Rock", 10);

		// prog.addBand(new Band("Green Day", "Rock"));
		// Band band = prog.getBand("Green Day");
		// System.out.println(band);
		// System.out.println(band.getMitglieder());
		//
		// Zeitraum zeitraum = new Zeitraum(toDate(1989, 3, 4));
		// Mitglied mitglied = new Mitglied("Billie Joe Armstrong", "123/45678",
		// "Gitarre", zeitraum);
		// band.getMitglieder().add(mitglied);
		// System.out.println("+ " + mitglied.toDetailString());
		//
		// zeitraum = new Zeitraum(toDate(1989, 3, 4));
		// mitglied = new Mitglied("Mike Dirnt", "321/12323", "Bass", zeitraum);
		// band.getMitglieder().add(mitglied);
		// System.out.println("+ " + mitglied.toDetailString());
		//
		// zeitraum = new Zeitraum(toDate(1989, 3, 4), toDate(1990, 1, 1));
		// mitglied = new Mitglied("Al Sobrante", "345/54327", "Schlagzeug",
		// zeitraum);
		// band.getMitglieder().add(mitglied);
		// System.out.println("+ " + mitglied.toDetailString());
		//
		// zeitraum = new Zeitraum(toDate(1990, 1, 1));
		// mitglied = new Mitglied("Tre Cool", "943/38321", "Schlagzeug",
		// zeitraum);
		// band.getMitglieder().add(mitglied);
		// System.out.println("+ " + mitglied.toDetailString());
		//
		// prog.quit();
		//testeTermine();

		/*
		 * System.out.println("Teste Band"); System.out.println(); testeBand();
		 * System.out.println(); System.out.println();
		 * System.out.println("Teste Mitglieder"); System.out.println();
		 * testeMitglieder(); System.out.println(); System.out.println();
		 * System.out.println("Teste Repertoire"); System.out.println();
		 * testeRepertoire(); System.out.println(); System.out.println();
		 * System.out.println("Teste Termine"); System.out.println();
		 * testeTermine();
		 */
		testeTermine(band);
		
		testeRepertoire(band);
		
		testeOrte(band);

	}

	private static void testeBand() {
		/*
		 * Band band = new Band("Green Day", "Rock"); System.out.println(band);
		 * System.out.println();
		 */
	}

	private static void testeTermine(Band band) {
		Termin termin;
		Zeitraum zeitraum;
		Date von;
		Date bis;
		
		List<Mitglied> mitglieder = new ArrayList<Mitglied>();

		zeitraum = new Zeitraum(toDate(1989, 3, 4));
		Mitglied mitglied = new Mitglied("Billie Joe Armstrong", "123/45678",
				"Gitarre", zeitraum, false);
		mitglieder.add(mitglied);
		zeitraum = new Zeitraum(toDate(1989, 3, 4));
		mitglied = new Mitglied("Mike Dirnt", "321/12323", "Bass", zeitraum,
				false);
		mitglieder.add(mitglied);
		zeitraum = new Zeitraum(toDate(1989, 3, 4), toDate(1990, 1, 1));
		mitglied = new Mitglied("Al Sobrante", "345/54327", "Schlagzeug",
				zeitraum, false);
		mitglieder.add(mitglied);
		zeitraum = new Zeitraum(toDate(1990, 1, 1));
		mitglied = new Mitglied("Tre Cool", "943/38321", "Schlagzeug",
				zeitraum, false);
		mitglieder.add(mitglied);

		/*
		 * Ausgabe der angelegten Mitglieder
		 * [Billie Joe Armstrong, Mike Dirnt, Al Sobrante, Tre Cool]
		 */
		System.out.println(mitglieder);

		// Auftritte
		von = toDate(2012, 9, 1, 18, 0);
		bis = toDate(2012, 9, 2, 1, 0);
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Konstanz", null),
				von, bis, 0, 100000, mitglieder);
		band.sendeTerminvorschlag(termin);
		//+ Auftritt: Konstanz [01.09.2012 06:00 - 02.09.2012 01:00], Kosten: 0,00, Umsatz: 100.000,00
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2012, 8, 30, 16, 0);
		bis = toDate(2012, 8, 30, 23, 0);
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Berlin", null), von,
				bis, 0, 250000, mitglieder);
		band.sendeTerminvorschlag(termin);
		//+ Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00], Kosten: 0,00, Umsatz: 250.000,00
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2010, 7, 1, 17, 0);
		bis = toDate(2010, 7, 1, 22, 0);
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Mainz", null), von,
				bis, 0, 80000, mitglieder);
		band.sendeTerminvorschlag(termin);
		//+ Auftritt: Mainz [01.07.2010 05:00 - 01.07.2010 10:00], Kosten: 0,00, Umsatz: 80.000,00
		System.out.println("+ " + termin.toDetailString());

		// Proben
		von = toDate(2012, 7, 1, 8, 0);
		bis = toDate(2012, 7, 1, 15, 0);
		termin = new Termin(Termin.Typ.Probe, new Ort("Studio", null), von,
				bis, 10000, 0, mitglieder);
		band.sendeTerminvorschlag(termin);
		//+ Probe: Studio [01.07.2012 08:00 - 01.07.2012 03:00], Kosten: 10.000,00, Umsatz: 0,00
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2012, 7, 20, 10, 0);
		bis = toDate(2012, 7, 20, 20, 0);
		termin = new Termin(Termin.Typ.Probe, new Ort("zu Hause", null), von,
				bis, 10, 0, mitglieder);
		band.sendeTerminvorschlag(termin);
		//+ Probe: zu Hause [20.07.2012 10:00 - 20.07.2012 08:00], Kosten: 10,00, Umsatz: 0,00
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2010, 9, 1, 9, 0);
		bis = toDate(2010, 9, 1, 18, 0);
		termin = new Termin(Termin.Typ.Probe, new Ort("Stadthalle", null), von,
				bis, 20000, 0, mitglieder);
		band.sendeTerminvorschlag(termin);
		//+ Probe: Stadthalle [01.09.2010 09:00 - 01.09.2010 06:00], Kosten: 20.000,00, Umsatz: 0,00
		System.out.println("+ " + termin.toDetailString());

		mitglied = mitglieder.get(0);
		System.out.println();
		System.out.println("Posteingang:");
		/*
		 * Posteingang:
		 * []
		 */
		System.out.println(mitglied.getNachrichten());
		//[Auftritt: Konstanz [01.09.2012 06:00 - 02.09.2012 01:00], Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00], Auftritt: Mainz [01.07.2010 05:00 - 01.07.2010 10:00], Probe: Studio [01.07.2012 08:00 - 01.07.2012 03:00], Probe: zu Hause [20.07.2012 10:00 - 20.07.2012 08:00], Probe: Stadthalle [01.09.2010 09:00 - 01.09.2010 06:00]]
		System.out.println(mitglied.getTerminvorschlaege());

		for (Mitglied m : mitglieder) {
			Random r = new Random();
			Terminvorschlag t;
			while ((t = m.getTerminvorschlaege().poll()) != null) {
				if (r.nextDouble() < 0.05) {
					t.decline(m, "hab keine Zeit!!!");
				} else {
					t.accept(m);
				}
			}
		}

		System.out.println();
		System.out.println("Posteingang:");
		/*
		 * Posteingang:
		 * [Tre Cool: hab keine Zeit!!! - Probe: zu Hause [20.07.2012 10:00 - 20.07.2012 08:00]]
		 */
		System.out.println(mitglied.getNachrichten());
		//[]
		System.out.println(mitglied.getTerminvorschlaege());
		while (!mitglied.getTerminvorschlaege().isEmpty()) {
			mitglied.getTerminvorschlaege().poll();
		}

		zeitraum = new Zeitraum(toDate(2012, 7, 2), toDate(2012, 9, 1));

		System.out.println();
		System.out.println("Termine: ");
		//[Auftritt: Konstanz [01.09.2012 06:00 - 02.09.2012 01:00], Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00], Auftritt: Mainz [01.07.2010 05:00 - 01.07.2010 10:00], Probe: Studio [01.07.2012 08:00 - 01.07.2012 03:00], Probe: Stadthalle [01.09.2010 09:00 - 01.09.2010 06:00]]
		System.out.println(band.getTermine());
		//Gewinn: 400000.0
		System.out.println("Gewinn: " + band.getTermine().getGewinn());
		//Kosten: 30000.0
		System.out.println("Kosten: " + band.getTermine().getKosten());
		System.out.println();

		List<Selector<Termin>> sel = new ArrayList<Selector<Termin>>();
		sel.add(new Termin.ZeitraumSelektor(zeitraum));
		Termine selected = band.getTermine(sel);

		//Termine [02.07.2012 - 01.09.2012]:
		System.out.println("Termine " + zeitraum + ": ");
		//[Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00]]
		System.out.println(selected);
		//Gewinn: 250000.0
		System.out.println("Gewinn: " + selected.getGewinn());
		//Kosten: 0.0
		System.out.println("Kosten: " + selected.getKosten());
		System.out.println();

		for (Termin t : selected) {
			t.setAusgaben(15000);
			t.setEinnahmen(20100);
		}

		//Termine [02.07.2012 - 01.09.2012] geaendert: 
		System.out.println("Termine " + zeitraum + " geaendert: ");
		//[Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00]]
		System.out.println(selected);
		//Gewinn: 5100.0
		System.out.println("Gewinn: " + selected.getGewinn());
		//Kosten: 15000.0
		System.out.println("Kosten: " + selected.getKosten());
		System.out.println();

		for (Termin t : selected) {
			t.undo();
			t.undo();
		}

		//Termine [02.07.2012 - 01.09.2012] undo: 
		System.out.println("Termine " + zeitraum + " undo: ");
		//[Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00]]
		System.out.println(selected);
		//Gewinn: 250000.0
		System.out.println("Gewinn: " + selected.getGewinn());
		//Kosten: 0.0
		System.out.println("Kosten: " + selected.getKosten());

		System.out.println();
		System.out.println("Posteingang:");
		//[Tre Cool: hab keine Zeit!!! - Probe: zu Hause [20.07.2012 10:00 - 20.07.2012 08:00], Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00] wurde geaendert: Kosten: 0.0 -> 15000.0, Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00] wurde geaendert: Umsatz: 250000.0 -> 20100.0, Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00] wurde geaendert: zurueckgesetzt auf vorige Version, Auftritt: Berlin [30.08.2012 04:00 - 30.08.2012 11:00] wurde geaendert: zurueckgesetzt auf vorige Version]
		System.out.println(mitglied.getNachrichten());
		//[]
		System.out.println(mitglied.getTerminvorschlaege());

	}

	private static void testeRepertoire(Band band) {

		Song song;
		Zeitraum zeitraum;
		List<Variante> varianten;
		List<Selector<Song>> songSelector;
		List<Selector<Variante>> variantenSelector;

		zeitraum = new Zeitraum(toDate(2005, 3, 6));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Normal", 200));
		varianten.add(new Variante("Acoustic", 210));
		song = new Song("Holiday", zeitraum, varianten);
		band.getRepertoire().add(song);
		//+ Holiday [06.03.2005 - ]
		System.out.println("+ " + song.toDetailString());

		zeitraum = new Zeitraum(toDate(1994, 2, 3), toDate(2004, 5, 3));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Normal", 195));
		song = new Song("Basketcase", zeitraum, varianten);
		band.getRepertoire().add(song);
		//+ Basketcase [03.02.1994 - 03.05.2004]
		System.out.println("+ " + song.toDetailString());

		zeitraum = new Zeitraum(toDate(2004, 4, 4));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Normal", 195));
		varianten.add(new Variante("Acoustic", 200));
		song = new Song("American Idiot", zeitraum, varianten);
		band.getRepertoire().add(song);
		//+ American Idiot [04.04.2004 - ]
		System.out.println("+ " + song.toDetailString());

		System.out.println();
		System.out.println("Repertoire: ");
		variantenSelector = new ArrayList<Selector<Variante>>();
		variantenSelector.add(new Variante.BezeichnungSelektor("Acoustic"));

		//[Holiday, Basketcase, American Idiot]
		System.out.println(band.getRepertoire());
		//[Holiday (Normal, 3:20), Holiday (Acoustic, 3:30), Basketcase (Normal, 3:15), American Idiot (Normal, 3:15), American Idiot (Acoustic, 3:20)]
		System.out.println(band.getRepertoire().getSongVarianten());
		//[Holiday (Acoustic, 3:30), American Idiot (Acoustic, 3:20)]
		System.out.println(band.getRepertoire().getSongVarianten(
				variantenSelector));

		System.out.println();
		System.out.println("Repertoire am 1.1.2000: ");
		songSelector = new ArrayList<Selector<Song>>();
		songSelector.add(new Song.ZeitpunktSelektor(toDate(2000, 1, 1)));

		//[Basketcase]
		System.out.println(band.getRepertoire(songSelector));
		//[Basketcase (Normal, 3:15)]
		System.out.println(band.getRepertoire(songSelector).getSongVarianten());
		//[]
		System.out.println(band.getRepertoire(songSelector).getSongVarianten(
				variantenSelector));

		System.out.println();
		System.out.println("Repertoire am 1.1.2012: ");
		songSelector = new ArrayList<Selector<Song>>();
		songSelector.add(new Song.ZeitpunktSelektor(toDate(2012, 1, 1)));

		//[Holiday, American Idiot]
		System.out.println(band.getRepertoire(songSelector));
		//[Holiday (Normal, 3:20), Holiday (Acoustic, 3:30), American Idiot (Normal, 3:15), American Idiot (Acoustic, 3:20)]
		System.out.println(band.getRepertoire(songSelector).getSongVarianten());
		//[Holiday (Acoustic, 3:30), American Idiot (Acoustic, 3:20)]
		System.out.println(band.getRepertoire(songSelector).getSongVarianten(
				variantenSelector));

		System.out.println();
		

		System.out.println();
		System.out.println("Repertoire: ");
		//[Holiday, Basketcase, American Idiot]
		System.out.println(band.getRepertoire());

		band.getRepertoire(songSelector).remove();

		System.out.println();
		System.out.println("Repertoire nach loeschen: ");
		//[Basketcase]
		System.out.println(band.getRepertoire());

		band.getRepertoire().restore();
		
		System.out.println();
		System.out.println("Repertoire nach wiederherstellen: ");
		//[Basketcase, Holiday, American Idiot]
		System.out.println(band.getRepertoire());
	}

	private static void testeMitglieder() {
		
		
		
		
		/*
		 * Mitglied mitglied; Zeitraum zeitraum; Band band = new
		 * Band("Green Day", "Rock");
		 * 
		 * zeitraum = new ZeitAb(toDate(1989, 3, 4)); mitglied = new
		 * Mitglied("Billie Joe Armstrong", "123/45678", "Gitarre", zeitraum);
		 * band.getMitglieder().add(mitglied); System.out.println("+ " +
		 * mitglied.toDetailString());
		 * 
		 * zeitraum = new ZeitAb(toDate(1989, 3, 4)); mitglied = new
		 * Mitglied("Mike Dirnt", "321/12323", "Bass", zeitraum);
		 * band.getMitglieder().add(mitglied); System.out.println("+ " +
		 * mitglied.toDetailString());
		 * 
		 * zeitraum = new ZeitIntervall(toDate(1989, 3, 4), toDate(1990, 1, 1));
		 * mitglied = new Mitglied("Al Sobrante", "345/54327", "Schlagzeug",
		 * zeitraum); band.getMitglieder().add(mitglied);
		 * System.out.println("+ " + mitglied.toDetailString());
		 * 
		 * zeitraum = new ZeitAb(toDate(1990, 1, 1)); mitglied = new
		 * Mitglied("Tre Cool", "943/38321", "Schlagzeug", zeitraum);
		 * band.getMitglieder().add(mitglied); System.out.println("+ " +
		 * mitglied.toDetailString());
		 * 
		 * System.out.println(); System.out.println("Mitglieder: ");
		 * System.out.println(band.getMitglieder()); System.out.println();
		 * System.out.println("Mitglieder am 31.12.1989: ");
		 * System.out.println(band.getMitglieder(toDate(1989, 12, 31)));
		 * System.out.println(); System.out.println("Mitglieder am 2.1.1990: ");
		 * System.out.println(band.getMitglieder(toDate(1990, 1, 2)));
		 * System.out.println();
		 * 
		 * mitglied = band.getMitglieder().get(1);
		 * band.getMitglieder().remove(mitglied); System.out.println("- " +
		 * mitglied.toDetailString());
		 * 
		 * System.out.println(); System.out.println("Mitglieder: ");
		 * System.out.println(band.getMitglieder()); System.out.println();
		 */
	}
	
	private static void testeOrte(Band band) {

		ArrayList<String> inf1 = new ArrayList<String>();
		inf1.add("GŠnserndorf");
		inf1.add("Mistelbach");
		inf1.add("Korneurburg");
		Ort o1 = new Ort("Niederšsterreich", inf1);
		
		//Bezeichnung: Niederšsterreich [GŠnserndorf, Mistelbach, Korneurburg]
		System.out.println(o1.toDetailString());
		
		ArrayList<String> inf2 = new ArrayList<String>();
		inf2.add("Wien Meidling");
		inf2.add("Wieden");
		inf2.add("FŸnfhaus");
		Ort o2 = new Ort("Wien", inf2);
		
		//Bezeichnung: Wien [Wien Meidling, Wieden, FŸnfhaus]
		System.out.println(o2.toDetailString());
		
	}
}
