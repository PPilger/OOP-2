import java.text.DateFormat;
import java.util.Date;

public class ZeitAb implements Zeitraum {
	private Date von;

	public ZeitAb(Date von) {
		this.von = von;
	}

	@Override
	public boolean inZeitraum(Date zeitpunkt) {
		return !von.after(zeitpunkt);
	}
	
	@Override
	public boolean enthaelt(ZeitIntervall intervall) {
		return !intervall.getVon().before(von);
	}

	@Override
	public String toString() {
		return toString(DateFormat.getDateInstance());
	}

	@Override
	public String toString(DateFormat format) {
		return "[" + format.format(von) + " - ]";
	}
}
