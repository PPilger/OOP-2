import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
		Programm prog = new Programm();

		prog.addBand(new Band("Sido", "Berlin-Rap", 4));
		Band mb = prog.getBand("Sido");
		mb.getMitglieder().add(new Mitglied("Sido","12345","Gesang",new Zeitraum(toDate(1999, 5, 10)),false));
		mb.getMitglieder().add(new Mitglied("Bg Girl1","123765","Getanze",new Zeitraum(toDate(1999, 10, 10)),false));
		mb.getMitglieder().add(new Mitglied("Bg Girl2","3412345","Getanze",new Zeitraum(toDate(1999, 10, 10)),false));
		mb.getMitglieder().add(new Mitglied("Bg Girl3","1234475","Getanze",new Zeitraum(toDate(2002, 1, 25)),true));
		mb.getMitglieder().add(new Mitglied("Bg Girl4","12342315","Getanze",new Zeitraum(toDate(2002, 1, 25)),true));
		mb.getMitglieder().add(new Mitglied("Heinzl","12345","Gesang",new Zeitraum(toDate(2012, 25, 8)),true));
		mb.getMitglieder().add(new Mitglied("Sidos Double","1234778","Gesang",new Zeitraum(toDate(2010, 9, 1)),true));
		
		ArrayList<String> aaa = new ArrayList<String>(); aaa.add("Umkleideraum");aaa.add("Presseraum");aaa.add("Proberaum");aaa.add("Bühne");aaa.add("10000Watt Sound");
		ArrayList<String> bbb = new ArrayList<String>(); bbb.add("Umkleideraum");bbb.add("Proberaum");bbb.add("Bühne");bbb.add("5000Watt Sound");
		ArrayList<String> ccc = new ArrayList<String>(); ccc.add("Umkleideraum");ccc.add("Restaurant");ccc.add("Pult mit Mikrofon");ccc.add("1000Watt Sound");
		
		
		mb.getOrte().add(new Ort("Kueniglberg", aaa));mb.getOrte().add(new Ort("KdW-Berlin", ccc));mb.getOrte().add(new Ort("Kreuzberger-Club", ccc));
		mb.getOrte().add(new Ort("Wr. Stadthalle", aaa));mb.getOrte().add(new Ort("Szene Wien", bbb));mb.getOrte().add(new Ort("Babenbergerhalle Kloburg", bbb));

		//Ergebis Küniglberg/Stadthalle/Szene/Babenberg...
		ArrayList<Selector<Ort>> mOrt = new ArrayList<Selector<Ort>>(); mOrt.add(new Ort.InfrastrukturSelektor("Presseraum"));mOrt.add(new Ort.InfrastrukturSelektor("proBeRaum"));
		System.out.println(mb.getOrte(mOrt).toString() + " ---> "+ mb.getOrte(mOrt).count());
		ArrayList<Selector<Ort>> kueb = new ArrayList<Selector<Ort>>();kueb.add(new Ort.BezeichnungSelektor("kueniglberg"));
		//Küniglberg loeschen
		System.out.println("Loesche:"+ mb.getOrte(kueb).toString() + "Anzahl: " + mb.getOrte(kueb).remove());
		
		//nur regulare Mitglieder
		ArrayList<Selector<Mitglied>> regM = new ArrayList<Selector<Mitglied>>(); regM.add(new Mitglied.TypSelector(false));
		
		//Termin mit allen Mitglieder
		Termin t = new Termin(Termin.Typ.Auftritt , mb.getOrte(mOrt).getFirst(),
				toDate(2012,4,1),toDate(2012,4,1),2500.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());		
		//Teminvorschlag schlaegt fehl, da ersatzmitglieder noch keine proben haben
		System.out.println("Termin erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
		
		//TErminvorschlag sollte nicht fehlschlagen, Probe mit regulaeren Mitglieder
		t = new Termin(Termin.Typ.Probe , mb.getOrte(mOrt).getFirst(),
				toDate(2012,4,1),toDate(2012,4,1),2500.99,.0,(ArrayList<Mitglied>) mb.getMitglieder(regM).asList());
		mb.sendeTerminvorschlag(t);
		for(Mitglied m : mb.getMitglieder())
		{
			Terminvorschlag tv = m.getTerminvorschlaege().poll();
			if(tv != null)
				tv.accept(m);
		}
		
		//Alle Termine sollten akzeptiert sein
		
		System.out.println("Akzeptierte Termine: " + mb.getTermine().toString());
		
		//Termine entfernen
		System.out.println("Alle Termine entfernen, Anzahl: " + mb.getTermine().remove());
		t = new Termin(Termin.Typ.Probe , mb.getOrte().getFirst(),
				toDate(2012,6,1),toDate(2012,6,1),7000.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());
		mb.sendeTerminvorschlag(t);
		
		for(Mitglied m : mb.getMitglieder())
		{
			Terminvorschlag tv = m.getTerminvorschlaege().poll();
			if(tv != null)
				tv.accept(m);
			while(m.getNachrichten().peek() != null){
			System.out.println("Nachricht erhalten:" + m.getNachrichten().poll());}
		}
		
		System.out.println("Akzeptierte Termine: " + mb.getTermine().toString());
		
		//restore den einen termin in wr stadthalle
		Termine mT = mb.getTermine();
		ArrayList<Selector<Termin>> ts = new ArrayList<Selector<Termin>>();ts.add(new Termin.TypSelektor(Termin.Typ.Probe));
		mT.select(ts);
		mT.restore();
		for(Mitglied m : mb.getMitglieder(regM))
		{
			Terminvorschlag tv = m.getTerminvorschlaege().poll();
			if(tv != null)
				tv.accept(m);
		}
		System.out.println("Anzahl restored Termine = " + mT.count());
		
		//erstelle fuenf Proben die von allen akzeptiert werden
				t = new Termin(Termin.Typ.Probe , mb.getOrte(mOrt).getFirst(),
						toDate(2012,6,16),toDate(2012,6,16),2600.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());
				System.out.println("Termin erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
				t = new Termin(Termin.Typ.Probe , mb.getOrte().getFirst(),
						toDate(2012,6,7),toDate(2012,6,7),2600.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());
				System.out.println("Termin erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
				t = new Termin(Termin.Typ.Probe , mb.getOrte(mOrt).getFirst(),
						toDate(2012,6,8),toDate(2012,6,8),2700.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());
				System.out.println("Termin erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
				t = new Termin(Termin.Typ.Probe , mb.getOrte().getFirst(),
						toDate(2012,6,9),toDate(2012,6,9),2800.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());
				System.out.println("Termin erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
				t = new Termin(Termin.Typ.Probe , mb.getOrte(mOrt).getFirst(),
						toDate(2012,6,10),toDate(2012,6,10),2900.99,.0,(ArrayList<Mitglied>) mb.getMitglieder().asList());
				System.out.println("Termin erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
				t = new Termin(Termin.Typ.Auftritt , mb.getOrte(mOrt).getFirst(),
						toDate(2012,7,20),toDate(2012,7,20),17999.98,57025.11,(ArrayList<Mitglied>) mb.getMitglieder().asList());
				System.out.println("Auftritt erstellen erfolgreich = " + mb.sendeTerminvorschlag(t));
				for(Mitglied m : mb.getMitglieder())
				{
					Terminvorschlag tv = m.getTerminvorschlaege().poll();
					while(tv != null){
						tv.accept(m); tv = m.getTerminvorschlaege().poll();}
				}
		
		
				
		//Alle Termine sollten akzeptiert sein
		System.out.println("Akzeptierte Termine: " + mb.getTermine().toString());
		
		//Küniglberg restore
		System.out.println("Restore:"+ mb.getOrte(kueb).toString() + "Anzahl: ?");mb.getOrte(kueb).restore();
		System.out.println(mb.getOrte().toString());
	}
}
