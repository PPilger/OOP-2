import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
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
		Band band = new Band("Green Day", "Rock");
		List<Mitglied> mitglieder = new ArrayList<Mitglied>();
		
		zeitraum = new Zeitraum(toDate(1989, 3, 4));
		Mitglied mitglied = new Mitglied("Billie Joe Armstrong", "123/45678",
				"Gitarre", zeitraum);
		mitglieder.add(mitglied);
		zeitraum = new Zeitraum(toDate(1989, 3, 4));
		mitglied = new Mitglied("Mike Dirnt", "321/12323", "Bass", zeitraum);
		mitglieder.add(mitglied);
		zeitraum = new Zeitraum(toDate(1989, 3, 4), toDate(1990, 1, 1));
		mitglied = new Mitglied("Al Sobrante", "345/54327", "Schlagzeug",
				zeitraum);
		mitglieder.add(mitglied);
		zeitraum = new Zeitraum(toDate(1990, 1, 1));
		mitglied = new Mitglied("Tre Cool", "943/38321", "Schlagzeug", zeitraum);
		mitglieder.add(mitglied);
		
		System.out.println(mitglieder);

		// Auftritte
		zeitraum = new Zeitraum(toDate(2012, 9, 1, 18, 0), toDate(2012, 9, 2,
				1, 0));
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Konstanz", null),
				zeitraum, 0, 100000, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitraum = new Zeitraum(toDate(2012, 8, 30, 16, 0), toDate(2012, 8, 30,
				23, 0));
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Berlin", null),
				zeitraum, 0, 250000, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitraum = new Zeitraum(toDate(2010, 7, 1, 17, 0), toDate(2010, 7, 1,
				22, 0));
		termin = new Termin(Termin.Typ.Auftritt, new Ort("Mainz", null),
				zeitraum, 0, 80000, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		// Proben
		zeitraum = new Zeitraum(toDate(2012, 7, 1, 8, 0), toDate(2012, 7, 1,
				15, 0));
		termin = new Termin(Termin.Typ.Probe, new Ort("Studio", null),
				zeitraum, 10000, 0, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitraum = new Zeitraum(toDate(2012, 7, 20, 10, 0), toDate(2012, 7, 20,
				20, 0));
		termin = new Termin(Termin.Typ.Probe, new Ort("zu Hause", null),
				zeitraum, 10, 0, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitraum = new Zeitraum(toDate(2010, 9, 1, 9, 0), toDate(2010, 9, 1,
				18, 0));
		termin = new Termin(Termin.Typ.Probe, new Ort("Stadthalle", null),
				zeitraum, 20000, 0, mitglieder);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		mitglied = mitglieder.get(0);
		System.out.println();
		System.out.println("Posteingang:");
		System.out.println(mitglied.getNachrichten());
		System.out.println(mitglied.getTerminvorschlaege());
		
		for(Mitglied m : mitglieder) {
			Random r = new Random();
			Terminvorschlag t;
			while((t = m.getTerminvorschlaege().poll()) != null) {
				if(r.nextDouble() < 0.05) {
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
		while(!mitglied.getTerminvorschlaege().isEmpty()) {
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
		/*
		 * Song song; Zeitraum zeitraum; Band band = new Band("Green Day",
		 * "Rock");
		 * 
		 * zeitraum = new ZeitAb(toDate(2005, 3, 6)); song = new Song("Holiday",
		 * 200, zeitraum); band.getRepertoire().add(song);
		 * System.out.println("+ " + song.toDetailString());
		 * 
		 * zeitraum = new ZeitIntervall(toDate(1994, 2, 3), toDate(2004, 5, 3));
		 * song = new Song("Basketcase", 195, zeitraum);
		 * band.getRepertoire().add(song); System.out.println("+ " +
		 * song.toDetailString());
		 * 
		 * zeitraum = new ZeitAb(toDate(2004, 4, 4)); song = new
		 * Song("American Idiot", 195, zeitraum);
		 * band.getRepertoire().add(song); System.out.println("+ " +
		 * song.toDetailString());
		 * 
		 * System.out.println(); System.out.println("Repertoire: ");
		 * System.out.println(band.getRepertoire()); System.out.println();
		 * System.out.println("Repertoire am 1.1.2000: ");
		 * System.out.println(band.getRepertoire(toDate(2000, 1, 1)));
		 * System.out.println(); System.out.println("Repertoire am 1.1.2012: ");
		 * System.out.println(band.getRepertoire(toDate(2012, 1, 1)));
		 * System.out.println();
		 * 
		 * song = band.getRepertoire().get(1);
		 * band.getRepertoire().remove(song); System.out.println("- " +
		 * song.toDetailString());
		 * 
		 * System.out.println(); System.out.println("Repertoire: ");
		 * System.out.println(band.getRepertoire()); System.out.println();
		 */
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
