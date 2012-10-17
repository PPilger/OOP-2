import java.util.Calendar;
import java.util.Date;

public class Test {
	private static Date toDate(int jahr, int monat, int tag) {
		Calendar kalender = Calendar.getInstance();
		kalender.set(jahr, monat - 1, tag);
		return kalender.getTime();
	}

	private static Date toDate(int jahr, int monat, int tag, int stunde,
			int minute) {
		Calendar kalender = Calendar.getInstance();
		kalender.set(jahr, monat - 1, tag, stunde, minute);
		return kalender.getTime();
	}

	public static void main(String[] args) {
		System.out.println("Teste Band");
		System.out.println();
		testeBand();
		System.out.println();
		System.out.println();
		System.out.println("Teste Mitglieder");
		System.out.println();
		testeMitglieder();
		System.out.println();
		System.out.println();
		System.out.println("Teste Repertoire");
		System.out.println();
		testeRepertoire();
		System.out.println();
		System.out.println();
		System.out.println("Teste Termine");
		System.out.println();
		testeTermine();

	}

	private static void testeBand() {
		Band band = new Band("Green Day", "Rock");
		System.out.println(band);
		System.out.println();
	}

	private static void testeTermine() {
		Termin termin;
		ZeitIntervall zeitIntervall;
		Band band = new Band("Green Day", "Rock");

		// Auftritte
		zeitIntervall = new ZeitIntervall(toDate(2012, 9, 1, 18, 0), toDate(
				2012, 9, 2, 1, 0));
		termin = new Auftritt("Konstanz", zeitIntervall, 100000);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitIntervall = new ZeitIntervall(toDate(2012, 8, 30, 16, 0), toDate(
				2012, 8, 30, 23, 0));
		termin = new Auftritt("Berlin", zeitIntervall, 250000);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitIntervall = new ZeitIntervall(toDate(2010, 7, 1, 17, 0), toDate(
				2010, 7, 1, 22, 0));
		termin = new Auftritt("Mainz", zeitIntervall, 80000);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		// Termine
		zeitIntervall = new ZeitIntervall(toDate(2012, 7, 1, 8, 0), toDate(
				2012, 7, 1, 15, 0));
		termin = new Probe("Studio", zeitIntervall, 10000);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitIntervall = new ZeitIntervall(toDate(2012, 7, 20, 10, 0), toDate(
				2012, 7, 20, 20, 0));
		termin = new Probe("zu Hause", zeitIntervall, 10);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		zeitIntervall = new ZeitIntervall(toDate(2010, 9, 1, 9, 0), toDate(
				2010, 9, 1, 18, 0));
		termin = new Probe("Stadthalle", zeitIntervall, 20000);
		band.getTermine().add(termin);
		System.out.println("+ " + termin.toDetailString());

		Zeitraum zeitraum = new ZeitIntervall(toDate(2012, 7, 2), toDate(2012,
				9, 1));

		System.out.println();
		System.out.println("Termine: ");
		System.out.println(band.getTermine());
		System.out.println("Gewinn: " + band.getTermine().getGewinn());
		System.out.println("Kosten: " + band.getTermine().getKosten());
		System.out.println();
		System.out.println("Termine " + zeitraum + ": ");
		System.out.println(band.getTermine(zeitraum));
		System.out.println("Gewinn: " + band.getTermine(zeitraum).getGewinn());
		System.out.println("Kosten: " + band.getTermine(zeitraum).getKosten());
		System.out.println();
		System.out.println("Proben " + zeitraum + ": ");
		System.out.println(band.getTermine(Probe.class, zeitraum));
		System.out.println("Gewinn: "
				+ band.getTermine(Probe.class, zeitraum).getGewinn());
		System.out.println("Kosten: "
				+ band.getTermine(Probe.class, zeitraum).getKosten());
		System.out.println();
		System.out.println("Auftritte " + zeitraum + ": ");
		System.out.println(band.getTermine(Auftritt.class, zeitraum));
		System.out.println("Gewinn: "
				+ band.getTermine(Auftritt.class, zeitraum).getGewinn());
		System.out.println("Kosten: "
				+ band.getTermine(Auftritt.class, zeitraum).getKosten());
		System.out.println();

		termin = band.getTermine().get(1);
		band.getTermine().remove(termin);
		System.out.println("- " + termin.toDetailString());

		termin = band.getTermine().get(3);
		band.getTermine().remove(termin);
		System.out.println("- " + termin.toDetailString());

		System.out.println();
		System.out.println("Termine: ");
		System.out.println(band.getTermine());
		System.out.println("Gewinn: " + band.getTermine().getGewinn());
		System.out.println("Kosten: " + band.getTermine().getKosten());
		System.out.println();
	}

	private static void testeRepertoire() {
		Song song;
		Zeitraum zeitraum;
		Band band = new Band("Green Day", "Rock");

		zeitraum = new ZeitAb(toDate(2005, 3, 6));
		song = new Song("Holiday", 200, zeitraum);
		band.getRepertoire().add(song);
		System.out.println("+ " + song.toDetailString());

		zeitraum = new ZeitIntervall(toDate(1994, 2, 3), toDate(2004, 5, 3));
		song = new Song("Basketcase", 195, zeitraum);
		band.getRepertoire().add(song);
		System.out.println("+ " + song.toDetailString());

		zeitraum = new ZeitAb(toDate(2004, 4, 4));
		song = new Song("American Idiot", 195, zeitraum);
		band.getRepertoire().add(song);
		System.out.println("+ " + song.toDetailString());

		System.out.println();
		System.out.println("Repertoire: ");
		System.out.println(band.getRepertoire());
		System.out.println();
		System.out.println("Repertoire am 1.1.2000: ");
		System.out.println(band.getRepertoire(toDate(2000, 1, 1)));
		System.out.println();
		System.out.println("Repertoire am 1.1.2012: ");
		System.out.println(band.getRepertoire(toDate(2012, 1, 1)));
		System.out.println();

		song = band.getRepertoire().get(1);
		band.getRepertoire().remove(song);
		System.out.println("- " + song.toDetailString());

		System.out.println();
		System.out.println("Repertoire: ");
		System.out.println(band.getRepertoire());
		System.out.println();
	}

	private static void testeMitglieder() {
		Mitglied mitglied;
		Zeitraum zeitraum;
		Band band = new Band("Green Day", "Rock");

		zeitraum = new ZeitAb(toDate(1989, 3, 4));
		mitglied = new Mitglied("Billie Joe Armstrong", "123/45678", "Gitarre",
				zeitraum);
		band.getMitglieder().add(mitglied);
		System.out.println("+ " + mitglied.toDetailString());

		zeitraum = new ZeitAb(toDate(1989, 3, 4));
		mitglied = new Mitglied("Mike Dirnt", "321/12323", "Bass", zeitraum);
		band.getMitglieder().add(mitglied);
		System.out.println("+ " + mitglied.toDetailString());

		zeitraum = new ZeitIntervall(toDate(1989, 3, 4), toDate(1990, 1, 1));
		mitglied = new Mitglied("Al Sobrante", "345/54327", "Schlagzeug",
				zeitraum);
		band.getMitglieder().add(mitglied);
		System.out.println("+ " + mitglied.toDetailString());

		zeitraum = new ZeitAb(toDate(1990, 1, 1));
		mitglied = new Mitglied("Tre Cool", "943/38321", "Schlagzeug", zeitraum);
		band.getMitglieder().add(mitglied);
		System.out.println("+ " + mitglied.toDetailString());

		System.out.println();
		System.out.println("Mitglieder: ");
		System.out.println(band.getMitglieder());
		System.out.println();
		System.out.println("Mitglieder am 31.12.1989: ");
		System.out.println(band.getMitglieder(toDate(1989, 12, 31)));
		System.out.println();
		System.out.println("Mitglieder am 2.1.1990: ");
		System.out.println(band.getMitglieder(toDate(1990, 1, 2)));
		System.out.println();

		mitglied = band.getMitglieder().get(1);
		band.getMitglieder().remove(mitglied);
		System.out.println("- " + mitglied.toDetailString());

		System.out.println();
		System.out.println("Mitglieder: ");
		System.out.println(band.getMitglieder());
		System.out.println();
	}
}
