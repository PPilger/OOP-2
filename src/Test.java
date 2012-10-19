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
		testeTermine();
		//testeRepertoire();

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

	}

	private static void testeBand() {
		/*
		 * Band band = new Band("Green Day", "Rock"); System.out.println(band);
		 * System.out.println();
		 */
	}

	private static void testeTermine() {
		Termin termin;
		Zeitraum zeitraum;
		Date von;
		Date bis;
		Band band = new Band("Green Day", "Rock", 10);
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

		System.out.println(mitglieder);

		// Auftritte
		von = toDate(2012, 9, 1, 18, 0);
		bis = toDate(2012, 9, 2, 1, 0);
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Konstanz", null),
				von, bis, 0, 100000, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2012, 8, 30, 16, 0);
		bis = toDate(2012, 8, 30, 23, 0);
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Berlin", null), von,
				bis, 0, 250000, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2010, 7, 1, 17, 0);
		bis = toDate(2010, 7, 1, 22, 0);
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Mainz", null), von,
				bis, 0, 80000, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		// Proben
		von = toDate(2012, 7, 1, 8, 0);
		bis = toDate(2012, 7, 1, 15, 0);
		termin = new Termin(Termin.Typ.Probe, new Ort("Studio", null), von,
				bis, 10000, 0, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2012, 7, 20, 10, 0);
		bis = toDate(2012, 7, 20, 20, 0);
		termin = new Termin(Termin.Typ.Probe, new Ort("zu Hause", null), von,
				bis, 10, 0, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		von = toDate(2010, 9, 1, 9, 0);
		bis = toDate(2010, 9, 1, 18, 0);
		termin = new Termin(Termin.Typ.Probe, new Ort("Stadthalle", null), von,
				bis, 20000, 0, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		mitglied = mitglieder.get(0);
		System.out.println();
		System.out.println("Posteingang:");
		System.out.println(mitglied.getNachrichten());
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
		System.out.println(mitglied.getNachrichten());
		System.out.println(mitglied.getTerminvorschlaege());
		while (!mitglied.getTerminvorschlaege().isEmpty()) {
			mitglied.getTerminvorschlaege().poll();
		}

		zeitraum = new Zeitraum(toDate(2012, 7, 2), toDate(2012, 9, 1));

		System.out.println();
		System.out.println("Termine: ");
		System.out.println(band.getTermine());
		System.out.println("Gewinn: " + band.getTermine().getGewinn());
		System.out.println("Kosten: " + band.getTermine().getKosten());
		System.out.println();

		List<Selector<Termin>> sel = new ArrayList<Selector<Termin>>();
		sel.add(new Termin.ZeitraumSelektor(zeitraum));
		Termine selected = band.getTermine(sel);

		System.out.println("Termine " + zeitraum + ": ");
		System.out.println(selected);
		System.out.println("Gewinn: " + selected.getGewinn());
		System.out.println("Kosten: " + selected.getKosten());
		System.out.println();

		selected.setKosten(15000);
		band.getTermine().setUmsatz(20100);

		System.out.println("Termine " + zeitraum + " geaendert: ");
		System.out.println(selected);
		System.out.println("Gewinn: " + selected.getGewinn());
		System.out.println("Kosten: " + selected.getKosten());
		System.out.println();

		selected.undo();
		selected.undo();

		System.out.println("Termine " + zeitraum + " undo: ");
		System.out.println(selected);
		System.out.println("Gewinn: " + selected.getGewinn());
		System.out.println("Kosten: " + selected.getKosten());

		System.out.println();
		System.out.println("Posteingang:");
		System.out.println(mitglied.getNachrichten());
		System.out.println(mitglied.getTerminvorschlaege());

	}

	private static void testeRepertoire() {

		Song song;
		Zeitraum zeitraum;
		List<Variante> varianten;
		List<Selector<Song>> songSelector;
		List<Selector<Variante>> variantenSelector;
		Band band = new Band("Green Day", "Rock", 10);

		zeitraum = new Zeitraum(toDate(2005, 3, 6));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Normal", 200));
		varianten.add(new Variante("Acoustic", 210));
		song = new Song("Holiday", zeitraum, varianten);
		band.getRepertoire().add(song);
		System.out.println("+ " + song.toDetailString());

		zeitraum = new Zeitraum(toDate(1994, 2, 3), toDate(2004, 5, 3));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Normal", 195));
		song = new Song("Basketcase", zeitraum, varianten);
		band.getRepertoire().add(song);
		System.out.println("+ " + song.toDetailString());

		zeitraum = new Zeitraum(toDate(2004, 4, 4));
		varianten = new ArrayList<Variante>();
		varianten.add(new Variante("Normal", 195));
		varianten.add(new Variante("Acoustic", 200));
		song = new Song("American Idiot", zeitraum, varianten);
		band.getRepertoire().add(song);
		System.out.println("+ " + song.toDetailString());

		System.out.println();
		System.out.println("Repertoire: ");
		variantenSelector = new ArrayList<Selector<Variante>>();
		variantenSelector.add(new Variante.BezeichnungSelektor("Acoustic"));

		System.out.println(band.getRepertoire());
		System.out.println(band.getRepertoire().getSongVarianten());
		System.out.println(band.getRepertoire().getSongVarianten(
				variantenSelector));

		System.out.println();
		System.out.println("Repertoire am 1.1.2000: ");
		songSelector = new ArrayList<Selector<Song>>();
		songSelector.add(new Song.ZeitpunktSelektor(toDate(2000, 1, 1)));

		System.out.println(band.getRepertoire(songSelector));
		System.out.println(band.getRepertoire(songSelector).getSongVarianten());
		System.out.println(band.getRepertoire(songSelector).getSongVarianten(
				variantenSelector));

		System.out.println();
		System.out.println("Repertoire am 1.1.2012: ");
		songSelector = new ArrayList<Selector<Song>>();
		songSelector.add(new Song.ZeitpunktSelektor(toDate(2012, 1, 1)));

		System.out.println(band.getRepertoire(songSelector));
		System.out.println(band.getRepertoire(songSelector).getSongVarianten());
		System.out.println(band.getRepertoire(songSelector).getSongVarianten(
				variantenSelector));

		System.out.println();
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
}
